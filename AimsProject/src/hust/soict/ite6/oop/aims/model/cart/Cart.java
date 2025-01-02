package hust.soict.ite6.oop.aims.model.cart;

import java.util.ArrayList;
import java.util.Collections;

import hust.soict.ite6.oop.aims.exception.CartFullException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInCartException;
import hust.soict.ite6.oop.aims.exception.MediaNotFoundException;
import hust.soict.ite6.oop.aims.model.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
	public static final int MAX_NUBMERS_ORDERED = 20; // mamximum number of media in a cart
	ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
	// (list of media )array
	

	// add 1 media into cart
	public void addMedia(Media media) throws CartFullException, MediaAlreadyInCartException {
	    System.out.println("***********************CART ADD***********************");
	    if (itemsOrdered.size() >= MAX_NUBMERS_ORDERED) {
	        throw new CartFullException("The cart is full, cannot add more media.");
	    }

	    if (itemsOrdered.contains(media)) {
	        throw new MediaAlreadyInCartException("The media " + media.getTitle() + " is already in the cart.");
	    }

	    itemsOrdered.add(media);
	    String simpleClassName = media.getClass().getSimpleName();
	    String displayClassName = simpleClassName.equalsIgnoreCase("dvd") ? "DVD" : 
	            simpleClassName.equalsIgnoreCase("cd") ? "CD" : 
	                simpleClassName.equalsIgnoreCase("book") ? "Book" :" ";
	    
	    System.out.println("The " + displayClassName + " " + media.getTitle() + " has been added into cart");
	}

	// add more than 1 media into cart
	public void addMedia(Media... medias) {
		System.out.println("***********************CART ADD***********************");
		// if quantity is not max yet, then still can add
		for (Media media : medias) {
			if (itemsOrdered.size() < MAX_NUBMERS_ORDERED && !itemsOrdered.contains(media)) {
				itemsOrdered.add(media);
				String simpleClassName = media.getClass().getSimpleName();
				String displayClassName = null;
				if (simpleClassName.equalsIgnoreCase("DigitalVideoDisc")) displayClassName = "DVD";
				if (simpleClassName.equalsIgnoreCase("CompactDisc")) displayClassName = "CD";
				if (simpleClassName.equalsIgnoreCase("Book")) displayClassName = "Book";
				
				System.out.println("The "+displayClassName+" " + media.getTitle() + " has been added into cart");
			} else {
				System.out.println("The cart is full or is already added, can't add " + media.getTitle());
			}
		}
	}

	public void removeMedia(Media media) throws MediaNotFoundException {
	    System.out.println("***********************CART REMOVE***********************");
	    if (!itemsOrdered.contains(media)) {
	        throw new MediaNotFoundException("The media " + media.getTitle() + " is not in the cart.");
	    }

	    itemsOrdered.remove(media);
	    String simpleClassName = media.getClass().getSimpleName();
	    String displayClassName = simpleClassName.equalsIgnoreCase("DigitalVideoDisc") ? "DVD" :
	            simpleClassName.equalsIgnoreCase("CompactDisc") ? "CD" : 
	                simpleClassName.equalsIgnoreCase("Book") ? "Book" : " ";

	    System.out.println("The " + displayClassName + " " + media.getTitle() + " has been removed.");
	}

	// calculate total cost
	public float totalCost() {
		float totalCost = 0;
		for (Media media : itemsOrdered) {
			totalCost += media.getCost();
		}
		return totalCost;
	}

	public void displayCart() {
		System.out.println("***********************CART***********************");
		for (Media media : itemsOrdered) {
			media.displayDetails();
			System.out.println();
		}
		System.out.println("Total cost: " + totalCost());
	}

	public Media search(int id) throws MediaNotFoundException {
	    System.out.println("**********************CART SEARCH************************");
	    for (Media media : itemsOrdered) {
	        if (media.getId() == id) {
	            media.displayDetails();
	            return media;
	        }
	    }
	    throw new MediaNotFoundException("No media found with ID = " + id);
	}

	public Media search(String title) throws MediaNotFoundException {
	    System.out.println("**********************CART SEARCH************************");
	    for (Media media : itemsOrdered) {
	        if (media.getTitle().equalsIgnoreCase(title)) {
	            media.displayDetails();
	            return media;
	        }
	    }
	    throw new MediaNotFoundException("No media found with title = " + title);
	}
	
	public void displayCartSortedByTitleThenCost() {
		System.out.println("***********************CART SORTED BY TITLE-COST***********************");
		Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
		for (Media media : itemsOrdered) {
			System.out.println(media.toString());
		}
	}
	
	public void displayCartSortedByCostThenTitle() {
		System.out.println("***********************CART SORTED BY COST-TITLE***********************");
		Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
		for (Media media:itemsOrdered) {
			System.out.println(media.toString());
		}
	}
	
	public void clear() {
		System.out.println("***********************CART CLEAR***********************");
		itemsOrdered.clear();
	}

	public ObservableList<Media> getItemsOrdered() {
	    
	    return itemsOrdered;
	}
}
