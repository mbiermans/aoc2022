import scala.io.Source
import scala.util.Try

object Day01 extends App:

  println("test")

  val input = Source.fromResource("Day01.txt").mkString("")

  val elves = for {
    group   <- input.split("\n\n").toList
    line     = group.split("\n").toList
    calories = line.map(x => Try(x.toInt).getOrElse(0))
  } yield calories.sum

  val result1 = elves.sorted.reverse.take(1).sum

  println(result1)

  val result2 = elves.sorted.reverse.take(3).sum
  println(result2)

end Day01
