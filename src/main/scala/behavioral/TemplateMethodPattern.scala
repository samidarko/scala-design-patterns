package behavioral

/**
  * Created by samidarko on 10/25/16.
  * What is the Builder Pattern
  * * Used to create a group of subclasses that have to execute a similar group of methods
  * * You create an abstract class that contains a method called the Template Method
  * * The Template Method contains a series of method calls that every subclass object will call
  * * The subclass objects can override some of the method calls
  */
object TemplateMethodPattern {

  abstract class Hoagie {
    final def makeSandwich(): Unit = {
      cutBun()
      if (customerWantsMeat) {
        addMeat()
      }
      if (customerWantsCheese) {
        addCheese()
      }
      if (customerWantsVegetables) {
        addVegetables()
      }
      if (customerWantsCondiments) {
        addCondiments()
      }
      wrapTheHoagie()
    }
    def cutBun() : Unit = println("The bun is cut")

    def addMeat() : Unit
    def addCheese() : Unit
    def addVegetables() : Unit
    def addCondiments() : Unit

    def customerWantsMeat : Boolean = true
    def customerWantsCheese : Boolean = true
    def customerWantsVegetables : Boolean = true
    def customerWantsCondiments : Boolean = true

    def wrapTheHoagie() : Unit = println("The hoagie is wrapped")
  }

  class ItalianHoagie extends Hoagie {

    val meats = List("Salami", "Pepperoni", "Capricola Ham")
    val cheeses = List("Provolone")
    val vegetables = List("Lettuce", "Tomatoes", "Onions")
    val condiments = List("Oil", "Vinegar")

    override def addMeat(): Unit = println(s"Adding the meat: ${meats.mkString(", ")}")

    override def addCheese(): Unit = println(s"Adding the cheese: ${cheeses.mkString(", ")}")

    override def addVegetables(): Unit = println(s"Adding the vegetables: ${vegetables.mkString(", ")}")

    override def addCondiments(): Unit = println(s"Adding the condiments: ${condiments.mkString(", ")}")
  }

  class VeggieHoagie extends Hoagie {

    val vegetables = List("Lettuce", "Tomatoes", "Onions")
    val condiments = List("Oil", "Vinegar")

    override def customerWantsMeat : Boolean = false
    override def customerWantsCheese : Boolean = false

    override def addMeat(): Unit = Unit

    override def addCheese(): Unit = Unit

    override def addVegetables(): Unit = println(s"Adding the vegetables: ${vegetables.mkString(", ")}")

    override def addCondiments(): Unit = println(s"Adding the condiments: ${condiments.mkString(", ")}")
  }

  def main(args: Array[String]): Unit = {
    val firstHoagie : Hoagie = new ItalianHoagie
    firstHoagie.makeSandwich()

    val secondHoagie : Hoagie = new VeggieHoagie
    secondHoagie.makeSandwich()
  }

}
