package hust.soict.ite6.oop.aims.media;

import java.util.Objects;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }
    
    

    public void play() {
        if (length > 0) {
        	System.out.println("Playing CD: " + this.getTitle() + " (" + this.getLength() + " mins)");
        } else {
            System.out.println("Track " + this.getTitle() + " cannot be played");
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

