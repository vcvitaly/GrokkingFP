package io.github.vcvitaly.grokkingfp
package ch7

import ch7.model.MusicGenre.{HardRock, HeavyMetal, Pop}
import ch7.model.YearsActive.{ActiveBetween, StillActive}
import ch7.model.{Artist, Location}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks.forAll

/**
 * ActiveYearCalculatorTest.
 *
 * @author Vitalii Chura
 */
class ActiveYearsCalculatorTest extends AnyFlatSpec with should.Matchers with ScalaCheckPropertyChecks {

  private val calculator: ActiveYearsCalculator = ActiveYearsCalculator()

  "The numberOfYearsActive" should "calculate correctly the number of artist's active years" in {
    val params = Table(
      ("artist", "currentYear", "numberOfYearsActive"),
      (Artist("Metallica", HeavyMetal, Location("U.S."), List(StillActive(1981))), 2022, 41),
      (Artist("Led Zeppelin", HardRock, Location("England"), List(ActiveBetween(1968, 1980))), 2022, 12),
      (Artist("Bee Gees", Pop, Location("England"), List(ActiveBetween(1958, 2003), ActiveBetween(2009, 2012))), 2022, 48),
    )

    forAll(params) { (artist, currentYear, numberOfYearsActive) =>
      calculator.numberOfYearsActive(artist, currentYear) should be (numberOfYearsActive)
    }
  }
}
