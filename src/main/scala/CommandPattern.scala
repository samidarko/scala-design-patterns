/**
  * Created by samidarko on 10/19/16.
  */
object CommandPattern {

  trait ElectronicDevice {
    def on() : Unit
    def off() : Unit
    def volumeUp() : Unit
    def volumeDown() : Unit
  }

  class Television extends ElectronicDevice {
    var volume = 0
    override def on(): Unit = println("Tv is ON")

    override def off(): Unit = println("Tv is OFF")

    override def volumeUp(): Unit = {
      volume += 1
      println(s"Tv volume is $volume")
    }

    override def volumeDown(): Unit = {
      if (volume > 0) volume -= 1
      println(s"Tv volume is $volume")
    }
  }

  class Radio extends ElectronicDevice {
    var volume = 0
    override def on(): Unit = println("Radio is ON")

    override def off(): Unit = println("Radio is OFF")

    override def volumeUp(): Unit = {
      volume += 1
      println(s"Radio volume is $volume")
    }

    override def volumeDown(): Unit = {
      if (volume > 0) volume -= 1
      println(s"Radio volume is $volume")
    }
  }

  trait Command {
    def execute() : Unit
    def revert() : Unit
  }

  class TurnTvOn(electronicDevice: ElectronicDevice) extends Command {
    override def execute(): Unit = electronicDevice.on()
    override def revert(): Unit = electronicDevice.off()
  }

  class TurnTvOff(electronicDevice: ElectronicDevice) extends Command {
    override def execute(): Unit = electronicDevice.off()
    override def revert(): Unit = electronicDevice.on()
  }

  class TurnTvUp(electronicDevice: ElectronicDevice) extends Command {
    override def execute(): Unit = electronicDevice.volumeUp()
    override def revert(): Unit = electronicDevice.volumeDown()
  }

  // TurnTvDown...

  class DeviceButton(var command: Command) {
    def press() : Unit = command.execute()
    def pressRevert() : Unit = command.revert()
    def setCommand(command: Command) : Unit = this.command = command
  }

  class TurnItAllOff(electronicDevices: List[ElectronicDevice]) extends Command {
    override def execute(): Unit = electronicDevices.foreach(_.off())
    override def revert(): Unit = electronicDevices.foreach(_.on())
  }

  def main(args: Array[String]): Unit = {
    val television : ElectronicDevice = new Television
    val turnTvOn: TurnTvOn = new TurnTvOn(television)
    val deviceButton: DeviceButton = new DeviceButton(turnTvOn)
    deviceButton.press()
    deviceButton.pressRevert()
    val turnTvOff: TurnTvOff = new TurnTvOff(television)
    deviceButton.setCommand(turnTvOff)
    deviceButton.press()
    deviceButton.pressRevert()
    val radio : ElectronicDevice = new Radio
    val turnItAllOff = new TurnItAllOff(List(television, radio))
    deviceButton.setCommand(turnItAllOff)
    deviceButton.press()
    deviceButton.pressRevert()
  }

}
