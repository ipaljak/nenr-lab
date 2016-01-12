package hr.fer.zemris.lab5.GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import hr.fer.zemris.lab5.backend.NeuralNet;
import hr.fer.zemris.lab5.backend.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Classifier extends JFrame {

	private int batchSize;
	private NeuralNet net; 
	
	public Classifier(final NeuralNet net, int batchSize) {
		
		super();
		
		this.net = net;
		this.batchSize = batchSize;
		
		net.learn(batchSize);
		
		this.setSize(600, 400); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	    
		// Layout Manager
		Container container = getContentPane();
	    setLayout(new BorderLayout());
		
	    // Swing Components
		final JPanel canvas = new CanvasPanel();
	 	container.add(canvas, BorderLayout.CENTER);	    
	
	 	JButton classify = new JButton("Classify");
	 	
	 	classify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Point> features = Point.getFeatures((ArrayList<Point>) ((CanvasPanel) canvas).getPoints(), 20);
				ArrayList<Double> sample = Point.fromFeatures(features);
				net.evaluate(sample);
				message(net.getLetter());
			}
		});
	 	
	 	JPanel buttons = new JPanel();	
	 	buttons.add(classify);
	 	
	 	container.add(buttons, BorderLayout.SOUTH);
	}
	
	public void message(String text) {
		JOptionPane.showMessageDialog(this, text);
	}
	
}
