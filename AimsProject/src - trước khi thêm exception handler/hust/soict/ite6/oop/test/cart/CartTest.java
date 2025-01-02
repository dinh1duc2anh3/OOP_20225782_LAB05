package hust.soict.ite6.oop.test.cart;

import java.util.Arrays;

import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.Book;
import hust.soict.ite6.oop.aims.model.media.CompactDisc;
import hust.soict.ite6.oop.aims.model.media.DigitalVideoDisc;
import hust.soict.ite6.oop.aims.model.media.Track;

public class CartTest {
    public static void main(String[] args) {
        // Create a Cart object
        Cart cart = new Cart();
        
        // Thêm CD vào Cart	
        CompactDisc cd = new CompactDisc("Thriller", "Pop", 15.0f, "Michael Jackson","Quincy Jones", 0);
        cd.addTrack(new Track("Wanna Be Startin' Somethin'", 6));
        cd.addTrack(new Track("Thriller", 7));
        cd.addTrack(new Track("Beat It", 5));
        cd.setLength(cd.getLength());
        
        // Thêm book vào Cart
        Book book = new Book("Effective Java", "Programming", 45.0f, Arrays.asList("Joshua Bloch"));
        
        // Create some DigitalVideoDisc objects
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Inception", "Sci-Fi", 20.0f, "Christopher Nolan", 148);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Avatar", "Fantasy", 25.0f, "James Cameron", 162);
        
        
        cart.addMedia(book,dvd1,dvd2);
        cart.addMedia(cd);
        cart.displayCart();
        
        cart.removeMedia(dvd2);
        cart.displayCart();

        cart.search("inception");  // Existing title
        cart.search("avengers");      // Non-existing title


        cart.search(dvd1.getId());  // Existing ID
        cart.search(999);           // Non-existing ID

        // Test total cost
        System.out.println("\nTotal cost of DVDs in the cart: " + cart.totalCost());

    }
}
