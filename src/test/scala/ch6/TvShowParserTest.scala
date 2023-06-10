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
      ("input", "expectedRes"),
      ("The Wire (-)", "Can't extract single year from The Wire (-)"),
      ("The Wire (oops)", "Can't parse oops"),
      ("(2002-2008)", "Can't extract name from (2002-2008)")
    )
    forAll(params) { (input: String, expectedRes: String) =>
      tvShowParser.parseShow(input).left.value should be (expectedRes)
    }
  }

  "The parseShow" should "return a Right(TvShow) for a correct input string " in {
    val params = Table(
      ("input", "expectedRes"),
      ("The Wire (2002-2008)", TvShow("The Wire", 2002, 2008)),
      ("Chernobyl (2019)", TvShow("Chernobyl", 2019, 2019)),
    )
    forAll(params) { (input: String, expectedRes: TvShow) =>
      tvShowParser.parseShow(input).value should be (expectedRes)
    }
  }
}
