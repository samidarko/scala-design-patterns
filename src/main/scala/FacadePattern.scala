/**
  * Created by samidarko on 10/20/16.
  * What is the Adapter Pattern
  * * When you create a simplified interface that performs many other actions behind the scene
  */
object FacadePattern {

  class AccountNumberCheck {
    val accountNumber: Int = 1
    def accountActive(accountNumber: Int): Boolean = this.accountNumber == accountNumber
  }

  class SecurityCodeCheck {
    val securityCode: Int = 1234
    def isCodeCorrect(securityCode: Int): Boolean = this.securityCode == securityCode
  }

  class FundsCheck {
    var casCashInAccount: Double = 1000.00

    private def decreaseCashInAccount(cash: Double): Unit = casCashInAccount -= cash

    private def increaseInAccount(cash: Double): Unit = casCashInAccount += cash

    def haveEnoughMoney(cash: Double): Boolean = cash > casCashInAccount

    def showBalance(): Unit = {
      println(s"Current balance: $casCashInAccount")
    }

    def drawMoney(cash: Double): Unit = {
      if (haveEnoughMoney(cash)) {
        decreaseCashInAccount(cash)
        println(s"Withdrawal complete")
        showBalance()
      } else {
        println("Error: you don't have enough money")
        showBalance()
      }

    }

    def makeDeposit(cash: Double): Unit = {
      increaseInAccount(cash)
      println(s"Deposit complete")
      showBalance()
    }
  }

  class BankAccountFacade(accountNumber: Int, securityCode: Int) {

    private val accountNumberCheck: AccountNumberCheck = new AccountNumberCheck
    private val securityCodeCheck: SecurityCodeCheck = new SecurityCodeCheck
    private val fundsCheck: FundsCheck = new FundsCheck

    def drawMoney(cash: Double): Unit = {
      if (accountNumberCheck.accountActive(accountNumber) && securityCodeCheck.isCodeCorrect(securityCode)) {
        fundsCheck.drawMoney(cash)
      } else {
        println("something is wrong with your account")
      }
    }

    def makeDeposit(cash: Double): Unit = {
      if (accountNumberCheck.accountActive(accountNumber) && securityCodeCheck.isCodeCorrect(securityCode)) {
        fundsCheck.makeDeposit(cash)
      } else {
        println("something is wrong with your account")
      }
    }
  }

  def main(args: Array[String]): Unit = {
    var bankAccountFacade : BankAccountFacade = new BankAccountFacade(0, 0)
    bankAccountFacade.drawMoney(100)
    bankAccountFacade.makeDeposit(100)
    bankAccountFacade = new BankAccountFacade(1, 1234)
    bankAccountFacade.drawMoney(100)
    bankAccountFacade.makeDeposit(100)
    bankAccountFacade.drawMoney(100000)
  }

}
