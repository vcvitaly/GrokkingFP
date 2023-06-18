package io.github.vcvitaly.grokkingfp
package ch7._261

import ch7._261.model.PlaylistKind.{ArtistBased, CuratedByUser, GenresBased}
import ch7._261.model.{Artist, MusicGenre, Playlist, Song}

/**
 * PlaylistDesigner.
 *
 * @author Vitalii Chura
 */
class PlaylistDesigner {

  def gatherSongs(playlists: List[Playlist], artist: Artist, genre: MusicGenre): List[Song] = {
    playlists.foldLeft(List.empty[Song])((songs, playlist) =>
      val matchingSongs = playlist.kind match
        case CuratedByUser(user) => playlist.songs.filter(_.artist == artist)
        case ArtistBased(playlistArtist) => if (playlistArtist == artist) playlist.songs else List.empty
        case GenresBased(genres) => if (genres.contains(genre)) playlist.songs else List.empty

      songs.appendedAll(matchingSongs)
    )
  }
}
