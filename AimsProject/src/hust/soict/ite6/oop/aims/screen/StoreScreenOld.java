package hust.soict.ite6.oop.aims.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import hust.soict.ite6.oop.aims.exception.CartFullException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInCartException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInStoreException;
import hust.soict.ite6.oop.aims.exception.StoreFullException;
import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.Book;
import hust.soict.ite6.oop.aims.model.media.CompactDisc;
import hust.soict.ite6.oop.aims.model.media.DigitalVideoDisc;
import hust.soict.ite6.oop.aims.model.media.Media;
import hust.soict.ite6.oop.aims.model.media.Playable;
import hust.soict.ite6.oop.aims.model.media.Track;
import hust.soict.ite6.oop.aims.model.store.Store;
import javafx.collections.ObservableList;


//ban dung awt

public class StoreScreenOld extends JFrame {
    private Store store;
    private Cart cart;
    
    // Constructor sửa lại nhận tham số Store
    public StoreScreenOld(Store store,Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        
        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }

    // Phương thức sửa lỗi cú pháp CreateNorth
    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    // Phương thức tạo menu bar
    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        smUpdateStore.add(new JMenuItem("Add Book"));
        smUpdateStore.add(new JMenuItem("Add CD"));
        smUpdateStore.add(new JMenuItem("Add DVD"));
        
        menu.add(smUpdateStore);
        menu.add(new JMenuItem("View Store"));
        menu.add(new JMenuItem("View Cart"));
        
        
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        
        
        return menuBar;
    }

    // Phương thức tạo header
    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        
        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setBackground(Color.CYAN);
        
        JButton cart = new JButton("View Cart");
        cart.setPreferredSize(new Dimension(100, 50));
        cart.setMaximumSize(new Dimension(100, 50));
        
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    // Phương thức tạo panel trung tâm chứa các sản phẩm media
    private JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        // Lấy danh sách các media từ store
        ObservableList<Media> mediaInStore = store.getItemsInStore() ;
        for (int i = 0; i < 9 && i < mediaInStore.size(); i++) {  // Đảm bảo không vượt quá giới hạn
            Media media = mediaInStore.get(i);
            MediaStore cell = new MediaStore(media);
            center.add(cell);
        }
        
        return center;
    }

    // Lớp MediaStore đại diện cho mỗi sản phẩm media
    public class MediaStore extends JPanel {
        private Media media;
        
        public MediaStore(Media media) {
            this.media = media;
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Nhãn tiêu đề
            JLabel title = new JLabel(media.getTitle());
            title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
            title.setAlignmentX(CENTER_ALIGNMENT);
            
            // Nhãn giá
            JLabel cost = new JLabel(String.format("%.2f$", media.getCost()));  // Hiển thị giá với 2 chữ số thập phân
            cost.setAlignmentX(CENTER_ALIGNMENT);
            
            // Panel chứa các nút
            JPanel container = new JPanel();
            container.setLayout(new FlowLayout(FlowLayout.CENTER));
            
            
            // Nút "Thêm vào giỏ hàng" :  button add to cart 
            JButton addToCartButton = new JButton("Add to cart");
            addToCartButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						cart.addMedia(media);
					} catch (CartFullException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MediaAlreadyInCartException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("Added to cart: " + media.getTitle());
				}
            	
            });
            container.add(addToCartButton);
            
            // Nút "Play" cho các media có thể phát
            if (media instanceof Playable) {
            	JButton playButton = new JButton("Play");
            	playButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// show media playing dialog
						JDialog playDialog = new JDialog(StoreScreenOld.this ,"Playing: "+media.getTitle(),true);
						playDialog.setSize(300, 150);
						playDialog.setLocationRelativeTo(StoreScreenOld.this);
						
						JLabel playLabel = new JLabel("Now playing: "+media.getTitle(), SwingConstants.CENTER);
						playDialog.add(playLabel, BorderLayout.CENTER);
						playDialog.setVisible(true);
					}
				});
                container.add(playButton);
                
            }
            
            // Thêm các thành phần vào panel
            this.add(Box.createVerticalGlue());
            this.add(title);
            this.add(cost);
            this.add(Box.createVerticalGlue());
            this.add(container);

            // Đặt viền cho panel
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }

    // Phương thức main để chạy chương trình
    public static void main(String[] args) {
    	// Create a store
        Store store = new Store();
        Cart cart = new Cart();

        // Add DigitalVideoDisc items to the store
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Inception", "Sci-Fi", 20.0f, "Christopher Nolan", 148);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Avatar", "Fantasy", 25.0f, "James Cameron", 162);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Titanic", "Romance", 22.0f, "James Cameron", 195);

        try {
			store.addMedia(dvd1, dvd2, dvd3);
		} catch (StoreFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaAlreadyInStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Add CompactDisc to the store
        CompactDisc cd = new CompactDisc("Thriller", "Pop", 15.0f, "Quincy Jones", "Michael Jackson", 0);
        cd.addTrack(new Track("Wanna Be Startin' Somethin'", 6));
        cd.addTrack(new Track("Thriller", 7));
        cd.setLength(cd.getLength());
        
        try {
			store.addMedia(cd);
		} catch (StoreFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaAlreadyInStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Add Book to the store
        Book book = new Book("Effective Java", "Programming", 45.0f, Arrays.asList("Joshua Bloch"));
        try {
			store.addMedia(book);
		} catch (StoreFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaAlreadyInStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        new StoreScreenOld(store,cart);
        
        
    }
}
