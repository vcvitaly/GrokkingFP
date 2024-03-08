package io.github.vcvitaly.grokkingfp
package ch9

import ch9.model.Currency

/**
 * Exchanger.
 *
 * @author Vitalii Chura
 */
class Exchanger {

  def exchangeTable(from: Currency): IO[Map[Currency, BigDecimal]] = {
    ???
  }

  private def trending(rates: List[BigDecimal]): Boolean = {
    rates.size > 1 && rates.zip(rates.drop(1)).forall(ratePair => ratePair match {
      case (previousRate, rate) => rate > previousRate
    })
  }

  def extractSingleCurrencyRate(currencyToExtract: Currency)(table: Map[Currency, BigDecimal]): Option[BigDecimal] = {
    table.get(currencyToExtract)
  }

}
