package structural

/**
  * Created by samidarko on 10/25/16.
  * What is the Proxy Pattern
  * * Provide a class which will limit access to another class
  * * You may do this for security reasons, because an Object is intensive to create, or is accesses from a remote location
  */
object ProxyPattern {

  trait Image {
    def display() : Unit
  }

  class ConcreteImage(name: String) extends Image {
    println(s"Image $name loaded")
    override def display(): Unit = println(s"display $name")
  }

  class ProxyImage(name: String) extends Image {
    private var image : ConcreteImage = _
    override def display(): Unit = {
      if (image == null) {
        image = new ConcreteImage(name)
      }
      image.display()
    }
  }

  def main(args: Array[String]): Unit = {
    val pic1 = new ProxyImage("pic1")
    val pic2 = new ProxyImage("pic2")
    pic1.display()  // loaded
    pic1.display()  // already loaded
    pic2.display()  // loaded
    pic2.display()  // already loaded
  }

}
