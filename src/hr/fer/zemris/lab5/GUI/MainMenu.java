package hr.fer.zemris.lab5.GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Implementation of <it>Main Menu</it> frame. 
 * 
 * @author ipaljak
 *
 */

public class MainMenu extends JFrame{

	public MainMenu(String title) {
		
		super(title);
		
		this.setSize(250, 115); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
		
		// Layout Manager
		Container container = getContentPane();
		setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		// Swing Components
		JButton train = new JButton("Make some examples");
		train.setAlignmentX(container.CENTER_ALIGNMENT);
		
		JButton test  = new JButton("Try it out!");
		test.setAlignmentX(container.CENTER_ALIGNMENT);
		
		JButton exit  = new JButton("Exit");
		exit.setAlignmentX(container.CENTER_ALIGNMENT);
		
		// TODO: Add button functionalities 
		
		train.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame exampleMaker = new ExampleMaker("Example Maker");
				exampleMaker.setVisible(true);
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
				
		// Add components to ContentPane
		container.add(train); container.add(Box.createVerticalGlue());
		container.add(test); container.add(Box.createVerticalGlue());
		container.add(exit);
		
	}
	
}
