package hust.soict.ite6.oop.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {

	@Override
	public int compare(Media media1, Media media2) {
		//first compare by cost in descending order
		int costComparison = Float.compare(media2.getCost(),media1.getCost());
		if (costComparison != 0) {
			return costComparison;
		}
		
		//is costs are the same, compare by title in alphabetical order
		return media1.getTitle().compareTo(media2.getTitle());
	}

}
