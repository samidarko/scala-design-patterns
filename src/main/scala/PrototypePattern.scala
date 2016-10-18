/**
  * Created by vincentdupont on 18/10/16.
  * What is the Prototype Pattern
  * * Creating new objects (instances) by cloning (copying) other objects
  * * Allows for adding of any subclass instance of a known super class at run time
  * * When there are numerous potential classes that you want to only use if needed at runtime
  * * Reduces the need for creating subclasses
  */
object PrototypePattern {

  trait Animal extends Cloneable {
    def makeCopy : Animal
  }

  class Sheep extends Animal {
    println("Sheep is made")
    override def makeCopy: Animal = {
      println("Sheep is being made")
      super.clone().asInstanceOf[Sheep]
    }
    override def toString: String = "Dolly is my Hero, Baaaa"
  }

  class CloneFactory {
    def getClone(animal: Animal) : Animal = animal.makeCopy
  }

  def main(args: Array[String]): Unit = {
    val cloneFactory : CloneFactory = new CloneFactory
    val sally: Sheep = new Sheep
    val clone : Sheep = cloneFactory.getClone(sally).asInstanceOf[Sheep]

    println(sally)
    println(clone)
    println(s"Sally Hashcode ${sally.hashCode()}")
    println(s"Clone Hashcode ${clone.hashCode()}")

  }

}
