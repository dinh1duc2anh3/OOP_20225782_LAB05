package hust.soict.ite6.oop.aims.model.media;

import hust.soict.ite6.oop.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

    // Default constructor
    public DigitalVideoDisc(String title) throws IllegalArgumentException {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, float cost, String director, int length) throws IllegalArgumentException {
        super(title, category, cost, director, length);

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
    }

    @Override
    public void displayDetails() {
        System.out.println("DVD Title: " + getTitle());
        System.out.println("DVD Id: " + getId());
        System.out.println("Category: " + getCategory());
        System.out.println("Director: " + getDirector());
        System.out.println("DVD Length: " + getLength() + " minutes");
        System.out.println("Cost: $" + getCost());
    }

    
    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            // Implement the actual play logic
            System.out.println("Playing DVD: " + getTitle() + " (" + getLength() + " minutes)");
        } else {
            System.err.println("ERROR: DVD length is non-positive!"); // Print the error message to the error stream
            throw new PlayerException("ERROR: DVD length is non-positive!"); // Raise the exception
        }
    }

    @Override
    public String toString() {
        String result = super.toString();
        StringBuilder sb = new StringBuilder();

        sb.append("DVD: ").append(result);

        result = sb.toString();
        return result;
    }
}
