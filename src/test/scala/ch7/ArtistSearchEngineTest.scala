package io.github.vcvitaly.grokkingfp
package ch7

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

/**
 * ArtistSearchEngineTest.
 *
 * @author Vitalii Chura
 */
class ArtistSearchEngineTest extends AnyFlatSpec with should.Matchers with ScalaCheckPropertyChecks {

  val artistSearchEngine: ArtistSearchEngine = ArtistSearchEngine()

  "The searchArtists" should "return the list of artists accordingly to search criteria" in {
    val artists = List(
      Artist("Metallica", "Heavy Metal", "U.S.", 1981, true, 0),
      Artist("Led Zeppelin", "Hard Rock", "England", 1968, false, 1980),
      Artist("Bee Gees", "Pop", "England", 1958, false, 2003)
    )
    val params = Table(
      ("artists", "genres", "locations", "searchByActiveYears", "activeAfter", "activeBefore", "result"),
      (
        artists, List("Pop"), List("England"), true, 1950, 2022,
        List(Artist("Bee Gees", "Pop", "England", 1958, false, 2003))
      ),
      (
        artists, List.empty, List("England"), true, 1950, 2022,
        List(
          Artist("Led Zeppelin", "Hard Rock", "England", 1968, false, 1980),
          Artist("Bee Gees", "Pop", "England", 1958, false, 2003)
        )
      ),
      (
        artists, List.empty, List.empty, true, 1950, 1979,
        List(
          Artist("Led Zeppelin", "Hard Rock", "England", 1968, false, 1980),
          Artist("Bee Gees", "Pop", "England", 1958, false, 2003)
        )
      ),
      (
        artists, List.empty, List.empty, true, 1981, 1984,
        List(
          Artist("Metallica", "Heavy Metal", "U.S.", 1981, true, 0),
          Artist("Bee Gees", "Pop", "England", 1958, false, 2003)
        )
      ),
      (
        artists, List("Heavy Metal"), List.empty, true, 2019, 2022,
        List(
          Artist("Metallica", "Heavy Metal", "U.S.", 1981, true, 0),
        )
      ),
      (
        artists, List.empty, List("U.S."), true, 1950, 1959,
        List.empty
      ),
      (
        artists, List.empty, List.empty, false, 2019, 2022,
        List(
          Artist("Metallica", "Heavy Metal", "U.S.", 1981, true, 0),
          Artist("Led Zeppelin", "Hard Rock", "England", 1968, false, 1980),
          Artist("Bee Gees", "Pop", "England", 1958, false, 2003)
        )
      )
    )

    forAll(params) { (artists, genres, locations, searchByActiveYears, activeAfter, activeBefore, result) =>
      artistSearchEngine.searchArtists(
        artists, genres, locations, searchByActiveYears, activeAfter, activeBefore
      ) should be (result)
    }
  }
}
