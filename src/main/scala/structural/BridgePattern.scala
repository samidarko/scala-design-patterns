package structural

/**
  * Created by vincentdupont on 20/10/16.
  * What is the Decorator Pattern
  * * Decouple an abstraction from its implementation so that the two can vary independently
  * * Progressively adding functionality while separating out major differences using abstract classes
  */
object BridgePattern {

  abstract class EntertainmentDevice(var deviceState: Int, var maxSetting: Int) {
    var volumeLevel = 0

    def buttonFivePressed(): Unit

    def buttonSixPressed(): Unit

    def deviceFeedback(): Unit = {
      if (deviceState > maxSetting || deviceState < 0) deviceState = 0
      println(s"On $deviceState")
    }

    private def showVolumeLevel(): Unit = {
      println(s"Volume at $volumeLevel")
    }

    def buttonSevenPressed(): Unit = {
      volumeLevel += 1
      showVolumeLevel()
    }

    def buttonHeightPressed(): Unit = {
      volumeLevel -= 1
      showVolumeLevel()
    }

  }

  class TVDevice(_deviceState: Int, maxSetting: Int) extends EntertainmentDevice(_deviceState, maxSetting) {
    override def buttonFivePressed(): Unit = {
      println("Channel Down")
      deviceState -= 1
    }

    override def buttonSixPressed(): Unit = {
      println("Channel Up")
      deviceState += 1
    }
  }

    class DVDDevice(_deviceState : Int, maxSetting: Int) extends EntertainmentDevice(_deviceState, maxSetting) {
      override def buttonFivePressed(): Unit = {
        println("Previous Chapter")
        deviceState -= 1
      }

      override def buttonSixPressed(): Unit = {
        println("Next Chapter")
        deviceState += 1
      }
    }

  abstract class RemoteControl(var entertainmentDevice: EntertainmentDevice) {
    def buttonFivePressed(): Unit = {
      entertainmentDevice.buttonFivePressed()
    }

    def buttonSixPressed(): Unit = {
      entertainmentDevice.buttonSixPressed()
    }

    def deviceFeedback(): Unit = {
      entertainmentDevice.deviceFeedback()
    }

    def buttonNinePressed(): Unit
  }

  class TVRemoteMute(entertainmentDevice: EntertainmentDevice) extends RemoteControl(entertainmentDevice) {
    def buttonNinePressed(): Unit = {
      println("TV was muted")
    }
  }

  class TVRemotePause(entertainmentDevice: EntertainmentDevice) extends RemoteControl(entertainmentDevice) {
    def buttonNinePressed(): Unit = {
      println("TV was paused")
    }
  }

  def main(args: Array[String]): Unit = {
    val tv1: RemoteControl = new TVRemoteMute(new TVDevice(1, 200))
    val tv2: RemoteControl = new TVRemotePause(new TVDevice(1, 200))
    //    val dvd : RemoteControl = new TVRemotePause(new DVDDevice(1, 14))

    println("Test tv with mute")
    tv1.deviceFeedback()
    tv1.buttonFivePressed()
    tv1.deviceFeedback()
    tv1.buttonSixPressed()
    tv1.deviceFeedback()
    tv1.buttonNinePressed()
    tv1.deviceFeedback()

    println("Test tv with pause")
    tv1.buttonFivePressed()
    tv1.buttonSixPressed()
    tv1.buttonNinePressed()

  }


}
