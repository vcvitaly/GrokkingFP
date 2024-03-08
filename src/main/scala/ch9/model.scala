package io.github.vcvitaly.grokkingfp
package ch9

/**
 * model.
 *
 * @author Vitalii Chura
 */
object model {
  
  opaque type Currency = String
  object Currency {
    def apply(name: String): Currency = name
    extension (currency: Currency) def name: String = currency
  }

}
