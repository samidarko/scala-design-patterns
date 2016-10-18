/**
  * Created by samidarko on 10/18/16.
  * When to use the Observer Pattern
  * * When you need may other objects to receive an update when another object changes
  * * * The Subject (publisher) sends information to the Observer
  * * * The Observers (subscribers) takes the ones they want and use
  * * Positive: loose coupling => the Subject doesn't need to know anything about the Observers
  * * Negative: The Subject may send updates that don't matter to the Observer
  */
object ObserverPattern {

  trait Observer {
    def update(ibmPrice: Double, aaplPrice: Double, googPrice: Double): Unit
  }

  trait Subject {
    def register(o: Observer): Unit

    def unRegister(o: Observer): Unit

    def notifyObserver(): Unit
  }

  class StockGrabber extends Subject {

    var ibmPrice: Double = 0
    var aaplPrice: Double = 0
    var googPrice: Double = 0
    var observers: List[Observer] = List[Observer]()

    override def register(o: Observer): Unit = {
      observers = o :: observers
    }

    override def unRegister(o: Observer): Unit = {
      observers = observers.filter(_ != o)
      println(s"un-register observer")
    }

    override def notifyObserver(): Unit = {
      observers.foreach(_.update(ibmPrice, aaplPrice, googPrice))
    }

    def setIbmPrice(price: Double): Unit = {
      ibmPrice = price
      notifyObserver()
    }

    def setAaplPrice(price: Double): Unit = {
      aaplPrice = price
      notifyObserver()
    }

    def setGoogPrice(price: Double): Unit = {
      googPrice = price
      notifyObserver()
    }
  }

  class StockObserver(stockGrabber: StockGrabber, val observerID: Int) extends Observer {

    println(s"New observer $observerID")

    stockGrabber.register(this)

    var ibmPrice: Double = 0
    var aaplPrice: Double = 0
    var googPrice: Double = 0

    override def update(ibmPrice: Double, aaplPrice: Double, googPrice: Double): Unit = {
      this.ibmPrice = ibmPrice
      this.aaplPrice = aaplPrice
      this.googPrice = googPrice
      printPrices()
    }

    def printPrices() : Unit = {
      println(s"Observer $observerID")
      println(s"IBM: $ibmPrice")
      println(s"Apple: $aaplPrice")
      println(s"Google: $googPrice")
    }

  }

  def main(args: Array[String]): Unit = {
    val stockGrabber: StockGrabber = new StockGrabber

    val obs1 = new StockObserver(stockGrabber, 1)
    new StockObserver(stockGrabber, 2)
    new StockObserver(stockGrabber, 3)

    stockGrabber.setIbmPrice(197.00)
    stockGrabber.setAaplPrice(677.60)
    stockGrabber.setGoogPrice(676.40)

    stockGrabber.unRegister(obs1)
    stockGrabber.setGoogPrice(656.40)

  }

}
