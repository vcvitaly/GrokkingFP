package io.github.vcvitaly.grokkingfp
package ch7

import ch7.model.MusicGenre.{HardRock, HeavyMetal, Pop}
import ch7.model.SearchCondition.{SearchByActiveYears, SearchByGenre, SearchByOrigin}
import ch7.model.YearsActive.{ActiveBetween, StillActive}
import ch7.model.{Artist, Location, SearchCondition}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

/**
 * ArtistSearchEngineTest.
 *
 * @author Vitalii Chura
 */
class ArtistSearchEngineTest extends AnyFunSuite with should.Matchers {

  val artistSearchEngine: ArtistSearchEngine = ArtistSearchEngine()

  val artists: List[Artist] = List(
    Artist("Metallica", HeavyMetal, Location("U.S."), List(StillActive(1981))),
    Artist("Led Zeppelin", HardRock, Location("England"), List(ActiveBetween(1968, 1980))),
    Artist("Bee Gees", Pop, Location("England"), List(ActiveBetween(1958, 2003), ActiveBetween(2009, 2012)))
  )

  test("search by genre, origin, active years"){
    val searchConditions = List(SearchByGenre(List(Pop)), SearchByOrigin(List(Location("England"))), SearchByActiveYears(1950, 2022))
    val result = List("Bee Gees")

    artistSearchEngine.searchArtists(artists, searchConditions).map(_.name) should be (result)
  }

  test("search by origin, active years"){
    val searchConditions = List(SearchByOrigin(List(Location("England"))), SearchByActiveYears(1950, 2022))
    val result = List("Led Zeppelin", "Bee Gees")

    artistSearchEngine.searchArtists(artists, searchConditions).map(_.name) should be (result)
  }

  test("search by active years 1") {
    val searchConditions = List(SearchByActiveYears(1950, 1979))
    val result = List("Led Zeppelin", "Bee Gees")

    artistSearchEngine.searchArtists(artists, searchConditions).map(_.name) should be (result)
  }

  test("search by active years 2") {
    val searchConditions = List(SearchByActiveYears(1981, 1984))
    val result = List("Metallica", "Bee Gees")

    artistSearchEngine.searchArtists(artists, searchConditions).map(_.name) should be(result)
  }

  test("search by genre and active years") {
    val searchConditions = List(SearchByGenre(List(HeavyMetal)), SearchByActiveYears(2019, 2022))
    val result = List("Metallica")

    artistSearchEngine.searchArtists(artists, searchConditions).map(_.name) should be(result)
  }

  test("search by origin and active years") {
    val searchConditions = List(SearchByOrigin(List(Location("U.S."))), SearchByActiveYears(1950, 1959))
    val result = List.empty

    artistSearchEngine.searchArtists(artists, searchConditions).map(_.name) should be(result)
  }

  test("search by empty condition list") {
    val searchConditions = List.empty
    val result = List("Metallica", "Led Zeppelin", "Bee Gees")

    artistSearchEngine.searchArtists(artists, searchConditions).map(_.name) should be(result)
  }

  test("search by active years given that one of the artists has multiple activity periods") {
    val searchConditions = List(SearchByActiveYears(2010, 2011))
    val result = List("Metallica", "Bee Gees")

    artistSearchEngine.searchArtists(artists, searchConditions).map(_.name) should be(result)
  }
}
