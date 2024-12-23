package hust.soict.ite6.oop.aims.media;

public abstract class Disc extends Media {
    private String director; // Director of the media (e.g., for DVD and CD)
    private int length;      // Length of the media in minutes

    // Constructor
    public Disc(String title, String category, float cost,  String director, int length) {
        super(title, category, cost); // Call the constructor of Media
        this.director = director;
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void displayDetails() {
       
    }

    // Abstract method for Disc-specific details
    public abstract void play();
    
    @Override
	public String toString() {
		String result = super.toString();
		StringBuilder sb = new StringBuilder();
		
		sb.append(result).append(", Director:").append(director).append(", Length:").append(length);
		
		result = sb.toString();
		return result;
	}
}
