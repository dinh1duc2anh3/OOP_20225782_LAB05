package hust.soict.ite6.oop.aims.model.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {

    @Override
    public int compare(Media media1, Media media2) {
        // Kiểm tra nếu một trong hai đối tượng là null
        if (media1 == null && media2 == null) {
            return 0; // Nếu cả hai đều null, coi là bằng nhau
        }
        if (media1 == null) {
            return -1; // Nếu media1 là null, coi nó nhỏ hơn media2
        }
        if (media2 == null) {
            return 1; // Nếu media2 là null, coi nó nhỏ hơn media1
        }

        // So sánh theo title
        String title1 = media1.getTitle();
        String title2 = media2.getTitle();
        
        if (title1 == null && title2 == null) {
            return 0; // Nếu cả hai title đều null, coi là bằng nhau
        }
        if (title1 == null) {
            return -1; // Nếu title1 là null, coi nó nhỏ hơn title2
        }
        if (title2 == null) {
            return 1; // Nếu title2 là null, coi nó nhỏ hơn title1
        }
        
        // So sánh title theo thứ tự alphabet
        int titleComparison = title1.compareTo(title2);
        if (titleComparison != 0) {
            return titleComparison;
        }

        // Nếu title giống nhau, so sánh theo giá (cost) theo thứ tự giảm dần
        return Float.compare(media2.getCost(), media1.getCost());
    }
}
