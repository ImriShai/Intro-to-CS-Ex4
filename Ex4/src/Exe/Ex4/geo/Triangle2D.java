package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{
	Point2D _p1,_p2,_p3;

	public Triangle2D(Point2D p1, Point2D p2, Point2D p3){
		_p1 = p1;
		_p2 = p2;
		_p3 = p3;

	}

	public Point2D get_p1() {
		return _p1;
	}

	public Point2D get_p2() {
		return _p2;
	}

	public Point2D get_p3() {
		return _p3;
	}

	@Override
	public String toString() {
		//return a string with the name of the class and the points of the shape
		String to_return = "Triangle2D,"+_p1.toString() + ","+ _p2.toString()+","+_p3.toString();
		return to_return;
	}


	@Override
	public boolean contains(Point2D ot) {
		//dividing the triangle to 3 smaller triangles, using ot and 2 of the 3 vertex of the triangle for each small one
		//then, if the sum of the areas of the 3 smaller ones is equal to the actual one, the point is inside.
		Triangle2D t1 = new Triangle2D(_p1,_p2,ot);
		Triangle2D t2 = new Triangle2D(_p1,_p3,ot);
		Triangle2D t3 = new Triangle2D(_p2,_p3,ot);
		double t1_area = t1.area();
		double t2_area = t2.area();
		double t3_area = t3.area();
		double full_area = Math.round((t1_area+t2_area+t3_area)/Ex4_Const.EPS1);
		double rounded_real = Math.round(this.area()/Ex4_Const.EPS1);
		if (full_area==rounded_real)return true;
		return false;
	}

	@Override
	public double area() {
		//using the formula: S = sqrt((SemiPerimeter*(SemiPerimeter-a)*(SemiPerimeter-b)*(SemiPerimeter-c))
		//when a, b and c are the distances between each vertex.
		double s = perimeter()/2;
		double a = _p1.distance(_p2);
		double b = _p1.distance(_p3);
		double c = _p2.distance(_p3);
		double ans = Math.sqrt(s*(s-a)*(s-b)*(s-c));
		return ans;
	}

	@Override
	public double perimeter() {
		//the sum of the distances between each vertex
		double ans = _p1.distance(_p2)+_p1.distance(_p3)+ _p2.distance(_p3);
		return ans;
	}
	public Point2D getCenter(){
		Point2D center = new Point2D((Math.abs(_p1.x())+Math.abs(_p2.x())+Math.abs(_p3.x()))/3,(Math.abs(_p1.y())+Math.abs(_p2.y())+Math.abs(_p3.y()))/3);
		return center;
	}

	@Override
	public void move(Point2D vec) {
		//given a vector vec,we move each point of the object by vec, using move method in Point2D
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		//returning a GeoShapeable deep copy of the object
		return new Triangle2D(_p1,_p2,_p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		//given a point and a ratio, we scale each point of the object using scale method in Point2D
		_p1.scale(center,ratio);
		_p2.scale(center,ratio);
		_p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		//given a point and a ratio, we scale each point of the object using scale method in Point2D
		_p1.rotate(center,angleDegrees);
		_p2.rotate(center,angleDegrees);
		_p3.rotate(center,angleDegrees);
		
	}

	@Override
	public Point2D[] getPoints() {
		//returns an array of all the points creating the object, while keeping the order of the points.
		Point2D[] ans = new Point2D[3];
		ans[0] =_p1;
		ans[1]=_p2;
		ans[2] =_p3;
		return ans;
	}
	
}
