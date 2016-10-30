package behavioral

/**
  * Created by vincentdupont on 30/10/16.
  * What is the Visitor Pattern
  * * Represent an operation to be performed on elements of an object structure. Visitor lets you define a new operation without changing the classes of the elements on which it operates.
  */
object VisitorPattern {

  trait CarElementVisitor {
    def visit(carElement: CarElement) : Unit
  }

  trait CarElement {
    def accept(visitor : CarElementVisitor) : Unit
  }

  case class Wheel(name : String) extends CarElement {
    def accept(visitor : CarElementVisitor) : Unit = {
      visitor.visit(this)
    }
  }

  case class Engine() extends CarElement {
    def accept(visitor : CarElementVisitor) {
      visitor.visit(this)
    }
  }

  case class Body() extends CarElement {
    def accept(visitor : CarElementVisitor) {
      visitor.visit(this)
    }
  }

  case class Car() extends CarElement {

    val elements : List[CarElement] = List(
      Wheel("front left"), Wheel("front right"),
      Wheel("back left"), Wheel("back right"),
      Body(), Engine()
    )

    def accept(visitor : CarElementVisitor) {
      for(element <- elements) {
        element.accept(visitor)
      }
      visitor.visit(this)
    }
  }

  class CarElementPrintVisitor extends CarElementVisitor {
    def visit(carElement: CarElement) : Unit = carElement match {
      case Wheel(n) => println(s"Visiting $n wheel")
      case Engine() => println("Visiting engine")
      case Body() => println("Visiting body")
      case Car() => println("Visiting car")
      case _ => println("Unknown car element")
    }
  }

  class CarElementDoVisitor extends CarElementVisitor {
    def visit(carElement: CarElement) : Unit = carElement match {
      case Wheel(n) => println(s"Kicking my $n wheel")
      case Engine() => println("Starting my engine")
      case Body() => println("Moving my body")
      case Car() => println("Starting my car")
      case _ => println("Unknown car element")
    }
  }

  def main(args: Array[String]): Unit = {
    val car = Car()
    car.accept(new CarElementPrintVisitor())
    car.accept(new CarElementDoVisitor())
  }

}
