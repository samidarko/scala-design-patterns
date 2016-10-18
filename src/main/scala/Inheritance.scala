

object Inheritance extends App {

  class Animal(var name: String, var weight: Int, var sound : String) {

    def getName = name
    def setName(n : String) = name = n
    def getWeight = weight
    def setWeight(w: Int) = if (w <= 0) println("weight must be positive") else weight = w
    def getSound = sound
    def setSound(s: String) = sound = s

  }

  class Dog(name: String, weight: Int) extends Animal(name, weight, "Bark") {
    def digHole() = println("digging")
  }

  class Cat(name: String, weight: Int) extends Animal(name, weight, "Meow") {
    def digHole() = println("meow")
  }

  val dog : Dog = new Dog("dog", 10)
  dog.digHole()
  println(dog.getSound)
  dog.setWeight(0)

  val cat : Cat = new Cat("cat", 5)
  println(cat.getSound)

  // Polymorphism
  val doggy : Animal = new Dog("dog", 10)
  val kitty : Animal = new Cat("cat", 5)

  println(s"doggy said " ++ doggy.getSound)
  println("kitty said " ++ kitty.getSound)

  abstract class Creature(name: String) {
    def setName(n: String) : Unit
    def getName : String
    def greetings : String = "hello"
  }

  class Giraffe(var name: String) extends Creature(name) {
    def setName(n: String): Unit = {
      name = n
    }
    override def getName: String = name
  }

  val g : Creature = new Giraffe("Vincent")
  println("Giraffe name is " ++ g.getName)
  g.setName("Giraffe")
  println("Giraffe name is " ++ g.getName)
  println("Giraffe says " ++ g.greetings)



  trait Living {
    def setName(n: String) : Unit
    def getName : String
    def greetings : String = "hello"
  }

  class Monkey(var name: String) extends Living {
    override def setName(n: String): Unit = name = n
    override def getName: String = name
  }

  val monkey : Living = new Monkey("Chimpanzee")
  println("Monkey name is " ++ monkey.getName)
  monkey.setName("Monkey")
  println("Monkey name is " ++ monkey.getName)
  println("Monkey says " ++ monkey.greetings)


}