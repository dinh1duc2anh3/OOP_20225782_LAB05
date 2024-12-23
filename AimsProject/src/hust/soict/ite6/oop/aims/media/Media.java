package hust.soict.ite6.oop.aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;
    
    public static int nbMedia =0;
     
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new  MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new  MediaComparatorByCostTitle();

    public Media(String title, String category, float cost) {
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
    

    public abstract void displayDetails();

    public abstract void play();
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(" Id:").append(id).append(", Title:").append(title).append(", Category:")
    		.append(category).append(", Cost:").append(cost);
    	return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Nếu 2 đối tượng trỏ đến cùng một ô nhớ
        if (obj == null || getClass() != obj.getClass()) return false; // Nếu null hoặc khác kiểu Media
        Media media = (Media) obj; // Ép kiểu (cast) sang Media
        return Objects.equals(title, media.title); // So sánh `title`
    }

    
}