package Exe.Ex4.geo;

/** 
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle2D implements GeoShapeable{
	private Point2D _center;
	private double _radius;
	
	public Circle2D(Point2D cen, double rad) {
		this._center = new Point2D(cen);
		this._radius = Math.abs(rad);
	}
	public double getRadius() {return this._radius;}

	public Point2D get_center() {
		return _center;
	}

	@Override
	    public String toString()
			 //returns a string with the class name, then the center point and then the radius
	    { return "Circle2D,"+ _center.toString()+","+_radius;}
	@Override
	public boolean contains(Point2D ot) {
		//if the distance from the point to the center is smaller or equal to the radius, the point is inside
		double dist = ot.distance(this._center);
		return dist<=this._radius;
	}

	public void set_radius(double radius) {
		this._radius = Math.abs(radius);
	}

	@Override
	public double area() {
		//using the formula S=PI*(Radius^2)
		double ans = Math.PI * Math.pow(this._radius, 2);
		return ans;
	}
	@Override
	public double perimeter() {
		//using the formula P = 2*PI*Radius
		double ans = Math.PI * 2 * this._radius;
		return ans;
	}
	@Override
	//given a vector vec,we move each point of the object by vec, using move method in Point2D
	public void move(Point2D vec) {
		_center.move(vec);
	}
	@Override
	//returning a GeoShapeable deep copy of the object
	public GeoShapeable copy() {
		return new Circle2D(_center, _radius);
	}
	@Override
	//returns an array of points with the center then a new point that is the highest point of the circle
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[2];
		ans[0] =new Point2D(this._center);
		ans[1] = new Point2D(ans[0].x(), ans[0].y()+this._radius);
		return ans;
	}
	@Override
	public void scale(Point2D center, double ratio) {
		//given a point and a ratio, we scale each point of the object using scale method in Point2D
		//////////add your code below ///////////
		_radius = Math.abs(_radius*ratio);
		 _center.scale(center,ratio);
		//////////////////////////////////////////
	}
	@Override
	public void rotate(Point2D center, double angleDegrees) {
		//given a point and a ratio, we scale each point of the object using scale method in Point2D
		//////////add your code below ///////////
		_center.rotate(center,angleDegrees);
		//////////////////////////////////////////
	}

}
