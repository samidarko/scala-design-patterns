package behavioral
import scala.collection.mutable.ArrayBuffer
/**
  * Created by vincentdupont on 29/10/16.
  * What is the Mediator Pattern?
  * * It is used to handle communication between related objects (Colleagues)
  * * All communication is handled by the Mediator and the Colleagues don't need to know anything about each other
  * * GOF:  Allows loos coupling by encapsulating the way disparate sets of objects interact and communicate with each other.
  *         Allows for the actions of each objects set to vary independently of one another.
  */
object MediatorPattern {

  class StockOffer(val stockSymbol : String, val stockShares : Int, val colleagueCode : Int) {

    override def toString : String = s"$stockShares of $stockSymbol"

  }

  abstract class Colleague(mediator: Mediator) {
    mediator.addColleague(this)

    var colleagueCode : Int = _

    def saleOffer(stockSymbol : String, stockShares : Int) : Unit = mediator.saleOffer(stockSymbol, stockShares, colleagueCode)
    def buyOffer(stockSymbol : String, stockShares : Int) : Unit = mediator.buyOffer(stockSymbol, stockShares, colleagueCode)

  }

  class GormanSlacks(m: Mediator) extends Colleague(m) {
    println("Gorman Slacks signed up with the stock exchange")
  }

  class JTPoorman(m: Mediator) extends Colleague(m) {
    println("JT Poorman signed up with the stock exchange")
  }

  trait Mediator {
    def saleOffer(stockSymbol : String, stockShares : Int, colleagueCode : Int) : Unit
    def buyOffer(stockSymbol : String, stockShares : Int, colleagueCode : Int) : Unit
    def addColleague(colleague: Colleague) : Unit
  }

  class StockMediator extends Mediator {

    val colleagues : ArrayBuffer[Colleague] = ArrayBuffer[Colleague]()
    val stockBuyOffers : ArrayBuffer[StockOffer] = ArrayBuffer[StockOffer]()
    val stockSellOffers : ArrayBuffer[StockOffer] = ArrayBuffer[StockOffer]()

    private var colleaguesCodeCounter = 0

    override def addColleague(colleague: Colleague): Unit = {
      colleagues += colleague
      colleaguesCodeCounter += 1
      colleague.colleagueCode = colleaguesCodeCounter
    }

    override def saleOffer(stockSymbol: String, stockShares: Int, colleagueCode: Int): Unit = {
      def fn(offers : List[StockOffer]) : Boolean = offers match  {
        case (offer::offersLeft) => if (offer.stockSymbol == stockSymbol && offer.stockShares == stockShares) {
          println(s"$stockShares shares of $stockSymbol bought by colleague code ${offer.colleagueCode}")
          stockBuyOffers -= offer
          true
        } else fn(offersLeft)
        case _ => false
      }

      if (!fn(stockBuyOffers.toList)) {
        println(s"$stockShares shares of $stockSymbol added to inventory")
        stockSellOffers += new StockOffer(stockSymbol, stockShares, colleagueCode)
      }

    }

    override def buyOffer(stockSymbol: String, stockShares: Int, colleagueCode: Int): Unit = {
      def fn(offers : List[StockOffer]) : Boolean = offers match  {
        case (offer::offersLeft) => if (offer.stockSymbol == stockSymbol && offer.stockShares == stockShares) {
          println(s"$stockShares shares of $stockSymbol bought by colleague code ${offer.colleagueCode}")
          stockSellOffers -= offer
          true
        } else fn(offersLeft)
        case _ => false
      }

      if (!fn(stockSellOffers.toList)) {
        println(s"$stockShares shares of $stockSymbol added to inventory")
        stockBuyOffers += new StockOffer(stockSymbol, stockShares, colleagueCode)
      }
    }

    def stockOfferings() : Unit = {
      println()
      println("Stocks for sale:")
      stockSellOffers.foreach(o => println(o.toString))
      println("Stocks buy offers:")
      stockBuyOffers.foreach(o => println(o.toString))
      println()
    }

  }

  def main(args: Array[String]): Unit = {
    val nyse : StockMediator = new StockMediator
    val broker1 : Colleague = new GormanSlacks(nyse)
    val broker2 : Colleague = new JTPoorman(nyse)

    broker1.saleOffer("MSFT", 100)
    broker1.saleOffer("GOOG", 50)
    broker2.buyOffer("MSFT", 100)
    nyse.stockOfferings()

    broker2.saleOffer("NRG", 10)
    broker1.buyOffer("NRG", 10)
    nyse.stockOfferings()

  }


}
