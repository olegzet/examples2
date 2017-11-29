package com.olegzet.hibernate;


import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by oleg.zorin on 28.11.2017.
 */
public class ArtistService {

    private Session session;

    public ArtistService(Session session) {
        this.session = session;
    }

    public Artist createArtist(int id, String name, String genre) {
        Artist artist = new Artist(id, name, genre);
        session.save(artist);
        return artist;
    }

    public void removeArtist(int id) {
        Artist artist = session.get(Artist.class, id);

        if (artist != null) {
            session.delete(artist);
        }
    }

    public Artist changeArtistGenre(int id, String genre) {
        Artist artist = session.get(Artist.class, id);

        if (artist != null) {
            artist.setGenre(genre);
        }

        return artist;
    }

    public Artist findArtist(int id) {
        return session.get(Artist.class, id);
    }

    public List<Artist> findAllArtists() {
        Query query = session.createQuery("SELECT A FROM Artist A");
        return query.list();
    }
}
