package io.github.vcvitaly.grokkingfp
package ch7._261

import ch7.model.Location

/**
 * model.
 *
 * @author Vitalii Chura
 */
object model {

  enum PlaylistKind {
    case CuratedByUser(user: User)
    case ArtistBased(artist: Artist)
    case GenresBased(genres: Set[MusicGenre])
  }

  enum MusicGenre {
    case House
    case Funk
    case HipHop
  }

  opaque type User = String
  object User {
    def apply(value: String): User = value

    extension (a: User) def name: String = a
  }

  opaque type Artist = String
  object Artist {
    def apply(value: String): Artist = value

    extension (a: Artist) def name: String = a
  }

  case class Song(artist: Artist, name: String)

  case class Playlist(name: String, kind: PlaylistKind, songs: List[Song])
}
