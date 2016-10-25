package behavioral
import scala.collection.Iterator

/**
  * Created by samidarko on 10/25/16.
  * What is the Iterator Pattern?
  * * The Iterator Pattern provides you with a uniform way to access different collections of Objects
  * * If you get an Array or Hashtable of Objects, you pop out an iterator for each and treat them the same
  * * This provides a uniform way to cycle through different collections
  * * You can also write polymorphic code because they';; implement the same interface
  */
object IteratorPattern {

  class Song(val name : String, val band : String, val year : Int)

  trait SongIterator {
    def createIterator : Iterator[Song]
  }

  class SongsOfThe70s extends SongIterator {
    val bestSongs : List[Song] = List(
      new Song("Imagine", "John Lennon", 1971),
      new Song("American Pie", "Don McLean", 1971),
      new Song("I will Survive", "Gloria Gaynor", 1979)
    )

    override def createIterator: Iterator[Song] = bestSongs.iterator
  }

  class SongsOfThe80s extends SongIterator {
    val bestSongs : Array[Song] = Array(
      new Song("Roam", "B 52s", 1989),
      new Song("Cruel Summer", "Banarama", 1984),
      new Song("Head Over Heels", "Tears For Fears", 1985)
    )

    override def createIterator: Iterator[Song] = bestSongs.iterator
  }

  class SongsOfThe90s extends SongIterator {
    val bestSongs : Map[Int, Song] = Map(
      1 -> new Song("Losing my Religion", "REM", 1991),
      2 -> new Song("Creep", "Radiohead", 1993),
      3 -> new Song("Walk on the Ocean", "Toad The Wet Sprocket", 1991)
    )

    override def createIterator: Iterator[Song] = bestSongs.values.iterator
  }

  class DiskJockey(song70s : SongIterator, song80s : SongIterator, song90s : SongIterator) {

    def showSongs() : Unit = {
      println("Songs of the 70s: ")
      printTheSongs(song70s.createIterator)
      println("Songs of the 80s: ")
      printTheSongs(song80s.createIterator)
      println("Songs of the 90s: ")
      printTheSongs(song90s.createIterator)
    }

    def printTheSongs(iterator: Iterator[Song]): Unit = {
      var song : Song = null
      while (iterator.hasNext) {
        song = iterator.next()
        println(s"${song.name} ${song.band} ${song.year}")
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val songsOfThe70s = new SongsOfThe70s
    val songsOfThe80s = new SongsOfThe80s
    val songsOfThe90s = new SongsOfThe90s
    val diskJockey = new DiskJockey(songsOfThe70s, songsOfThe80s, songsOfThe90s)
    diskJockey.showSongs()
  }

}
