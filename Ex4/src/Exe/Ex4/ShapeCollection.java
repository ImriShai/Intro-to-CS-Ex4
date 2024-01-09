package Exe.Ex4;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


import Exe.Ex4.geo.*;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable {
	private ArrayList<GUI_Shapeable> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}

	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		//given an index, the method remove the shape at the given index, and returns it
		//////////add your code below ///////////
		GUI_Shapeable removed = this.get(i).copy();
		_shapes.remove(i);
		return removed;
		//////////////////////////////////////////
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		//////////add your code below //////////
		//add a GUIShape object to a specified location in the collection
		if (s != null && s.getShape() != null&&i<=_shapes.size()) _shapes.add(i, s);
		//////////////////////////////////////////
	}

	@Override
	public void add(GUI_Shapeable s) {
		//add a GUIShape object to the end of the collection
		if (s != null && s.getShape() != null) {
			_shapes.add(s);
		}
	}

	@Override
	public ShapeCollectionable copy() {
		//the method return a deep copy of the collection
		//////////add your code below ///////////
		ShapeCollection deepCopyList = new ShapeCollection();
		if (_shapes != null) {
			for (int i = 0; i < _shapes.size(); i++) {
				deepCopyList.add(_shapes.get(i));
			}
			return deepCopyList;
		}
		return null;
		//////////////////////////////////////////
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		//////////add your code below ///////////
		//sort the collection using a given comparator
		if(comp!=null)_shapes.sort(comp);
		//////////////////////////////////////////
	}

	@Override
	public void removeAll() {
		//////////add your code below ///////////
		_shapes.clear();
		//////////////////////////////////////////
	}

	@Override
	public void save(String file) {
		//////////add your code below ///////////
		//saving the collection to the given file(there is an implementation of FileDialog at Ex4)

		try {
			FileWriter fw = new FileWriter(file);
			PrintWriter outs = new PrintWriter(fw);
			for (int i = 0; i < _shapes.size(); i++) {
				outs.println(_shapes.get(i).toString());
			}
			outs.close();
			fw.close();
		} catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}


		//////////////////////////////////////////
	}

	@Override
	public void load(String file) {
		////////// add your code below ///////////
		//loading a collection from the given file(there is an implementation of FileDialog at Ex4)
		GUIShape shape = null;
		try {
			//try block for reading
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str;
			str = br.readLine();
			if (str != null){
				try {
					//for each line, we try to create a GUIShape object out of it, based on the toString method
					shape = new GUIShape(fromString(str));//if the line is unreadable catch null
				}
				catch (NullPointerException ex){
					System.out.println("Error reading line\n"+ex);
				}
				if (shape!=null)_shapes.add(shape);//else add the shape to the collection
			}

			for (int i = 1; str != null; i = i + 1) {//iterating over each line of the file
				str = br.readLine();
				if (str != null) {
					try {
						 shape = new GUIShape(fromString(str));
					}
					catch (NullPointerException ex){
						System.out.println("Error reading line\n"+ex);
					}
					if (shape!=null)_shapes.add(shape);
				}
			}
			br.close();
		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
		}


		//////////////////////////////////////////
	}

	@Override
	public Rect2D getBoundingBox() {
		//////////add your code below ///////////
		//finding the values for the rectangle vertexes, and returns a Rect2D object as the bounding box of all the shapes
		//in the collection
		if(_shapes.size()==0) return null;
		double minX = 0,minY=0,maxX=0,maxY=0;
		for (int i = 0;i< _shapes.size();i++){
			Point2D[] points = _shapes.get(i).getShape().getPoints();
			for (int j =0; j<points.length;j++){
				if (points[j].x()>maxX) maxX=points[j].x();
				if (points[j].x()<minX) minX=points[j].x();
				if (points[j].y()>maxY) maxY=points[j].y();
				if (points[j].y()>minY) minY=points[j].y();
			}
		}
		Point2D p1 = new Point2D(minX,maxY);
		Point2D p2 = new Point2D(maxX,maxY);
		Point2D p3 = new Point2D(maxX,minY);
		Point2D p4 = new Point2D(minX,minY);
		return new Rect2D(p1,p2,p3,p4);
		//////////////////////////////////////////
	}

	@Override
	public String toString() {
		//iterating over the collection getting the toString of each GUIShape
		String ans = "";
		for (int i = 0; i < size(); i = i + 1) {
			ans += this.get(i);
		}
		return ans;
	}

	private GUIShape fromString(String str) {
		//given a line from the file:
		String[] line;
		GUIShape shape;
		int counter = 0;
		line = str.split(",");//splitting by commas
		if (!line[0].equals("GUIShape"))return null;//if the first word isn't "GUIShape" the line is unreadable
		int color = Integer.parseInt(line[1]);//the second object represent the rgb value of the shape color
		boolean fill = Boolean.parseBoolean(line[2]);//the third represent the fill value of the shape
		int tag = Integer.parseInt(line[3]);//the 4 represent the tag value of the shape
		String type = line[4];//the 5 represent the shape type
		if (type.equals("Circle2D")) {//if it's a circle,we take the radius from the 7'th, and the center point from 5th and 6th
			double rad = Double.parseDouble(line[7]);
			Point2D center = new Point2D(Double.parseDouble(line[5]), Double.parseDouble(line[6]));
			Circle2D c = new Circle2D(center, rad);
			Color col = new Color(color);
			shape = new GUIShape(c, fill, col, tag);//creates a GUIShape with all the properties we got
			return shape;
		}
		Point2D[] points = new Point2D[(line.length - 4) / 2];// if it's not a circle, we have an even number of "cells"
		//starting after the type cell. each two cells represent the x and y value of a point in the shape

		for (int i = 5; i < line.length; i = i + 2) {//iterates over the line, adding each two sequential cells as a point
			String toPoint = line[i] + "," + line[i + 1];
			points[counter] = new Point2D(toPoint);
			counter++;
		}
		//then, we have all we need to create a GUIShape object. we create it according to the type we have. then return

		if (type.equals("Rect2D")) {
			Rect2D rect = new Rect2D(points[0], points[1], points[2], points[3]);
			shape = (new GUIShape(rect, fill, new Color(color), tag));
			return shape;
		}
		if (type.equals("Triangle2D")) {
			Triangle2D t = new Triangle2D(points[0], points[1], points[2]);
			shape = (new GUIShape(t, fill, new Color(color), tag));
			return shape;
		}
		if (type.equals("Segment2D")) {
			Segment2D seg = new Segment2D(points[0], points[1]);
			shape = (new GUIShape(seg, fill, new Color(color), tag));
			return shape;
		}
		if (type.equals("Polygon2D")) {

			Polygon2D poly = new Polygon2D(points);
			GUIShape s1 = (new GUIShape(poly, fill, new Color(color), tag));
			return s1;
		}
		return null;
	}
	

}




