package structural

/**
  * Created by samidarko on 10/19/16.
  * What is the Decorator Pattern
  * * The Decorator Pattern allows you to modify an object dynamically
  * * Capabilities of inheritance with subclasses but add functionality at run time
  * * More flexible than inheritance
  */
object DecoratorPattern {

  trait Pizza {
    def getDescription : String
    def getCost : Double
  }

  class PlainPizza extends Pizza {
    override def getDescription: String = "Thin Dough"
    override def getCost: Double = 4
  }

  abstract class ToppingDecorator(protected val pizza: Pizza) extends Pizza {
    override def getDescription: String = pizza.getDescription
    override def getCost: Double = pizza.getCost
  }

  class Mozzarella(override val pizza: Pizza) extends ToppingDecorator(pizza) {
    println("Add Mozzarella")
    override def getDescription: String = super.getDescription ++ ", Mozzarella"
    override def getCost: Double = super.getCost + .5
  }

  class Tomato(override val pizza: Pizza) extends ToppingDecorator(pizza) {
    println("Add Tomato")
    override def getDescription: String = super.getDescription ++ ", Tomato"
    override def getCost: Double = super.getCost + .35
  }

  def main(args: Array[String]): Unit = {
    val pizza: Pizza = new Tomato(new Mozzarella(new PlainPizza))
    println(pizza.getDescription)
    println(pizza.getCost)
  }

}
