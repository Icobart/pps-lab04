package it.unibo.pps.tasks.adts

/*  Exercise 1: 
 *  Complete the implementation of ComplexADT trait below, so that it passes
 *  the test in ComplexTest.
 */

object Ex1ComplexNumbers:

  trait ComplexADT:
    type Complex
    def complex(re: Double, im: Double): Complex
    extension (complex: Complex)
      def re(): Double
      def im(): Double
      def sum(other: Complex): Complex
      def subtract(other: Complex): Complex
      def asString(): String

  object BasicComplexADT extends ComplexADT:

    case class ComplexImpl(real: Double, imag: Double)
    type Complex = ComplexImpl
    def complex(re: Double, im: Double): Complex = ComplexImpl(re, im)
    extension (complex: Complex)
      def re(): Double = complex.real
      def im(): Double = complex.imag
      def sum(other: Complex): Complex = ComplexImpl(complex.real + other.real, complex.imag + other.imag)
      def subtract(other: Complex): Complex = ComplexImpl(complex.real - other.real, complex.imag - other.imag)
      def asString(): String = (complex.real, complex.imag) match
        case (r, 0.0) => s"$r"
        case (0.0, i) => s"${i}i"
        case (r, i) if i < 0 => s"$r - ${Math.abs(i)}i"
        case (r, i) => s"$r + ${i}i"

