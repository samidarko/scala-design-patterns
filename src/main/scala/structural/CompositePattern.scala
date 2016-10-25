package structural
import scala.collection.mutable.ArrayBuffer
/**
  * Created by samidarko on 10/25/16.
  * What is the Composite Pattern?
  * * Allows you to treat the individual objects and compositions of objects uniformly
  * * They allow you to represent part-whole hierarchies
  * * * Components can be further divided into smaller components
  * * You can structure data, or represent the inner working of every part of a whole object individually
  */
object CompositePattern {

  abstract class SongComponent {
    def add(songComponent: SongComponent) : Unit = throw new UnsupportedOperationException
    def remove(songComponent: SongComponent) : Unit = throw new UnsupportedOperationException
    def getComponent(componentIndex : Int) : SongComponent = throw new UnsupportedOperationException
    def getSongName: String = throw new UnsupportedOperationException
    def getBandName: String = throw new UnsupportedOperationException
    def getYear: Int = throw new UnsupportedOperationException
    def displaySongInfo(): Unit = throw new UnsupportedOperationException
  }

  class SongGroup(val name : String, val description : String) extends SongComponent {

    val songComponents : ArrayBuffer[SongComponent] = ArrayBuffer[SongComponent]()

    override def add(songComponent: SongComponent): Unit = songComponents += songComponent

    override def remove(songComponent: SongComponent): Unit = songComponent.remove(songComponent)

    override def getComponent(componentIndex: Int): SongComponent = songComponents(componentIndex)

    override def displaySongInfo(): Unit = {
      println(s"$name $description")
      for (song <- songComponents) {
        song.displaySongInfo()
      }
    }
  }

  class Song(val name : String, val band : String, val year : Int) extends SongComponent {
    override def displaySongInfo(): Unit = println(s"$name was recorded by $band in $year")
  }

  class DiscJockey(songComponent: SongComponent) {
    def getSongList() : Unit = songComponent.displaySongInfo()
  }

  def main(args: Array[String]): Unit = {
    val industrial : SongComponent = new SongGroup("Industrial", "Industrial description")
    val metal : SongComponent = new SongGroup("Metal", "Metal description")
    val techno : SongComponent = new SongGroup("Techno", "Techno description")
    val music : SongComponent = new SongGroup("Music", "All the musics")
    industrial.add(new Song("Head like a hole", "NIN", 1990))
    industrial.add(new Song("Hunter", "Front 242", 1988))
    industrial.add(metal)
    metal.add(new Song("War Pigs", "Black Sabath", 1970))
    metal.add(new Song("Ace of Spade", "Motorhead", 1980))
    music.add(techno)
    music.add(industrial)
    music.add(metal)

    val dj : DiscJockey = new DiscJockey(music)
    dj.getSongList()
  }
}
