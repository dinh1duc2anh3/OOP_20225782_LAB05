package hust.soict.ite6.oop.aims.model.media;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Book extends Media {
    private List<String> authors;

    // Default Constructor for book
    public Book(String title) throws IllegalArgumentException {
        super(title);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        authors = new ArrayList<>();
    }

    public Book(String title, String category, float cost, List<String> authors) throws IllegalArgumentException {
        super(title, category, cost);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException("Authors list cannot be null or empty.");
        }
        this.authors = authors;
    }

    public void addSingleAuthor(String authorName) throws IllegalArgumentException {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be null or empty.");
        }
        if (!authors.contains(authorName)) { // Check if the author is not already in the list
            authors.add(authorName); // Add the author
            System.out.println("Author " + authorName + " has been added to the book " + getTitle());
        } else {
            System.out.println("Author " + authorName + " is already in the list for book " + getTitle());
        }
    }

    public List<String> addAuthorsDevidedByComma(String authorsListDevidedByComma) throws IllegalArgumentException {
        // Check if the string is null or empty
        if (authorsListDevidedByComma == null || authorsListDevidedByComma.trim().isEmpty()) {
            throw new IllegalArgumentException("Authors list cannot be null or empty.");
        }

        List<String> authors = new ArrayList<>();
        
        // Split the authors list by commas and remove any extra spaces
        String[] authorArray = authorsListDevidedByComma.split("\\s*,\\s*");
        
        for (String author : authorArray) {
            if (author.trim().isEmpty()) {
                throw new IllegalArgumentException("Author name cannot be empty after trimming.");
            }
            authors.add(author.trim());
        }
        
        return authors;
    }

    public void removeAuthor(String authorName) throws IllegalArgumentException {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be null or empty.");
        }
        if (authors.contains(authorName)) { // Check if the author exists in the list
            authors.remove(authorName); // Remove the author
            System.out.println("Author " + authorName + " has been removed from the book " + getTitle());
        } else {
            throw new IllegalArgumentException("Author " + authorName + " is not in the list for book " + getTitle());
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("Book Title: " + getTitle());
        System.out.println("Id: " + getId());
        System.out.println("Category: " + getCategory());
        System.out.println("Authors: " + String.join(", ", authors)); // Display authors
        System.out.println("Book Title: " + getTitle());
        StringTokenizer tokenizer = new StringTokenizer(getTitle()); // Question: Is this necessary?
        System.out.println("Content Length: " + tokenizer.countTokens() + " tokens");
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) throws IllegalArgumentException {
        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException("Authors list cannot be null or empty.");
        }
        this.authors = authors;
    }

    @Override
    public void play() {
        System.out.println("Books can't be played");
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
