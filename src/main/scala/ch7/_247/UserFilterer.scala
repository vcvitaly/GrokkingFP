package io.github.vcvitaly.grokkingfp
package ch7._247


/**
 * UserFilterer.
 *
 * @author Vitalii Chura
 */
class UserFilterer {

  def f1(users: List[User]): List[User] = {
    users
      .filter(u => u.city.forall(_ == "Melbourne"))
  }

  def f2(users: List[User]): List[User] = {
    users
      .filter(u => u.city.contains("Lagos"))
  }

  def f3(users: List[User]): List[User] = {
    users
      .filter(u => u.favoriteArtists.contains("Bee Gees"))
  }

  def f4(users: List[User]): List[User] = {
    users
      .filter(u => u.city.exists(_.startsWith("T")))
  }

  def f5(users: List[User]): List[User] = {
    users
      .filter(u => u.favoriteArtists.forall(_.length > 8))
  }

  def f6(users: List[User]): List[User] = {
    users
      .filter(u => u.favoriteArtists.exists(_.startsWith("M")))
  }
}
