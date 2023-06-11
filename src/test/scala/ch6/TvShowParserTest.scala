package io.github.vcvitaly.grokkingfp
package ch6

import org.scalatest.EitherValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

/**
 * TvShowParserTest.
 *
 * @author Vitalii Chura
 */
class TvShowParserTest extends AnyFlatSpec with EitherValues with should.Matchers with ScalaCheckPropertyChecks {

  val tvShowParser: TvShowParser = TvShowParser(TvShowAttributesParser())

  "The parseShow" should "return Left for an incorrect input string" in {
    val params = Table(
      ("rawShow", "expectedRes"),
      ("The Wire (-)", "Can't extract single year from The Wire (-)"),
      ("The Wire (oops)", "Can't parse oops"),
      ("(2002-2008)", "Can't extract name from (2002-2008)")
    )
    forAll(params) { (rawShow: String, expectedRes: String) =>
      tvShowParser.parseShow(rawShow).left.value should be (expectedRes)
    }
  }

  it should "return a Right(TvShow) for a correct input string " in {
    val params = Table(
      ("rawShow", "expectedRes"),
      ("The Wire (2002-2008)", TvShow("The Wire", 2002, 2008)),
      ("Chernobyl (2019)", TvShow("Chernobyl", 2019, 2019)),
    )
    forAll(params) { (rawShow: String, expectedRes: TvShow) =>
      tvShowParser.parseShow(rawShow).value should be (expectedRes)
    }
  }

  "The parseShows" should "return Left for a list of raw shows given that one of them is incorrectly formatted" in {
    val params = Table(
      ("rawShows", "expectedRes"),
      (List("The Wire (2002-2008)", "[2019]"), "Can't extract name from [2019]"),
      (List("The Wire (-)", "Chernobyl (2019)"), "Can't extract single year from The Wire (-)")
    )
    forAll(params) { (rawShows: List[String], expectedRes: String) =>
      tvShowParser.parseShows(rawShows).left.value should be(expectedRes)
    }
  }

  it should "return Right(List[TvShow]) for a list of correct raw shows" in {
    val rawShows = List("The Wire (2002-2008)", "Chernobyl (2019)")
    val resEither = tvShowParser.parseShows(rawShows)
    val expectedRes = List(TvShow("The Wire", 2002, 2008), TvShow("Chernobyl", 2019, 2019))
    resEither.value should be (expectedRes)
  }
}
