package hust.soict.ite6.oop.aims.model.store;

import java.util.ArrayList;
import java.util.Collections;

import hust.soict.ite6.oop.aims.exception.MediaAlreadyInStoreException;
import hust.soict.ite6.oop.aims.exception.MediaNotFoundException;
import hust.soict.ite6.oop.aims.exception.StoreFullException;
import hust.soict.ite6.oop.aims.model.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store {
	public static final int MAX_NUBMERS_STORED = 100;
	private ObservableList<Media> itemsInStore = FXCollections.observableArrayList();;
//	ArrayList<Media> itemsInStore = new ArrayList<>();
	public static int nbMedia = 0;

	// note qtyStored là thừa vì size của itemsInStore.size() là được, và cấu trúc
	// ArrayList sẽ tự độgn resizes khi đạt max

//	int qtyStored = 0;

	public void addMedia(Media... medias) throws StoreFullException, MediaAlreadyInStoreException {
	    System.out.println("***********************STORE ADD***********************");
	    for (Media media : medias) {
	        if (itemsInStore.size() >= MAX_NUBMERS_STORED) {
	            throw new StoreFullException("The store is full, can't add " + media.getTitle());
	        }

	        if (itemsInStore.contains(media)) {
	            throw new MediaAlreadyInStoreException("The media " + media.getTitle() + " is already in the store.");
	        }

	        itemsInStore.add(media);
	        String simpleClassName = media.getClass().getSimpleName();
	        String displayClassName = null;
	        if (simpleClassName.equalsIgnoreCase("DigitalVideoDisc")) displayClassName = "DVD";
	        if (simpleClassName.equalsIgnoreCase("CompactDisc")) displayClassName = "CD";
	        if (simpleClassName.equalsIgnoreCase("Book")) displayClassName = "Book";

	        System.out.println("The " + displayClassName + " " + media.getTitle() + " with Id=" + media.getId() + " has been added into the store");
	    }
	}
	
	

	public void removeMedia(Media media) throws MediaNotFoundException {
	    System.out.println("***********************STORE REMOVE***********************");
	    if (!itemsInStore.contains(media)) {
	        throw new MediaNotFoundException("The media " + media.getTitle() + " is not in the store.");
	    }

	    itemsInStore.remove(media);
	    String simpleClassName = media.getClass().getSimpleName();
	    String displayClassName = null;
	    if (simpleClassName.equalsIgnoreCase("DigitalVideoDisc")) displayClassName = "DVD";
	    if (simpleClassName.equalsIgnoreCase("CompactDisc")) displayClassName = "CD";
	    if (simpleClassName.equalsIgnoreCase("Book")) displayClassName = "Book";

	    System.out.println("The " + displayClassName + " " + media.getTitle() + " with Id=" + media.getId() + " has been removed");
	}

	
	
	public Media search(int id) throws MediaNotFoundException {
	    System.out.println("**********************STORE SEARCH************************");
	    for (Media media : itemsInStore) {
	        if (media.getId() == id) {
	            media.displayDetails();
	            return media;
	        }
	    }
	    throw new MediaNotFoundException("No media found with ID = " + id);
	}

	public Media search(String title) throws MediaNotFoundException {
	    System.out.println("**********************STORE SEARCH************************");
	    for (Media media : itemsInStore) {
	        if (media.getTitle().equalsIgnoreCase(title)) {
	            media.displayDetails();
	            return media;
	        }
	    }
	    throw new MediaNotFoundException("No media found with title = " + title);
	}
	
	// Display details of all Media in the store
		public void displayStore() {
			System.out.println("***********************STORE***********************");
			for (Media media : itemsInStore) {
//				media.displayDetails();
				System.out.println(media.toString());
			}
			// note có cần dùng toString trong dvd nữa ko
//			for (int i=0; i<itemsInStore.size(); i++) {
//				System.out.println(itemsInStore.get(i).toString());
//			}
		}
	
	public void displayStoreSortedByTitleThenCost() {
		System.out.println("***********************STORE SORTED BY TITLE-COST***********************");
		Collections.sort(itemsInStore, Media.COMPARE_BY_TITLE_COST);
		for (Media media : itemsInStore) {
			System.out.println(media.toString());
		}
	}
	
	public void displayStoreSortedByCostThenTitle() {
		System.out.println("***********************STORE SORTED BY COST-TITLE***********************");
		Collections.sort(itemsInStore, Media.COMPARE_BY_COST_TITLE);
		for (Media media:itemsInStore) {
			System.out.println(media.toString());
		}
	}



	public ObservableList<Media> getItemsInStore() {
		// TODO Auto-generated method stub
		return itemsInStore;
	}


}
