package hust.soict.ite6.oop.test.store;

import java.util.Arrays;

import hust.soict.ite6.oop.aims.model.media.Book;
import hust.soict.ite6.oop.aims.model.media.CompactDisc;
import hust.soict.ite6.oop.aims.model.media.DigitalVideoDisc;
import hust.soict.ite6.oop.aims.model.media.Track;
import hust.soict.ite6.oop.aims.model.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        // Create a store
        Store store = new Store();

        // Add DigitalVideoDisc items to the store
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Inception", "Sci-Fi", 20.0f, "Christopher Nolan", 148);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Avatar", "Fantasy", 25.0f, "James Cameron", 162);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Titanic", "Romance", 22.0f, "James Cameron", 195);

        store.addMedia(dvd1, dvd2, dvd3);

        // Add CompactDisc to the store
        CompactDisc cd = new CompactDisc("Thriller", "Pop", 15.0f, "Quincy Jones", "Michael Jackson", 0);
        cd.addTrack(new Track("Wanna Be Startin' Somethin'", 6));
        cd.addTrack(new Track("Thriller", 7));
        cd.setLength(cd.getLength());
        
        store.addMedia(cd);

        // Add Book to the store
        Book book = new Book("Effective Java", "Programming", 45.0f, Arrays.asList("Joshua Bloch"));
        store.addMedia(book);

        // Display all media in the store
        store.displayStore();

        // Remove a media item
        System.out.println("\nRemoving Titanic from the store:");
        store.removeMedia(dvd3);

        // Display the store after removal
        store.displayStore();

        // Attempt to remove a non-existent item
        System.out.println("\nAttempting to remove a non-existent item:");
        DigitalVideoDisc nonExistentDVD = new DigitalVideoDisc("Non-Existent", "Unknown", 0.0f, "Unknown", 0);
        store.removeMedia(nonExistentDVD);

        
    }
}
