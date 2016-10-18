/**
  * Created by samidarko on 10/18/16.
  * When to use the Factory Pattern
  * * When a method returns one of several possible classes that share common super class
  * * * Create a new enemy in a game
  * * * Random number generator picks a number assigned to a specific enemy
  * * * The factory returns the enemy associated with that number
  * * The class is chosen at run time
  * * To encapsulate object creation
  */
object FactoryPattern {

  abstract class EnemyShip(var name : String, var damage : Double) {
    def getName = name
    def setName(n : String) = name = n
    def getDamage = damage
    def setDamage(d : Double) = damage = d
    def followHeroShip() = println(s"$getName is following the hero")
    def displayEnemyShip() = println(s"$getName is on the screen")
    def enemyShipShoots() = println(s"$getName attacks and does $getDamage damages")
  }

  class UFOEnemyShip extends EnemyShip("UFO Enemy Ship", 20)
  class RocketEnemyShip extends EnemyShip("Rocket Enemy Ship", 10)
  class BigUFOEnemyShip extends EnemyShip("Big UFO Enemy Ship", 40)

  def doStuffEnemy(enemyShip: EnemyShip) = {
    enemyShip.displayEnemyShip()
    enemyShip.followHeroShip()
    enemyShip.enemyShipShoots()
  }

  class EnemyShipFactory {
    def makeEnemyShip(ship : String) : EnemyShip = ship match {
      case "U" => new UFOEnemyShip
      case "R" => new RocketEnemyShip
      case "B" => new BigUFOEnemyShip
      case _ => throw new NoSuchElementException(s"no such a ship")
    }
  }

  def main(args: Array[String]): Unit = {
    val factory : EnemyShipFactory = new EnemyShipFactory
    doStuffEnemy(factory.makeEnemyShip("U"))
  }

}
