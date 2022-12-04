package aoc2022

import scala.io.Source

object Day03 extends App:

  val input = Source.fromResource("Day03.txt").getLines.toList

  val mapping: Map[String, Int] =
    (('a' to 'z') ++ ('A' to 'Z')).toList
      .zip((1 to 52).toList)
      .map(x => (x._1.toString, x._2))
      .toMap

  val result = for {
    line  <- input
    first  = line.take(line.size / 2)
    second = line.drop(line.size / 2)
    common = first.intersect(second).distinct
  } yield mapping(common)

  println(result.sum)

  val result2 = for {
    index                 <- input.indices by 3
    (first, second, third) = (input(index), input(index + 1), input(index + 2))
    common                 = first.intersect(second).intersect(third).distinct
  } yield mapping(common)

  println(result2.sum)

end Day03
