
class A(var a: Int) {
}

class B(var b: Int) extends A(b) {
  def increase() : Unit = {
    a += 1
    println(a)
  }
}


val b = new B(1)

b.increase()

