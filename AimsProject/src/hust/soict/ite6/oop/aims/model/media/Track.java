package hust.soict.ite6.oop.aims.model.media;

import java.util.Objects;

import hust.soict.ite6.oop.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

    // Constructor
    public Track(String title, int length) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Track title cannot be null or empty.");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Track length cannot be negative.");
        }
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Track title cannot be null or empty.");
        }
        this.title = title;
    }

    public void setLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Track length cannot be negative.");
        }
        this.length = length;
    }

    // Play method
    
    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            // Play the track
            System.out.println("Playing track: " + this.getTitle() + " (" + this.getLength() + " mins)");
        } else {
            System.err.println("ERROR: Track length is non-positive!"); // Print the error message to the error stream
            throw new PlayerException("ERROR: Track length is non-positive!"); // Raise the exception
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Nếu 2 đối tượng trỏ đến cùng một ô nhớ
        if (obj == null || getClass() != obj.getClass()) return false; // Nếu null hoặc khác kiểu Track
        Track track = (Track) obj; // Ép kiểu (cast) sang Track
        return Objects.equals(title, track.title) && length == track.length; // So sánh `title` và `length`
    }
}
