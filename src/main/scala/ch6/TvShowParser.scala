package io.github.vcvitaly.grokkingfp
package ch6


/**
 * TvShowParser.
 *
 * @author Vitalii Chura
 */
class TvShowParser(attributesParser: TvShowAttributesParser) {

  def parseShow(rawShow: String): Either[String, TvShow] = {
    for {
      name <- attributesParser.extractName(rawShow)
      yearStart <- attributesParser.extractYearStart(rawShow).orElse(attributesParser.extractSingleYear(rawShow))
      yearEnd <- attributesParser.extractYearEnd(rawShow).orElse(Right(yearStart))
    } yield TvShow(name, yearStart, yearEnd)
  }
}
