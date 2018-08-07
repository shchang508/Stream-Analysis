package tw.com.jamie.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class Loading {
	
	public static void main(String[] args) throws Exception {

		JFrame loadingFrame = new JFrame("Stream Convertion");
		ImageIcon icon = new ImageIcon("C:\\Users\\jamie.chang\\Downloads\\ajax-loader.gif");
		
		//loadingFrame.getContentPane().setBackground(new Color(255, 255, 255));
		
		
		JLabel loadingLabel = new JLabel("Loading... ", icon, JLabel.CENTER);
		loadingFrame.add(loadingLabel);
		loadingLabel.setFont(new Font("Arial", Font.BOLD, 18));
		loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingFrame.setSize(300, 200);
		loadingFrame.setLocationRelativeTo(null);  //set Jframe to the centre of the screen
		loadingFrame.setVisible(true);	    
		
		Image image = ImageIO.read(Main.class.getResource("/output.png"));
		loadingFrame.setIconImage(image);
		
		
		
		
		
		Thread.sleep(5000);
	    
//	    frame.add(new JLabel("Test", JLabel.));
	    
	    Thread.sleep(5000);
	    
	    loadingFrame.dispose();
	}

}
