package aoc2022

import scala.io.Source

object Day06 extends App:

  val input = Source.fromResource("Day06.txt").getLines.toList.head

  val result1 = for {
    (window, index) <- input.sliding(4).zipWithIndex.toList
    if window.distinct == window
  } yield window -> (index + 4)

  println(result1.head)

  val result2 = for {
    (window, index) <- input.sliding(14).zipWithIndex.toList
    if window.distinct == window
  } yield window -> (index + 14)

  println(result2.head)

end Day06
