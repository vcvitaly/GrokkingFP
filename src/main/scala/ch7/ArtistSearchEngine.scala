package io.github.vcvitaly.grokkingfp
package ch7

import ch7.model.SearchCondition.{SearchByActiveYears, SearchByGenre, SearchByNumberOfActiveYears, SearchByOrigin}
import ch7.model.YearsActive.{ActiveBetween, StillActive}
import ch7.model.{Artist, Location, MusicGenre, SearchCondition, YearsActive}

/**
 * ArtistSearchEngine.
 *
 * @author Vitalii Chura
 */
class ArtistSearchEngine(activeYearsCalculator: ActiveYearsCalculator) {

  def searchArtists(
                     artists: List[Artist],
                     requiredConditions: List[SearchCondition]
                   ): List[Artist] = {
    artists
      .filter(artist => requiredConditions.forall(condition =>
        condition match
          case SearchByGenre(genres) => genres.contains(artist.genre)
          case SearchByOrigin(locations) => locations.contains(artist.origin)
          case SearchByActiveYears(start, end) => wasArtistActive(artist, start, end)
          case SearchByNumberOfActiveYears(numberOfYearsActive, currentYear) =>
            activeYearsCalculator.numberOfYearsActive(artist.yearsActive, currentYear) >= numberOfYearsActive
      ))
  }

  private def wasArtistActive(artist: model.Artist, activeAfter: Int, activeBefore: Int): Boolean = {
    artist.yearsActive.exists(period => wasArtistActiveDuringAPeriod(period, activeAfter, activeBefore))
  }

  private def wasArtistActiveDuringAPeriod(period: YearsActive, activeAfter: Int, activeBefore: Int): Boolean = {
    val (yearsActiveStart, yearsActiveEnd) = period match
      case StillActive(since) => (since, 0)
      case ActiveBetween(start, end) => (start, end)

    (activeAfter <= yearsActiveStart && activeBefore >= yearsActiveStart) ||
      (activeAfter >= yearsActiveStart && (activeBefore <= yearsActiveEnd || yearsActiveEnd == 0)) ||
      (activeAfter <= yearsActiveEnd && activeBefore >= yearsActiveEnd)
  }
}

/*(activeAfter <= yearsActiveStart.value && activeBefore >= yearsActiveStart.value) ||
  (activeAfter >= yearsActiveStart.value && (activeBefore <= yearsActiveEnd.value || yearsActiveEnd.value == 0)) ||
  (activeAfter <= yearsActiveEnd.value && activeBefore >= yearsActiveEnd.value)*/
