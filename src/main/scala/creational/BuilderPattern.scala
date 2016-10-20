package creational

/**
  * Created by vincentdupont on 18/10/16.
  * What is the Builder Pattern
  * * Pattern used to create objects made from a bunch of other objects
  * * Hide the creation of the parts from the client so both aren't dependent
  * * The builder knows about the specific
  */
object BuilderPattern {
  trait RobotPlan {
    def setRobotHead(head: String) : Unit
    def setRobotTorso(torso: String) : Unit
    def setRobotArms(arms: String) : Unit
    def setRobotLegs(legs: String) : Unit
  }

  class Robot extends RobotPlan {

    var head : String = _
    var torso : String = _
    var arms : String = _
    var legs : String = _

    override def setRobotHead(head: String): Unit = this.head = head

    def getRobotHead: String = head

    override def setRobotTorso(torso: String): Unit = this.torso = torso

    def getRobotTorso: String = torso

    override def setRobotArms(arms: String): Unit = this.arms = arms

    def getRobotArms: String = arms

    override def setRobotLegs(legs: String): Unit = this.legs = legs

    def getRobotLegs: String = legs
  }

  trait RobotBuilder {
    def buildRobotHead() : Unit
    def buildRobotTorso() : Unit
    def buildRobotArms() : Unit
    def buildRobotLegs() : Unit
    def getRobot : Robot
  }

  class OldRobotBuilder extends RobotBuilder {

    var robot : Robot = new Robot

    override def buildRobotHead(): Unit = robot.setRobotHead("Tin Head")

    override def buildRobotTorso(): Unit = robot.setRobotTorso("Tin Torso")

    override def buildRobotArms(): Unit = robot.setRobotArms("Blowtorch Arms")

    override def buildRobotLegs(): Unit = robot.setRobotLegs("RollerSkate Legs")

    override def getRobot: Robot = robot
  }

  class RobotEngineer(robotBuilder: RobotBuilder) {
    def getRobot : Robot = robotBuilder.getRobot
    def makeRobot() : Unit = {
      robotBuilder.buildRobotHead()
      robotBuilder.buildRobotTorso()
      robotBuilder.buildRobotArms()
      robotBuilder.buildRobotLegs()
    }
  }

  def main(args: Array[String]): Unit = {

    val oldRobotBuilder : RobotBuilder = new OldRobotBuilder
    val robotEngineer : RobotEngineer = new RobotEngineer(oldRobotBuilder)
    robotEngineer.makeRobot()
    val robot : Robot = robotEngineer.getRobot

    println("Robot built:")
    println(s"Robot Head Type: ${robot.getRobotHead}")
    println(s"Robot Torso Type: ${robot.getRobotTorso}")
    println(s"Robot Arms Type: ${robot.getRobotArms}")
    println(s"Robot Legs Type: ${robot.getRobotLegs}")

  }

}
