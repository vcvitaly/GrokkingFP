package io.github.vcvitaly.grokkingfp
package ch6

import org.scalatest.EitherValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

/**
 * TvShowAttributesParserTest.
 *
 * @author Vitalii Chura
 */
class TvShowAttributesParserTest extends AnyFlatSpec with EitherValues with should.Matchers {

  val tvShowAttributesParser: TvShowAttributesParser = TvShowAttributesParser()

  "The extractYearStart" should "return Right for a correct input string with both start and end year" in {
    val res = tvShowAttributesParser.extractYearStart("The Wire (2002-2008)")
    res.value should be (2002)
  }

  it should "return Right for a correct input string with just a start year" in {
    val res = tvShowAttributesParser.extractYearStart("The Wire (2002-)")
    res.value should be(2002)
  }

  it should "return Left for a incorrect input string without a start year" in {
    val res = tvShowAttributesParser.extractYearStart("The Wire (-2008)")
    res.left.value should be("Can't extract start year from The Wire (-2008)")
  }

  it should "return Left for a incorrect input string with an unparsable start year" in {
    val res = tvShowAttributesParser.extractYearStart("The Wire (oops-2008)")
    res.left.value should be("Can't parse oops")
  }
}
