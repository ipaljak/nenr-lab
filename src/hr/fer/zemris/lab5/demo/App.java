package hr.fer.zemris.lab5.demo;

import hr.fer.zemris.lab5.GUI.MainMenu;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Desktop application which demonstrates the capabilities of three learning algorithms:
 *   1) Backpropagation
 *   2) Stohastic backpropagation
 *   3) Mini-batch backpropagation
 * 
 * The user is able to create his/her own learning examples and test each learning algorithm. 
 * 
 * @author ipaljak
 *
 */

public class App {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new MainMenu("Main Menu");
				frame.setVisible(true);
			}
		});
		
	}
	
}
