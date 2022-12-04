package aoc2022

import scala.io.Source

object Day04 extends App:

  val input = Source.fromResource("Day04.txt").getLines.toList

  val result = for {
    line      <- input
    sections   = line.split(",").toList
    left       = sections(0).split("-")
    leftRange  = (left(0).toInt to left(1).toInt)
    right      = sections(1).split("-")
    rightRange = (right(0).toInt to right(1).toInt)
  } yield leftRange.forall(rightRange.contains(_)) ||
    rightRange.forall(leftRange.contains(_))

  println(result.count(identity))

  val result2 = for {
    line      <- input
    sections   = line.split(",").toList
    left       = sections(0).split("-")
    leftRange  = (left(0).toInt to left(1).toInt)
    right      = sections(1).split("-")
    rightRange = (right(0).toInt to right(1).toInt)
  } yield leftRange.exists(rightRange.contains(_)) ||
    rightRange.exists(leftRange.contains(_))

  println(result2.count(identity))

end Day04
