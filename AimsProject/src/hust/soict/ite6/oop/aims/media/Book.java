package hust.soict.ite6.oop.aims.media;


import java.util.List;
import java.util.StringTokenizer;

public class Book extends Media {
	private List<String> authors;
	
	public Book(String title, String category, float cost, List<String> authors) {
		super(title, category, cost);
		this.authors = authors;
//		System.out.println("Book " + title + " : id = "+this.getId());
	}
	
	public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) { // Check if the author is not already in the list
            authors.add(authorName); // Add the author
            System.out.println("Author " + authorName + " has been added to the book " + getTitle());
        } else {
            System.out.println("Author " + authorName + " is already in the list for book " + getTitle());
        }
    }
	
	public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) { // Check if the author exists in the list
            authors.remove(authorName); // Remove the author
            System.out.println("Author " + authorName + " has been removed from the book " + getTitle());
        } else {
            System.out.println("Author " + authorName + " is not in the list for book " + getTitle());
        }
    }
	
	@Override
	public void displayDetails() {
		System.out.println("Book Title: " + getTitle());
		System.out.println("Id: " + getId());
		System.out.println("Category: " + getCategory());
		System.out.println("Authors: " + String.join(", ", authors)); //note 
		System.out.println("Book Title: " + getTitle());
		StringTokenizer tokenizer = new StringTokenizer(getTitle()); //question
		System.out.println("Content Length: " + tokenizer.countTokens() + " tokens"); 
	}
	
	@Override
	public void play() {
		System.out.println("Books cant be played");
	}
	
	@Override
	public String toString() {
		String result = super.toString();
		StringBuilder sb = new StringBuilder();
		
		sb.append("Book: ").append(result).append(", Authors:").append(String.join(",", authors));
		
		result = sb.toString();
		return result;
	}
	
	
}