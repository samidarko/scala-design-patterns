package behavioral

/**
  * Created by vincentdupont on 26/10/16.
  * What is the State Pattern
  * * Allows an object to alter its behavior when its internal state changes. The object will appear to change its class
  * * Context (Account) : Maintains an instance of ConcreteState subclass that defines the current state
  * * State : Defines an interface for encapsulating the behavior associated with a particular state of the Context
  * * Concrete State : Each subclass implements a behavior associated with a state of Context
  */
object StatePattern {

  trait ATMState {
    def insertCard() : Unit
    def ejectCard() : Unit
    def requestCash(amount: Int) : Unit
    def insertPin(pin : Int) : Unit
  }

  class Unused(aTMMachine: ATMMachine) extends ATMState {

    private def defaultState() : Unit = println("you did not insert your card")

    override def insertCard(): Unit = {
      aTMMachine.setCardInserted()
      println("Please enter your PIN")
    }

    override def ejectCard(): Unit = defaultState()

    override def requestCash(amount: Int): Unit = defaultState()

    override def insertPin(pin: Int): Unit = defaultState()
  }

  class CardInserted(aTMMachine: ATMMachine) extends ATMState {
    override def insertCard(): Unit = println("You can only insert one card at a time")

    override def ejectCard(): Unit = {
      println("Your card is ejected")
      aTMMachine.setUnused()
    }

    override def requestCash(amount: Int): Unit = println("you do not have entered your PIN")

    override def insertPin(pin: Int): Unit = {
      if (pin == 1234) {
        println("You entered the correct PIN")
        aTMMachine.setPinEntered()
      } else {
        println("You entered the wrong PIN")
        println("Your card is ejected")
        aTMMachine.setUnused()
      }
    }
  }

  class PinEntered(aTMMachine: ATMMachine) extends ATMState {
    override def insertCard(): Unit = println("You already entered a card")

    override def ejectCard(): Unit = {
      println("Your card is ejected")
      aTMMachine.setUnused()
    }

    override def requestCash(amount: Int): Unit = {
      if(amount > aTMMachine.getCashInMachine){
        println("You don't have that much cash available")
        println("Your card is ejected")
        aTMMachine.setUnused()
      } else {
        println(amount + " is provided by the machine")
        aTMMachine.setCashInMachine(aTMMachine.getCashInMachine - amount)
        println("Your card is ejected")
        aTMMachine.setUnused()
        if(aTMMachine.getCashInMachine <= 0){
          aTMMachine.setOutOfCash()
        }
      }

    }

    override def insertPin(pin: Int): Unit = println("You already entered you PIN")

  }

  class OutOfCash(aTMMachine: ATMMachine) extends ATMState {
    private def defaultState() : Unit = println("We don't have any money")
    override def insertCard(): Unit = {
      defaultState()
      println("Your card is ejected")
    }

    override def ejectCard(): Unit = defaultState()

    override def requestCash(amount: Int): Unit = defaultState()

    override def insertPin(pin: Int): Unit = defaultState()

  }

  class ATMMachine extends ATMState {
    private var cashInMachine = 1000


    private var state : ATMState = new Unused(this)

    def setUnused() : Unit = state = new Unused(this)
    def setCardInserted() : Unit = state = new CardInserted(this)
    def setPinEntered() : Unit = state = new PinEntered(this)
    def setOutOfCash() : Unit = state = new OutOfCash(this)
    def setCashInMachine(amount: Int) = cashInMachine = amount
    def getCashInMachine : Int = cashInMachine

    override def insertCard(): Unit = state.insertCard()

    override def ejectCard(): Unit = state.ejectCard()

    override def requestCash(amount: Int): Unit = state.requestCash(amount)

    override def insertPin(pin: Int): Unit = state.insertPin(pin)
  }


  def main(args: Array[String]): Unit = {

    // TODO review State Pattern not sure this implementation is very good
    val aTMMachine: ATMMachine = new ATMMachine
    aTMMachine.insertCard()
    aTMMachine.insertPin(0)
    aTMMachine.insertCard()
    aTMMachine.insertPin(1234)
    aTMMachine.requestCash(500)
    aTMMachine.insertCard()
    aTMMachine.insertPin(2234)
    aTMMachine.insertCard()
    aTMMachine.ejectCard()

  }
}
