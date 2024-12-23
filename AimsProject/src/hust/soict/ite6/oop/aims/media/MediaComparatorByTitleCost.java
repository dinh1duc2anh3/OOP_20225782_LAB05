package hust.soict.ite6.oop.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {

	@Override
	public int compare(Media media1, Media media2) {
		//first compare by title in alphabetical order
		int titleComparison = media1.getTitle().compareTo(media2.getTitle());
		if (titleComparison != 0) {
			return titleComparison;
		}
		
		//if title are the same, compare by cost in decresing order
		return Float.compare(media2.getCost(), media1.getCost()); //media2 before media1 for decreasing order
	}

}
