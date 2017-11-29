package com.olegzet.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by oleg.zorin on 28.11.2017.
 */
public class HibernateBasicsTutorial {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            ArtistService service = new ArtistService(session);

            System.out.println("--- Create and persist artist ---");
            Transaction transaction = session.beginTransaction();
            Artist artist = service.createArtist(1, "Franz Ferdinand", "Rock");
            transaction.commit();
            System.out.println(String.format("Persisted: %s\n", artist));

            System.out.println("--- Find artist ---");
            artist = service.findArtist(1);
            System.out.println(String.format("Found: %s\n", artist));

            System.out.println("--- Find all artists ---");
            List<Artist> artists = service.findAllArtists();
            for (Artist foundArtist : artists) {
                System.out.println(String.format("Found: %s\n", foundArtist));
            }

            System.out.println("--- Update artist ---");
            transaction = session.beginTransaction();
            artist = service.changeArtistGenre(1, "Indie Rock");
            transaction.commit();
            System.out.println(String.format("Updated: %s\n", artist));

            System.out.println("--- Remove artist ---");
            transaction = session.beginTransaction();
            service.removeArtist(1);
            transaction.commit();
            artist = service.findArtist(1);
            System.out.println(String.format("Found: %s\n", artist));
        }
    }
}
