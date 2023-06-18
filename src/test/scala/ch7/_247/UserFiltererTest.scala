package io.github.vcvitaly.grokkingfp
package ch7._247

import ch7._247.{User, UserFilterer}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks.forAll

import scala.collection.immutable.List

/**
 * UserFiltererTest.
 *
 * @author Vitalii Chura
 */
class UserFiltererTest extends AnyFlatSpec with should.Matchers {

  val userFilterer: UserFilterer = UserFilterer()

  val users: List[User] = List(
    User("Alice", Some("Melbourne"), List("Bee Gees")),
    User("Bob", Some("Lagos"), List("Bee Gees")),
    User("Eve", Some("Tokyo"), List.empty),
    User("Mallory", None, List("Metallica", "Bee Gees")),
    User("Trent", Some("Buenos Aires"), List("Led Zeppelin"))
  )

  "f1" should "return users that haven’t specified their city or live in Melbourne" in {
    val expectedUsers = List("Alice", "Mallory")
    userFilterer.f1(users).map(_.name) should be (expectedUsers)
  }

  "f2" should "return users that live in Lagos" in {
    val expectedUsers = List("Bob")
    userFilterer.f2(users).map(_.name) should be(expectedUsers)
  }

  "f3" should "return users that like Bee Gees" in {
    val expectedUsers = List("Alice", "Bob", "Mallory")
    userFilterer.f3(users).map(_.name) should be(expectedUsers)
  }

  "f4" should "return users that live in cities that start with the letter T" in {
    val expectedUsers = List("Eve")
    userFilterer.f4(users).map(_.name) should be(expectedUsers)
  }

  "f5" should "return users that only like artists that have a name longer than eight characters" +
    "(or no favorite artists at all)" in {
    val expectedUsers = List("Eve", "Trent")
    userFilterer.f5(users).map(_.name) should be(expectedUsers)
  }

  "f6" should "return users that like some artists whose names start with an M" in {
    val expectedUsers = List("Mallory")
    userFilterer.f6(users).map(_.name) should be(expectedUsers)
  }
}
