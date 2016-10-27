package behavioral
import scala.collection.mutable

/**
  * Created by vincentdupont on 27/10/16.
  */
object InterpreterPattern {

  trait Expression {
    def interpret(variables : Map[String, Expression]) : Int
  }

  class Number(number : Int) extends Expression {
    override def interpret(variables: Map[String, Expression]): Int = number
  }

  class Plus(left : Expression, right : Expression) extends Expression {
    override def interpret(variables: Map[String, Expression]): Int = left.interpret(variables) + right.interpret(variables)
  }

  class Minus(left : Expression, right : Expression) extends Expression {
    override def interpret(variables: Map[String, Expression]): Int = left.interpret(variables) - right.interpret(variables)
  }

  class Divide(left : Expression, right : Expression) extends Expression {
    override def interpret(variables: Map[String, Expression]): Int = left.interpret(variables) / right.interpret(variables)
  }

  class Multiply(left : Expression, right : Expression) extends Expression {
    override def interpret(variables: Map[String, Expression]): Int = left.interpret(variables) * right.interpret(variables)
  }

  class Variable(name : String) extends Expression {
    override def interpret(variables: Map[String, Expression]): Int =
      if (variables.contains(name)) variables(name).interpret(variables) else 0
  }

  class Evaluator(expression: String) extends Expression {

    val expressionStack : mutable.Stack[Expression] = mutable.Stack[Expression]()
    var syntaxTree : Expression = _

    for (token <- expression.split(" ")) {
      if (token == "+") {
        expressionStack.push(new Plus(expressionStack.pop(), expressionStack.pop()))
      } else if (token == "-") {
        val right = expressionStack.pop()
        val left = expressionStack.pop()
        expressionStack.push(new Minus(left, right))
      } else if (token == "/") {
        expressionStack.push(new Divide(expressionStack.pop(), expressionStack.pop()))
      } else if (token == "*") {
        expressionStack.push(new Multiply(expressionStack.pop(), expressionStack.pop()))
      } else {
        expressionStack.push(new Variable(token))
      }
    }

    syntaxTree = expressionStack.pop()

    override def interpret(variables: Map[String, Expression]): Int = {
      println(syntaxTree)
      syntaxTree.interpret(variables)
    }
  }

  def main(args: Array[String]): Unit = {
    val expression = "w x z - +"
//    val expression = "w x /"
    val evaluator : Evaluator = new Evaluator(expression)
    val variables = Map[String, Expression](
      "w" -> new Number(5),
      "x" -> new Number(10),
      "z" -> new Number(42)
    )
    val result : Int = evaluator.interpret(variables)
    println(s"$expression = $result")
  }

}
