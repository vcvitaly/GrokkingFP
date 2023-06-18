package io.github.vcvitaly.grokkingfp
package ch7

import ch7.model.MusicGenre.{HardRock, HeavyMetal, Pop}
import ch7.model.SearchCondition.{SearchByActiveYears, SearchByGenre, SearchByOrigin}
import ch7.model.YearsActive.{ActiveBetween, StillActive}
import ch7.model.{Artist, Location, SearchCondition}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

/**
 * ArtistSearchEngineTest.
 *
 * @author Vitalii Chura
 */
class ArtistSearchEngineTest extends AnyFlatSpec with should.Matchers {

  val artistSearchEngine: ArtistSearchEngine = ArtistSearchEngine()

  val artists: List[Artist] = List(
    Artist("Metallica", HeavyMetal, Location("U.S."), StillActive(1981)),
    Artist("Led Zeppelin", HardRock, Location("England"), ActiveBetween(1968, 1980)),
    Artist("Bee Gees", Pop, Location("England"), ActiveBetween(1958, 2003))
  )

  "The searchArtists" should "search by genre, origin, active years" in {
    val searchConditions = List(SearchByGenre(List(Pop)), SearchByOrigin(List(Location("England"))), SearchByActiveYears(1950, 2022))
    val result = List(Artist("Bee Gees", Pop, Location("England"), ActiveBetween(1958, 2003)))

    artistSearchEngine.searchArtists(artists, searchConditions) should be (result)
  }

  it should "search by origin, active years" in {
    val searchConditions = List(SearchByOrigin(List(Location("England"))), SearchByActiveYears(1950, 2022))
    val result = List(
      Artist("Led Zeppelin", HardRock, Location("England"), ActiveBetween(1968, 1980)),
      Artist("Bee Gees", Pop, Location("England"), ActiveBetween(1958, 2003))
    )

    artistSearchEngine.searchArtists(artists, searchConditions) should be (result)
  }

  it should "search by active years 1" in {
    val searchConditions = List(SearchByActiveYears(1950, 1979))
    val result = List(
      Artist("Led Zeppelin", HardRock, Location("England"), ActiveBetween(1968, 1980)),
      Artist("Bee Gees", Pop, Location("England"), ActiveBetween(1958, 2003))
    )

    artistSearchEngine.searchArtists(artists, searchConditions) should be (result)
  }

  it should "search by active years 2" in {
    val searchConditions = List(SearchByActiveYears(1981, 1984))
    val result = List(
      Artist("Metallica", HeavyMetal, Location("U.S."), StillActive(1981)),
      Artist("Bee Gees", Pop, Location("England"), ActiveBetween(1958, 2003))
    )

    artistSearchEngine.searchArtists(artists, searchConditions) should be(result)
  }

  it should "search by genre and active years" in {
    val searchConditions = List(SearchByGenre(List(HeavyMetal)), SearchByActiveYears(2019, 2022))
    val result = List(
      Artist("Metallica", HeavyMetal, Location("U.S."), StillActive(1981)),
    )

    artistSearchEngine.searchArtists(artists, searchConditions) should be(result)
  }

  it should "search by origin and active years" in {
    val searchConditions = List(SearchByOrigin(List(Location("U.S."))), SearchByActiveYears(1950, 1959))
    val result = List.empty

    artistSearchEngine.searchArtists(artists, searchConditions) should be(result)
  }

  it should "search by empty condition list" in {
    val searchConditions = List.empty
    val result = List(
      Artist("Metallica", HeavyMetal, Location("U.S."), StillActive(1981)),
      Artist("Led Zeppelin", HardRock, Location("England"), ActiveBetween(1968, 1980)),
      Artist("Bee Gees", Pop, Location("England"), ActiveBetween(1958, 2003))
    )

    artistSearchEngine.searchArtists(artists, searchConditions) should be(result)
  }
}
