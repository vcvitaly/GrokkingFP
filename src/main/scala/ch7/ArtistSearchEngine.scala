package io.github.vcvitaly.grokkingfp
package ch7

import ch7.model.{Artist, Location, MusicGenre, YearsActive}

import io.github.vcvitaly.grokkingfp.ch7.model.YearsActive.{ActiveBetween, StillActive}

/**
 * ArtistSearchEngine.
 *
 * @author Vitalii Chura
 */
class ArtistSearchEngine {

  def searchArtists(
                     artists: List[Artist],
                     genres: List[MusicGenre],
                     locations: List[String],
                     searchByActiveYears: Boolean,
                     activeAfter: Int,
                     activeBefore: Int
                   ): List[Artist] = {
    artists
      .view
      .filter(a => filterByGenres(a.genre, genres))
      .filter(a => filterByLocations(a.origin, locations))
      .filter(a => !searchByActiveYears || filterByYearsActive(a.yearsActive, activeAfter, activeBefore))
      .to(List)
  }

  private def filterByGenres(genre: MusicGenre, expectedGenres: List[MusicGenre]): Boolean = {
    expectedGenres.isEmpty || expectedGenres.contains(genre)
  }

  private def filterByLocations(location: Location, expectedLocations: List[String]): Boolean = {
    expectedLocations.isEmpty || expectedLocations.contains(location.name)
  }

  private def filterByYearsActive(yearsActive: YearsActive,
                                  activeAfter: Int,
                                  activeBefore: Int): Boolean = {
    yearsActive match
      case StillActive(since) => since <= activeBefore
      case ActiveBetween(start, end) => start <= activeAfter && end >= activeBefore
  }
}

/*(activeAfter <= yearsActiveStart.value && activeBefore >= yearsActiveStart.value) ||
  (activeAfter >= yearsActiveStart.value && (activeBefore <= yearsActiveEnd.value || yearsActiveEnd.value == 0)) ||
  (activeAfter <= yearsActiveEnd.value && activeBefore >= yearsActiveEnd.value)*/
