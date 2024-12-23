package hust.soict.ite6.oop.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
	
    public DigitalVideoDisc( String title, String category, float cost, String director, int length) {
        super( title, category, cost,director , length);
//        System.out.println("DVD " + title + " : id = "+this.getId());
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
    public void play() {
        if (getLength() > 0) {
            System.out.println("Playing DVD: " + getTitle() + " (" + getLength() + " minutes)");
        } else {
            System.out.println("DVD " + getTitle() + " cannot be played (invalid length).");
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

