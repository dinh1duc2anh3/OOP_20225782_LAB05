package hust.soict.ite6.oop.aims.model.media;

import hust.soict.ite6.oop.aims.exception.PlayerException;

public abstract class Disc extends Media {
    private String director; // Director of the media (e.g., for DVD and CD)
    private int length;      // Length of the media in minutes

    // Default constructor for disc
    public Disc(String title) throws IllegalArgumentException {
        super(title);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
    }

    // Constructor
    public Disc(String title, String category, float cost, String director, int length) throws IllegalArgumentException {
        super(title, category, cost); // Call the constructor of Media
        
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("Director name cannot be null or empty.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Length cannot be negative.");
        }
        
        this.director = director;
        this.length = length;
    }

    public void setDirector(String director) throws IllegalArgumentException {
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("Director name cannot be null or empty.");
        }
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) throws IllegalArgumentException {
        if (length < 0) {
            throw new IllegalArgumentException("Length cannot be negative.");
        }
        this.length = length;
    }

    @Override
    public void displayDetails() {
        // This method is abstract in the parent class, so it's not implemented here
    }

    // Abstract method for Disc-specific details
    public abstract void play() throws PlayerException;

    @Override
    public String toString() {
        String result = super.toString();
        StringBuilder sb = new StringBuilder();
        
        sb.append(result).append(", Director:").append(director).append(", Length:").append(length);
        
        result = sb.toString();
        return result;
    }
}
