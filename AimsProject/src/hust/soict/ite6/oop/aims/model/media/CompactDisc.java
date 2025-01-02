package hust.soict.ite6.oop.aims.model.media;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hust.soict.ite6.oop.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<>();

    // Default constructor
    public CompactDisc(String title) throws IllegalArgumentException {
        super(title);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
    }

    public CompactDisc(String title, String category, float cost, String artist, String director, int length) throws IllegalArgumentException {
        super(title, category, cost, director, length);
        
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be null or empty.");
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
        
        this.artist = artist;
    }

    public void addTrack(Track track) throws IllegalArgumentException {
        if (track == null) {
            throw new IllegalArgumentException("Track cannot be null.");
        }
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track " + track.getTitle() + " has been added to the CD " + this.getTitle());
        } else {
            System.out.println("Track " + track.getTitle() + " is already in the CD " + this.getTitle());
        }
    }

    public void removeTrack(Track track) throws IllegalArgumentException {
        if (track == null) {
            throw new IllegalArgumentException("Track cannot be null.");
        }
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track " + track.getTitle() + " has been removed from the CD " + this.getTitle());
        } else {
            System.out.println("Track " + track.getTitle() + " is not in the CD " + this.getTitle());
        }
    }

    public int getLength() {
        return tracks.stream().mapToInt(Track::getLength).sum();
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) throws IllegalArgumentException {
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be null or empty.");
        }
        this.artist = artist;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) throws IllegalArgumentException {
        if (tracks == null) {
            throw new IllegalArgumentException("Tracks list cannot be null.");
        }
        if (tracks.contains(null)) {
            throw new IllegalArgumentException("Tracks list cannot contain null elements.");
        }
        this.tracks = tracks;
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
    public void play() throws PlayerException {
        if (this.getLength() > 0) {  // Check CD length
            System.out.println("Playing CD: " + this.getTitle() + " (" + this.getLength() + " minutes)");

            Iterator<Track> iter = tracks.iterator();
            while (iter.hasNext()) {
                Track nextTrack = iter.next();
                try {
                    nextTrack.play();  // Play each track, might throw PlayerException
                } catch (PlayerException e) {
                    System.err.println("Error while playing track: " + nextTrack.getTitle());
                    throw e;  // Re-throw the PlayerException if a track can't be played
                }
            }
        } else {
            throw new PlayerException("ERROR: CD length is non-positive!");  // Raise PlayerException for invalid CD length
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
