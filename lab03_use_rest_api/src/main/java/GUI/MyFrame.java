package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyFrame extends JFrame {

	public MyFrame() throws IOException, InterruptedException {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();

		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setBounds(screenWidth / 4, screenHeight / 4, 1000 , 500);

		
		add(new MainPanel());

	}

}

