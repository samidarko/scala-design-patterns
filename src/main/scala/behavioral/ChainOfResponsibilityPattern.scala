package behavioral

/**
  * Created by vincentdupont on 27/10/16.
  * What is the Chain Of esponsibility Pattern?
  * * This pattern sends data to an object and if that object can't use it, it sends it to any number of other objects that may be able to use it
  * * * Create 4 objects that can either add, subtract, multiply or divide
  * * * Send 2 numbers and a command and allow the 4 objects to decide which can handle the requested calculation
  */
object ChainOfResponsibilityPattern {

  class Operation(val a : Int, val b: Int, val operation : String)

  abstract class Chain {
    // used to be a trait but I changed it to abstract class as I Chain can inherit from chain var and setNextChain method
    protected var chain : Chain = _
    def setNextChain(chain: Chain): Unit = this.chain = chain
    def calculate(operation: Operation) : Unit
  }

  class Add extends Chain {

    override def calculate(operation: Operation): Unit = {
      if (operation.operation == "add")
        println(s"${operation.a} + ${operation.b} = ${operation.a + operation.b}")
      else
        chain.calculate(operation)
    }
  }

  class Sub extends Chain {

    override def calculate(operation: Operation): Unit = {
      if (operation.operation == "sub")
        println(s"${operation.a} - ${operation.b} = ${operation.a - operation.b}")
      else
        chain.calculate(operation)
    }
  }

  class Mul extends Chain {

    override def calculate(operation: Operation): Unit = {
      if (operation.operation == "mul")
        println(s"${operation.a} * ${operation.b} = ${operation.a * operation.b}")
      else
        chain.calculate(operation)
    }
  }

  class Div extends Chain {

    override def calculate(operation: Operation): Unit = {
      if (operation.operation == "div")
        println(s"${operation.a} / ${operation.b} = ${operation.a / operation.b}")
      else
        println("Only works for add, sub, mul and div")
    }
  }


  def main(args: Array[String]): Unit = {
    val add : Chain = new Add
    val sub : Chain = new Sub
    val mul : Chain = new Mul
    val div : Chain = new Div

    add.setNextChain(sub)
    sub.setNextChain(mul)
    mul.setNextChain(div)

    add.calculate(new Operation(4, 2, "add"))
    add.calculate(new Operation(4, 2, "sub"))
    add.calculate(new Operation(4, 2, "mul"))
    add.calculate(new Operation(4, 2, "div"))
    add.calculate(new Operation(4, 2, "odd"))
  }

}
