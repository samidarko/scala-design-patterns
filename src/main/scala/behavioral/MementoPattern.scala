package behavioral
import scala.collection.mutable.ArrayBuffer

/**
  * Created by vincentdupont on 28/10/16.
  * What is the Memento Pattern?
  */
object MementoPattern {

  class Memento(val state : String)

  class Originator {
    private var state : String = _

    def setState(state: String) : Unit = {
      println(s"Originator: setting state to $state")
      this.state = state
    }

    def saveToMemento : Memento = {
      println("Originator: saving to Memento")
      new Memento(state)
    }

    def restoreFromMemento(memento: Memento) : Unit = {
      state = memento.state
      println(s"Originator: restored state to $state")
    }

  }

  def main(args: Array[String]): Unit = {
    val states : ArrayBuffer[Memento] = ArrayBuffer[Memento]()

    val originator: Originator = new Originator
    originator.setState("State 1")
    originator.setState("State 2")
    states += originator.saveToMemento
    originator.setState("State 3")
    originator.restoreFromMemento(states.head)
    originator.setState("State 4")
  }

}
