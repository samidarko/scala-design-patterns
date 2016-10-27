
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


class C(private var c: Int)

val o = new C(1)
//o.c
//o.c = 2
//o.c

class D {
  private var d : Int = _
}

val d = new D
//d.d = 1
//println(d.d)
