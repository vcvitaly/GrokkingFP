package io.github.vcvitaly.grokkingfp
package ch6


/**
 * TvShowParser.
 *
 * @author Vitalii Chura
 */
class TvShowParser(attributesParser: TvShowAttributesParser) {
  
  def parseShows(rawShows: List[String]): Either[String, List[TvShow]] = {
    val initialResult: Either[String, List[TvShow]] = Right(List.empty)
    
    rawShows
      .map(parseShow)
      .foldLeft(initialResult)(addOrResign)
  }

  def parseShow(rawShow: String): Either[String, TvShow] = {
    for {
      name <- attributesParser.extractName(rawShow)
      yearStart <- attributesParser.extractYearStart(rawShow).orElse(attributesParser.extractSingleYear(rawShow))
      yearEnd <- attributesParser.extractYearEnd(rawShow).orElse(Right(yearStart))
    } yield TvShow(name, yearStart, yearEnd)
  }

  private def addOrResign(
                   parsedShows: Either[String, List[TvShow]],
                   newParsedShow: Either[String, TvShow]): Either[String, List[TvShow]] = {
    for {
      shows <- parsedShows
      parsedShow <- newParsedShow
    } yield shows.appended(parsedShow)
  }
}
