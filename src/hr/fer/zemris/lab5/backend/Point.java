package hr.fer.zemris.lab5.backend;

import java.util.ArrayList;

/**
 * CLass which models a single point drawn on a canvas. 
 * 
 * @author ipaljak
 *
 */

public class Point {

	public double x, y;
	
	public Point() {}
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static double getDist(Point A, Point B) {
		return Math.sqrt((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
	}
	
	private static void scale(ArrayList<Point> pts) {
		
		Point Tc = new Point();
		for (Point p : pts) {
			Tc.x += p.x;
			Tc.y += p.y;
		}
		
		Tc.x /= (double) pts.size();
		Tc.y /= (double) pts.size();
		
		double mx = 0, my = 0;
		for (Point p : pts) {
			p.x -= Tc.x;
			p.y -= Tc.y;
			mx = Math.max(mx, Math.abs(p.x));
			my = Math.max(my, Math.abs(p.y));
		}
		
		double m = Math.max(mx, my);
		for (Point p : pts) {
			p.x /= m;
			p.y /= m;
		}
		
	}
	
	private static double getGestureLength(ArrayList<Point> pts) {
		double ret = 0;
		for (int i = 1; i < pts.size(); ++i) { 
			ret += getDist(pts.get(i - 1), pts.get(i));
		}
		return ret;
	}
	
	public static ArrayList<Point> getFeatures(ArrayList<Point> pts, int M) {
		
		ArrayList<Point> ret = new ArrayList<Point>();
		
		scale(pts);
		double D = getGestureLength(pts);
		
		double block = D / (M - 1);
		double nextDist = block, currDist = 0; 
		
		ret.add(pts.get(0));
		
		for (int i = 1; i < pts.size(); ++i) {
			currDist += getDist(pts.get(i - 1), pts.get(i));
			if (currDist >= nextDist) {
				nextDist += block;
				ret.add(pts.get(i));
			}
		}
		
		while (ret.size() != M) 
			ret.add(pts.get(pts.size() - 1));
		
		return ret;	
		
	}
	
	public static ArrayList<Double> fromFeatures(ArrayList<Point> fts) {
		ArrayList<Double> ret = new ArrayList<Double>();
		for (Point p : fts) {
			ret.add(p.x);
			ret.add(p.y);
		}
		return ret;
	}

	@Override
	public String toString() {
		return x + " " + y + " ";
	}
	
}