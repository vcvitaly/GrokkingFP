package io.github.vcvitaly.grokkingfp
package ch7

/**
 * Artist.
 *
 * @author Vitalii Chura
 */
case class Artist(
                   name: String,
                   genre: String,
                   origin: String,
                   yearsActiveStart: Int,
                   isActive: Boolean,
                   yearsActiveEnd: Int
                 )
