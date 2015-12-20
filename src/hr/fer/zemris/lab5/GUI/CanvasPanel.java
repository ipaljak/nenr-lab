package hr.fer.zemris.lab5.GUI;

import hr.fer.zemris.lab5.backend.Point;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel {

	private List<Point> points;
	
	public CanvasPanel() {
		this.setBackground(new java.awt.Color(255, 255, 255));
	    
	    this.addMouseListener(new MouseAdapter() {
		
	    	public void mousePressed(MouseEvent evt) {
	    		canvasMousePressed(evt);
	    	}
	    	
	    	public void mouseReleased(MouseEvent evt) {
	    		canvasMouseReleased(evt);
	    	}
	    	
	    });
	    
	    this.addMouseMotionListener(new MouseMotionAdapter() {
		
	    	public void mouseDragged(MouseEvent evt) {
	    		canvasMouseDragged(evt);
	    	}
	    
	    });
	    

	}
	
	public List<Point> getPoints() {
		return this.points;
	}
	
	private void canvasMousePressed(MouseEvent evt) {
		this.points = new ArrayList<Point>();
		points.add(new Point(evt.getX(), evt.getY()));
	}
	
	private void canvasMouseReleased(MouseEvent evt) {
		points.add(new Point(evt.getX(), evt.getY()));
		this.repaint();
	}
	
	private void canvasMouseDragged(MouseEvent evt) {
		points.add(new Point(evt.getX(), evt.getY()));
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if (points != null && points.size() > 1) {
			Point from = points.get(0);
			for (int i = 1; i < points.size(); ++i) {
				Point to = points.get(i);
				g.drawLine((int) from.x, (int) from.y, (int) to.x, (int) to.y);
				from = to;
			}
		}
		
	}
	
}
