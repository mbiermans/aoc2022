package aoc2022

import scala.io.Source

enum MatchResult(val score: Int):

  case Loss extends MatchResult(0)
  case Draw extends MatchResult(3)
  case Win extends MatchResult(6)

  def calcMyHand(their: Hand): Hand = {
    this match {
      case Draw => their
      case Win  => Hand.fromScore(their.score + 1)
      case Loss => Hand.fromScore(their.score - 1)
    }

  }

end MatchResult

object Hand:

  def fromScore(score: Int): Hand = {
    score match {
      case 0 => Scissors
      case 1 => Rock
      case 2 => Paper
      case 3 => Scissors
      case 4 => Rock
    }
  }

end Hand

enum Hand(val score: Int):

  case Rock extends Hand(1)
  case Paper extends Hand(2)
  case Scissors extends Hand(3)

  def beats(other: Hand): MatchResult = {
    (this, other) match {
      case (Rock, Scissors)         => MatchResult.Win
      case (Paper, Rock)            => MatchResult.Win
      case (Scissors, Paper)        => MatchResult.Win
      case (me, them) if me == them => MatchResult.Draw
      case _                        => MatchResult.Loss
    }
  }

end Hand

object Day02 extends App:

  import MatchResult._
  import Hand._

  val input = Source.fromResource("Day02.txt").getLines.toList

  val handMappingThem = Map(
    "A" -> Rock,
    "B" -> Paper,
    "C" -> Scissors
  )

  val handMappingMe = Map(
    "X" -> Rock,
    "Y" -> Paper,
    "Z" -> Scissors
  )

  val scores = for {
    line     <- input
    list      = line.split(" ").toList
    theirHand = handMappingThem(list(0))
    myHand    = handMappingMe(list(1))
  } yield myHand.score + (myHand beats theirHand).score

  println(scores.sum)

  val resultMapping = Map(
    "X" -> Loss,
    "Y" -> Draw,
    "Z" -> Win
  )

  val scores2 = for {
    line     <- input
    list      = line.split(" ").toList
    theirHand = handMappingThem(list(0))
    result    = resultMapping(list(1))
    myHand    = result.calcMyHand(theirHand)
  } yield result.score + myHand.score

  println(scores2.sum)

end Day02
