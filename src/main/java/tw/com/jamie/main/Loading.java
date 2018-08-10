package tw.com.jamie.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

public class Loading extends JFrame {

	JFileChooser chooser = new JFileChooser();
	JButton button = new JButton("Select a directory");

	public Loading() {
//		super("Simple File Chooser Application");
		Container contentPane = getContentPane();

		contentPane.setLayout(new FlowLayout());
		contentPane.add(button, BorderLayout.CENTER);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Stream Analysis");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null,  chooser.getSelectedFile());
					String streamPath = chooser.getSelectedFile().getPath();
					System.out.println("Print path: " + streamPath);
					System.out.println("Directory :" +  chooser.getSelectedFile());
				} else if (chooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(null, "Canceled");
				}
	
			}
		});
	}

	public static void main(String args[]) {
		JFrame f = new Loading();
		f.setBounds(300, 300, 350, 100);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(false);

		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		
		
	}
}

//	private JFrame frame;
//	private JTextField txtPath;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Loading window = new Loading();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	   * Create the application.
//	   */
//	  public Loading() {
//	    initialize();
//	  }
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 300, 200);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		frame.setResizable(false);
//
//		txtPath = new JTextField();
//		txtPath.setBounds(10, 10, 275, 21);
//		frame.getContentPane().add(txtPath);
//		txtPath.setColumns(10);
//
//		JButton btnBrowse = new JButton("Browse");
//		btnBrowse.setBounds(10, 41, 80, 23);
////		JButton btnOK = new JButton("OK");
////		btnOK.setBounds(120, 41, 80, 23);
//		frame.getContentPane().add(btnBrowse);
////		frame.getContentPane().add(btnOK);
//		System.out.println("111111111111");
//		final String s;
//		btnBrowse.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JFileChooser fileChooser = new JFileChooser();
//
//				// For Directory
//				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
//				// For File
//				// fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//				fileChooser.setAcceptAllFileFilterUsed(false);
//
//				int rVal = fileChooser.showOpenDialog(null);
//				if (rVal == JFileChooser.APPROVE_OPTION) {
//					txtPath.setText(fileChooser.getSelectedFile().toString());
//					 
//					
//					
//					
//				}
//			}
//		});
//		
//		System.out.println("2222222222222");
//		
//		s = txtPath.getText();
//		System.out.println(s);
//	}
//}

//	public static void main(String[] args) throws Exception {
//		JFileChooser chooser = new JFileChooser();
//	    chooser.setCurrentDirectory(new java.io.File("."));
//	    chooser.setDialogTitle("choosertitle");
//	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//	    chooser.setAcceptAllFileFilterUsed(false);
//
//	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//	      System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
//	      System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
//	      
//	    } else {
//	      System.out.println("No Selection ");
//	    }
//	  }
//	}

//		JFrame loadingFrame = new JFrame("Stream Convertion");
//		ImageIcon icon = new ImageIcon("C:\\Users\\jamie.chang\\Downloads\\ajax-loader.gif");
//		
//		loadingFrame.getContentPane().setBackground(new Color(76, 77, 81));
//		
//		
//		JLabel loadingLabel = new JLabel("Loading... ", icon, JLabel.CENTER);
//		loadingFrame.add(loadingLabel);
//		loadingLabel.setFont(new Font("Arial", Font.BOLD, 18));
//		loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		loadingFrame.setSize(300, 200);
//		loadingFrame.setLocationRelativeTo(null);  //set Jframe to the centre of the screen
//		loadingFrame.setVisible(true);	    
//		
//		Image image = ImageIO.read(Main.class.getResource("/output.png"));
//		loadingFrame.setIconImage(image);
//		
//		
//		
//		
//		
//		Thread.sleep(5000);
//	    
//	    
//	    Thread.sleep(5000);
//	    
//	    loadingFrame.dispose();
