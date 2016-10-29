package structural
import scala.collection.mutable

/**
  * Created by samidarko on 10/25/16.
  * What is the Flyweight Pattern
  * * Used when you need to create a large number of similar objects
  * * To reduce memory usage you share Objects that are similar in some way rather than creating new ones
  * * Intrinsic State: Color
  * * Extrinsic State: Size
  */
object FlyweightPattern {

  class CoffeeFlavour(name: String) {
    override def toString: String = s"CoffeeFlavour($name)"
  }

  class Order(tableNumber: Int, coffeeFlavour: CoffeeFlavour) {
    def serve() : Unit = println(s"Serving $coffeeFlavour to table $tableNumber")
  }

  object CoffeeFlavour {
    private val cache = mutable.Map.empty[String, CoffeeFlavour]

    def apply(name: String): CoffeeFlavour =
      cache.getOrElseUpdate(name, new CoffeeFlavour(name))

    def totalCoffeeFlavoursMade = cache.size
  }

  object CoffeeShop {
    var orders = List.empty[Order]

    def takeOrder(flavourName: String, table: Int) {
      val flavour = CoffeeFlavour(flavourName)
      val order = new Order(table, flavour)
      orders = order :: orders
    }

    def service() : Unit = orders.foreach(_.serve())

    def report = s"total CoffeeFlavour objects made: ${CoffeeFlavour.totalCoffeeFlavoursMade}"

  }

  def main(args: Array[String]): Unit = {

    CoffeeShop.takeOrder("Cappuccino", 2)
    CoffeeShop.takeOrder("Frappe", 1)
    CoffeeShop.takeOrder("Espresso", 1)
    CoffeeShop.takeOrder("Frappe", 897)
    CoffeeShop.takeOrder("Cappuccino", 97)
    CoffeeShop.takeOrder("Frappe", 3)
    CoffeeShop.takeOrder("Espresso", 3)
    CoffeeShop.takeOrder("Cappuccino", 3)
    CoffeeShop.takeOrder("Espresso", 96)
    CoffeeShop.takeOrder("Frappe", 552)
    CoffeeShop.takeOrder("Cappuccino", 121)
    CoffeeShop.takeOrder("Espresso", 121)

    CoffeeShop.service()
    println(CoffeeShop.report)
  }

}
