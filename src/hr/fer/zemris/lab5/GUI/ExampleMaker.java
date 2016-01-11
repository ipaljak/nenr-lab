package hr.fer.zemris.lab5.GUI;


import hr.fer.zemris.lab5.backend.Point;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Implementation of the example maker. Users can draw their own greek letter examples used to train neural networks. 
 * 
 * @author ipaljak
 *
 */

public class ExampleMaker extends JFrame {

	public ExampleMaker(String title) {
		
		super(title);

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
	
	 	JButton alpha   = new JButton("Alpha");
	 	JButton beta    = new JButton("Beta");
	 	JButton gamma   = new JButton("Gamma");
	 	JButton delta   = new JButton("Delta");
	 	JButton epsilon = new JButton("Epsilon");
	
	 	alpha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(((CanvasPanel) canvas).getPoints().size());
				try {
					storeExample((ArrayList<Point>) ((CanvasPanel) canvas).getPoints(), 1 << 0, "examples.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	

	 	beta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(((CanvasPanel) canvas).getPoints().size());
				try {
					storeExample((ArrayList<Point>) ((CanvasPanel) canvas).getPoints(), 1 << 1, "examples.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		});
	

	 	gamma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(((CanvasPanel) canvas).getPoints().size());
				try {
					storeExample((ArrayList<Point>) ((CanvasPanel) canvas).getPoints(), 1 << 2, "examples.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	 	delta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(((CanvasPanel) canvas).getPoints().size());
				try {
					storeExample((ArrayList<Point>) ((CanvasPanel) canvas).getPoints(), 1 << 3, "examples.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	 	epsilon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(((CanvasPanel) canvas).getPoints().size());
				try {
					storeExample((ArrayList<Point>) ((CanvasPanel) canvas).getPoints(), 1 << 4, "examples.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	 	
	 	JPanel buttons = new JPanel();
	 	buttons.add(alpha);
	 	buttons.add(beta);
	 	buttons.add(gamma);
	 	buttons.add(delta);
	 	buttons.add(epsilon);
		
	 	container.add(buttons, BorderLayout.SOUTH); 
	 	
	}
	
	private void storeExample(ArrayList<Point> pts, int mask, String path) throws IOException {
		Writer out = new BufferedWriter(new FileWriter(path, true));
		ArrayList<Point> features = Point.getFeatures(pts, 20);
		for (Point f : features) {
			out.write(f.toString());
		}
		for (int i = 0; i < 5; ++i) {
			out.write((1 & (mask >> i)) + " "); 
		}
		out.write("\n");
		out.close();
	}
	
}
