import scala.io.Source

enum MatchResult(val score: Int):

  case Loss extends MatchResult(0)
  case Draw extends MatchResult(3)
  case Win extends MatchResult(6)

end MatchResult

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

  val scores = Map(
    "A" -> Rock,
    "B" -> Paper,
    "C" -> Scissors,
    "X" -> Rock,
    "Y" -> Paper,
    "Z" -> Scissors
  )

  val tupled = for {
    line     <- input
    list      = line.split(" ").toList
    myHand    = scores(list(0))
    theirHand = scores(list(1))
  } yield (myHand, theirHand, (myHand beats theirHand))

  println(tupled.map(x => x._1.score + x._2.score).sum)

end Day02
