package hust.soict.ite6.oop.aims;

import hust.soict.ite6.oop.aims.cart.Cart;
import hust.soict.ite6.oop.aims.media.Book;
import hust.soict.ite6.oop.aims.media.CompactDisc;
import hust.soict.ite6.oop.aims.media.DigitalVideoDisc;
import hust.soict.ite6.oop.aims.media.Media;
import hust.soict.ite6.oop.aims.media.Track;
import hust.soict.ite6.oop.aims.store.Store;

import java.util.Arrays;
import java.util.Scanner;

public class Aims {
	
	private static Store store = new Store();
    private static Cart cart = new Cart();
	
	//  Thêm book vào Store
	
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display the main menu
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1: // View store
                    viewStore(scanner);
                    break;
                case 2: // Update store
                    updateStore(scanner);
                    break;
                case 3: // See current cart
                    seeCurrentCart(scanner);
                    break;
                case 0: // Exit
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("project AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void storeMenu() {
        System.out.println("Store Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Display store");
        System.out.println("2. See a media’s details");
        System.out.println("3. Add a media to cart");
        System.out.println("4. Play a media");
        System.out.println("5. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Media Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
    }

    public static void cartMenu() {
        System.out.println("Cart Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Search medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5: ");
    }

    // Option 1: View store
    public static void viewStore(Scanner scanner) {
        while (true) {
            storeMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
            	case 1: //display store
            		System.out.println("Choose criteria: ");
                    System.out.println("1. By Title");
                    System.out.println("2. By Cost");
                    int sortingCriteria = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
            	
                    if (sortingCriteria == 1 ) {
                    	store.displayStoreSortedByTitleThenCost();
                    	break;
                    }
                    
                    else if (sortingCriteria == 2 ) {
                    	store.displayStoreSortedByCostThenTitle();
                    	break;
                    
                    }
                    else {
                    	System.out.println("Invalid choice! Please enter a valid option.");
                    	break;
                    }
                case 2: // See media's details
                    System.out.print("Enter media title: ");
                    String title = scanner.nextLine();
                    
                    
                    Media media = store.search(title);
                    if (media != null) {
                        mediaDetailsMenu();
                        int mediaChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (mediaChoice) {
                            case 1: // Add to cart
                                cart.addMedia(media);
                                break;
                            case 2: // Play
                                media.play();
                                break;
                            case 0: // Back
                                break;
                        }
                    } 
                    break;
                    
                case 3: // Add a media to cart
                	System.out.print("Enter media title: ");
                    String addMediaString = scanner.nextLine();
                    Media addMedia = store.search(addMediaString);
                    cart.addMedia(addMedia);
                    break;
                    
                case 4: // Play a media
                    System.out.print("Enter media title: ");
                    title = scanner.nextLine();
                    media = store.search(title);
                    if (media != null) {
                        media.play();
                    }
                    break;
                    
                case 5: // See current cart
                    seeCurrentCart(scanner);
                    break;
                    
                case 0: // Back
                    return;
                    
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    // Option 2: Update store
    public static void updateStore(Scanner scanner) {
        System.out.println("Store Update Options: ");
        System.out.println("1. Add media");
        System.out.println("2. Remove media");
        System.out.println("0. Back");
        System.out.print("Please choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1: 
            	addMediaToStore(scanner);
                break;
            case 2: // Remove media
                // Similar logic to remove media from the store
            	removeMediaFromStore(scanner);
                break;
            case 0: // Back
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Option 3: See current cart
    public static void seeCurrentCart(Scanner scanner) {
        while (true) {
            cartMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Search medias in cart
                    System.out.println("Choose filter: ");
                    System.out.println("1. By ID");
                    System.out.println("2. By title");
                    int filterChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    if (filterChoice == 1) {
                        System.out.print("Enter ID: ");
                        int id = scanner.nextInt();
                        
                        Media media = cart.search(id);
                        if (media != null) {
                            System.out.println(media.toString());
                        } 
                    } else if (filterChoice == 2) {
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        
                        Media media = cart.search(title);
                        if (media != null) {
                            System.out.println(media.toString());
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                    
                case 2: // Sort medias in cart
                    System.out.println("Choose sorting option: ");
                    System.out.println("1. By title");
                    System.out.println("2. By cost");
                    int sortChoice = scanner.nextInt();
                    if (sortChoice == 1) {
                        cart.displayCartSortedByTitleThenCost();
                    } else if (sortChoice == 2) {
                        cart.displayCartSortedByCostThenTitle();
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                    
                case 3: // Remove media from cart
                    removeMediaFromCart(scanner);
                    break;
                case 4: // Play a media
                    System.out.print("Enter media title to play: ");
                    String playTitle = scanner.nextLine();
                    cart.search(playTitle);
                    break;
                    
                case 5: // Place order
                    System.out.println("Order has been placed!");
                    cart.clear();
                    return;
                case 0: // Back
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    
    
    
    
    
    
    
    public static void addMediaToStore(Scanner scanner) {
        System.out.println("Enter the type of media (Book/DVD/CD): ");
        String type = scanner.nextLine().trim();

        String title = getTitle(scanner);
        String category = getCategory(scanner);
        float cost = getCost(scanner);

        switch (type.toLowerCase()) {
            case "book":
                System.out.print("Enter authors (comma-separated): ");
                String[] authors = scanner.nextLine().split(",");
                store.addMedia(new Book(title, category, cost, Arrays.asList(authors)));
                break;
                
            case "dvd":
                System.out.print("Enter director: ");
                String dvdDirector = scanner.nextLine();
                System.out.print("Enter length: ");
                int dvdLength = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                store.addMedia(new DigitalVideoDisc(title, category, cost, dvdDirector, dvdLength));
                break;
                
            case "cd":
            	System.out.print("Enter director: ");
                String cdDirector = scanner.nextLine();
                scanner.nextLine(); // Consume newline
                
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();

                System.out.print("Enter number of tracks: ");
                int trackCount = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                CompactDisc cd = new CompactDisc(title, category, cost, artist, cdDirector , 0);

                for (int i = 1; i <= trackCount; i++) {
                    System.out.println("Enter details for track " + i + ":");
                    System.out.print("Track name: ");
                    String trackName = scanner.nextLine();

                    System.out.print("Track length (in seconds): ");
                    int trackLength = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    cd.addTrack(new Track(trackName.trim(), trackLength));
                }

                store.addMedia(cd);
                System.out.println("CD added to the store successfully!");
                break;
            default:
                System.out.println("Invalid media type.");
        }
    }
    
    public static void removeMediaFromStore(Scanner scanner) {
        String title = getTitle(scanner);
        Media media = store.search(title);

        if (media != null) {
            store.removeMedia(media);
            System.out.println("Media removed successfully.");
        } else {
            System.out.println("Media not found in store.");
        }
    }
    
    public static void addMediaToCart(Scanner scanner) {
        System.out.println("Enter the type of media (Book/DVD/CD): ");
        String type = scanner.nextLine().trim();
        
        String title = getTitle(scanner);
        String category = getCategory(scanner);
        float cost = getCost(scanner);

        switch (type.toLowerCase()) {
            case "book":
                System.out.print("Enter authors (comma-separated): ");
                String[] authors = scanner.nextLine().split(",");
                cart.addMedia(new Book(title, category, cost, Arrays.asList(authors)));
                break;
                
            case "dvd":
                System.out.print("Enter director: ");
                String dvdDirector = scanner.nextLine();
                System.out.print("Enter length: ");
                int dvdLength = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                cart.addMedia(new DigitalVideoDisc(title, category, cost, dvdDirector, dvdLength));
                break;
                
            case "cd":
            	System.out.print("Enter director: ");
                String cdDirector = scanner.nextLine();
                scanner.nextLine(); // Consume newline
                
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();

                System.out.print("Enter number of tracks: ");
                int trackCount = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                CompactDisc cd = new CompactDisc(title, category, cost, artist, cdDirector , 0);

                for (int i = 1; i <= trackCount; i++) {
                    System.out.println("Enter details for track " + i + ":");
                    System.out.print("Track name: ");
                    String trackName = scanner.nextLine();

                    System.out.print("Track length (in seconds): ");
                    int trackLength = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    cd.addTrack(new Track(trackName.trim(), trackLength));
                }

                cart.addMedia(cd);
                System.out.println("CD added to the cart successfully!");
                break;
            default:
                System.out.println("Invalid media type.");
        }
    }
    
    
    public static void removeMediaFromCart(Scanner scanner) {
        String title = getTitle(scanner);
        Media media = cart.search(title);

        if (media != null) {
            cart.removeMedia(media);
            System.out.println("Media removed successfully.");
        } else {
            System.out.println("Media not found in cart.");
        }
    }
    
    public static String getTitle(Scanner scanner) {
        System.out.print("Enter title: ");
        return scanner.nextLine();
    }

    public static String getCategory(Scanner scanner) {
        System.out.print("Enter category: ");
        return scanner.nextLine();
    }

    public static float getCost(Scanner scanner) {
        System.out.print("Enter cost: ");
        while (!scanner.hasNextFloat()) {
            System.out.println("Invalid input. Please enter a valid number for cost.");
            scanner.next(); // Consume invalid input
        }
        float cost = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        return cost;
    }
    
}

