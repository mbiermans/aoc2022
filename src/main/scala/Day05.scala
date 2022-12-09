package aoc2022

import scala.io.Source
import scala.collection.mutable.Queue
import scala.util.matching.Regex

object Day05 extends App:

  val input = Source.fromResource("Day05.txt").getLines.toList

  val stacksIndex = input.indexWhere(_.contains(" 1   2   3"))

  val stacks: List[(Int, Int)] = for {
    i     <- input(stacksIndex).indices.toList
    number = input(stacksIndex)(i) if Character.isDigit(number)
  } yield i -> s"$number".toInt

  val start = (for {
    (index, stack) <- stacks
    current         = for {
                        line <- input.take(stacksIndex)
                        char  = line(index)
                      } yield char.toString.trim
  } yield stack -> current.filter(_.nonEmpty)).toMap

  val commandRegex = "move (\\d+) from (\\d+) to (\\d+)".r

  val commands = for {
    line <- input.drop(stacksIndex + 2)
    tuple = line match {
              case commandRegex(am, fr, t) =>
                (am.toInt, fr.toInt, t.toInt)
            }
  } yield tuple

  val res = commands.foldLeft(start) { case (accum, (amount, from, to)) =>
    val updatedFrom = accum(from).drop(amount)
    val updatedTo   = accum(from).take(amount).reverse ++ accum(to)
    accum ++ Map(from -> updatedFrom, to -> updatedTo)
  }

  val result = (for {
    (x, list) <- res.toList.sortBy(_._1)
  } yield list(0)).mkString("")

  println(result)

  val res2 = commands.foldLeft(start) { case (accum, (amount, from, to)) =>
    val updatedFrom = accum(from).drop(amount)
    val updatedTo   = accum(from).take(amount) ++ accum(to)

    accum ++ Map(from -> updatedFrom, to -> updatedTo)

  }

  val result2 = (for {
    (x, list) <- res2.toList.sortBy(_._1)
  } yield list(0)).mkString("")

  println(result2)

end Day05
