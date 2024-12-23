package hust.soict.ite6.oop.test.disc;

import hust.soict.ite6.oop.aims.media.DigitalVideoDisc;

public class TestPassingParameter {

	public static void main(String[] args) {
		DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle", "Sci-Fi", 20.0f, "Christopher Nolan", 148);
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinerella", "Fantasy", 25.0f, "James Cameron", 162);
		
		swap(jungleDVD, cinderellaDVD);
		
		System.out.println("jungle dvd title: "+ jungleDVD.getTitle()+"(id = "+jungleDVD.getId()+")");
		System.out.println("cinderella dvd title: "+ cinderellaDVD.getTitle()+"(id = "+cinderellaDVD.getId()+")");
		
		changeTitle(jungleDVD, cinderellaDVD.getTitle());
		System.out.println("jungle dvd title: "+ jungleDVD.getTitle());
		
		
		
	}
//	hàm swap cũ mà ko thay đổi được giá trị
	public static void swap(Object o1,Object o2 ) {
		Object tmp = o1;
		o1 = o2;
		o2 = tmp;
	}
	
//	public static void swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
//		String tmp = dvd1.getTitle();
//		dvd1.setTitle(dvd2.getTitle());
//		dvd2.setTitle(tmp);
//	}
//	
	public static void changeTitle(DigitalVideoDisc dvd , String title ) {
		
		
	}
	

}
