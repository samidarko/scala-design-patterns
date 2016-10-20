/**
  * Created by samidarko on 10/20/16.
  * What is the Adapter Pattern
  * * Allows 2 incompatible interface to work together
  * * Used when the client expects a (target) interface
  * * The Adapter class allows the use of the available interface and the Target interface
  * * Any class can work together as long as the Adapter solved the issue that all classes must implement every method defined by the shared interface
  */
import scala.util.Random

object AdapterPattern {

  trait EnemyAttacker {
    def fireWeapon() : Unit
    def driveForward() : Unit
    def assignDriver(driverName : String) : Unit
  }

  class EnemyTank extends EnemyAttacker {
    val random = new Random()
    override def fireWeapon(): Unit = println(s"Enemy Tank does ${random.nextInt(10)+1} damages")
    override def driveForward(): Unit = println(s"Enemy Tank moves ${random.nextInt(5)+1} spaces")
    override def assignDriver(driverName: String): Unit = println(s"$driverName is driving the tank")
  }

  class EnemyRobot {
    val random = new Random()
    def smashWithHands() : Unit = println(s"Enemy Robot causes ${random.nextInt(10)+1} with its hands")
    def walkForward() : Unit = println(s"Enemy Tank walks forward ${random.nextInt(5)+1} spaces")
    def reactToHuman(driverName: String) : Unit = println(s"Enemy Robot tramps on $driverName")
  }

  class EnemyRobotAdapter(enemyRobot: EnemyRobot) extends EnemyAttacker {
    override def fireWeapon(): Unit = enemyRobot.smashWithHands()
    override def driveForward(): Unit = enemyRobot.walkForward()
    override def assignDriver(driverName: String): Unit = enemyRobot.reactToHuman(driverName)
  }

  def main(args: Array[String]): Unit = {
    val enemyTank : EnemyAttacker = new EnemyTank
    val enemyRobot : EnemyRobot = new EnemyRobot
    val enemyRobotAdapter : EnemyAttacker = new EnemyRobotAdapter(enemyRobot)

    println("The robot")
    enemyRobot.reactToHuman("Paul")
    enemyRobot.walkForward()
    enemyRobot.smashWithHands()

    println("The tank")
    enemyTank.assignDriver("John")
    enemyTank.driveForward()
    enemyTank.fireWeapon()

    println("The robot adapter")
    enemyRobotAdapter.assignDriver("Ringo")
    enemyRobotAdapter.driveForward()
    enemyRobotAdapter.fireWeapon()

  }

}
