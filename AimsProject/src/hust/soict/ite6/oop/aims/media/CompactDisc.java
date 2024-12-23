package hust.soict.ite6.oop.aims.media;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<>();

    public CompactDisc( String title, String category, float cost, String artist, String director,int length) {
        super(title, category, cost, director,length);
        this.artist = artist;
//        System.out.println("CD " + title + " : id = "+this.getId());
    }

    public void addTrack(Track track) {
    	if (!tracks.contains(track)) {
    		tracks.add(track);
    		System.out.println("Track " + track.getTitle() + " has been added to the CD " + this.getTitle());
    	}
    	else {
    		System.out.println("Track " + track.getTitle() + " is already in the CD " + this.getTitle());
    	}
    }
    
    public void removeTrack(Track track) {
    	if (tracks.contains(track)) {
    		tracks.remove(track);
    		System.out.println("Track " + track.getTitle() + " has been removed from the CD " + this.getTitle());
    	}
    	else {
    		System.out.println("Track " + track.getTitle() + " is not in the CD " + this.getTitle());
    	}
    }
    

    public int getLength() {
        return tracks.stream().mapToInt(Track::getLength).sum();
    }
    

    @Override
    public void displayDetails() {
        System.out.println("CD Title: " + getTitle());
        System.out.println("CD Id: " + getId());
        System.out.println("Category: " + getCategory());
        System.out.println("Director: " + getDirector());
        System.out.println("CD Length: " + getLength() + " minutes");
        System.out.println("Cost: $" + getCost());
        for (Track track : tracks) {
            System.out.println("- " + track.getTitle() + " (" + track.getLength() + " minutes)");
        }
    }

    @Override
    public void play() {
        System.out.println("Playing CD: " + getTitle() + " (" + getLength() + " minutes)");
        for (Track track : tracks) {
            track.play();
        }
    }
    
    @Override
	public String toString() {
		String result = super.toString();
		StringBuilder sb = new StringBuilder();
		
		sb.append("CD: ").append(result).append(", Artist: ").append(artist);
		
		result = sb.toString();
		return result;
	}
    
}
