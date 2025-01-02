package hust.soict.ite6.oop.aims.model.media;

import java.util.Comparator;
import java.util.Objects;

import hust.soict.ite6.oop.aims.exception.PlayerException;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;
    
    public static int nbMedia = 0;
     
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
    
    // Default constructor for Media
    public Media(String title) throws IllegalArgumentException {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
        this.id = ++nbMedia;
    }
    
    public Media(String title, String category, float cost) throws IllegalArgumentException {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.id = ++nbMedia;
    }
    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty.");
        }
        this.category = category;
    }

    public void setCost(float cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        this.cost = cost;
    }

    public abstract void displayDetails();

    public abstract void play() throws PlayerException;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Id:").append(id).append(", Title:").append(title)
          .append(", Category:").append(category).append(", Cost:").append(cost);
        return sb.toString();
    }
    
    
    @Override
    public boolean equals(Object obj) {
        // Check if the object is the same as this
        if (this == obj) {
            return true;
        }
        
        // Check if the object is null or not an instance of Media
        if (obj == null || !(obj instanceof Media)) {
            return false;
        }
        
        // Cast the object to Media
        Media otherMedia = (Media) obj;
        
        // Compare titles
        // Avoid NullPointerException by checking if titles are equal
        if (this.title == null && otherMedia.title == null) {
            return true;
        }
        
        if (this.title != null && this.title.equals(otherMedia.title)) {
            return true;
        }
        
        return false; // Return false if titles don't match
    }
}
