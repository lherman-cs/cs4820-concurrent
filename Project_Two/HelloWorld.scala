object HelloWorld {
    def main(args: Array[String]) {
        println("Hello World!")
    }
}
/*
// Print infinitely 
object Timer {
  def oncePerSecond(callback: () => Unit) {
    while (true) { callback(); Thread sleep 1000 }
  }
  def timeFlies() {
    println("time flies like an arrow...")
  }
  def main(args: Array[String]) {
    oncePerSecond(timeFlies)
  }
}

// Equivilent to Timer
object TimerAnonymous {
  def oncePerSecond(callback: () => Unit) {
    while (true) { callback(); Thread sleep 1000 }
  }
  def main(args: Array[String]) {
    oncePerSecond(() =>
      println("time flies like an arrow..."))
  }
}

// Class of two arguments, doubles
class Complex(real: Double, imaginary: Double) {
  def re() = real
  def im() = imaginary
}
object ComplexNumbers {
  def main(args: Array[String]) {
    val c = new Complex(1.2, 3.4)
    println("imaginary part: " + c.im())
  }
}
class Complex(real: Double, imaginary: Double) {
  def re = real
  def im = imaginary
}
object complexNumbers {
  def main(args: Array[String]) {
    val c = new Complex(1.2, 3.4)
    println("imaginary part: " + c.im
  }
}
*/