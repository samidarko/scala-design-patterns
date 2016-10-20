package behavioral

/**
  * Created by samidarko on 10/18/16.
  * When to use the Strategy Pattern
  * * Often reduces long list of conditionals
  * * Avoids duplicate code
  * * Keeps class changes from forcing other class changes
  * * Can hide complicated / secret code from the user
  * * Negative: Increased number of Objects / Classes
  */
object StrategyPattern {

  trait Fly {
    def fly: String
  }

  class FlyWithWings extends Fly {
    override def fly = "fly with wings"
  }

  class FlyWithEngine extends Fly {
    override def fly = "fly with an engine"
  }

  class CanNotFly extends Fly {
    override def fly = "cannot fly"
  }

  abstract class Animal(var name: String, var flyingType : Fly) {

    def getName = name
    def setName(n : String) = name = n
    def tryToFly : String = flyingType.fly
    def setFlyingAbility(flyingAbility : Fly) = flyingType = flyingAbility

  }

  class Dog(name: String, flyingType : Fly) extends Animal(name, flyingType) {
  }

  class Bird(name: String, flyingType : Fly) extends Animal(name, flyingType) {
  }

  def main(args: Array[String]): Unit = {

    val dog = new Dog("doggy", new CanNotFly)
    val bird = new Dog("birdy", new FlyWithWings)

    println(s"dog ${dog.tryToFly}")
    println(s"bird ${bird.tryToFly}")
    dog.setFlyingAbility(new FlyWithEngine)
    println(s"dog ${dog.tryToFly}")
  }


}
