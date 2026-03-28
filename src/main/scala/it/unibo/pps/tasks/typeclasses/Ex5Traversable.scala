package it.unibo.pps.tasks.typeclasses

import it.unibo.pps.u03.Sequences.Sequence, Sequence.*
import it.unibo.pps.u03.Optionals.Optional, Optional.*

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  def log[A](a: A): Unit = println("The next element is: "+a)

  trait Traversable[T[_]]:
    def traverse[A](t: T[A])(consumer: A => Unit): Unit

  def logAll[T[_] : Traversable, A](t: T[A])(consumer: A => Unit): Unit =
    val traversable = summon[Traversable[T]]
    traversable.traverse(t)(consumer)

  given Traversable[Optional] with
    def traverse[A](opt: Optional[A])(consumer: A => Unit): Unit = opt match
      case Just(e) => consumer(e)
      case _ => ()

  given Traversable[Sequence] with
    def traverse[A](seq: Sequence[A])(consumer: A => Unit): Unit = seq match
      case Cons(h, t) =>
        consumer(h)
        traverse(t)(consumer)
      case _ => ()

  @main def tryTraversable() =
    val seq = Cons(10, Cons(20, Cons(30, Nil())))
    val opt = Just(40)
    println("Sequence log:")
    logAll(seq)(log)
    println("Sequence println:")
    logAll(seq)(println)
    println("Optional log:")
    logAll(opt)(log)
    println("Optional println:")
    logAll(opt)(println)
