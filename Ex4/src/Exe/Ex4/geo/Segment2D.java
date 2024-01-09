package Exe.Ex4.geo;


import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D segment on the plane,
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class Segment2D implements GeoShapeable {
    private Point2D _point1, _point2;


    public Segment2D(Point2D point1, Point2D point2) {
        this._point1 = new Point2D(point1);
        this._point2 = new Point2D(point2);
    }

    public Point2D get_point1() {
        return _point1;
    }

    public Point2D get_point2() {
        return _point2;
    }

    public String toString() {
        return ("Segment2D,"+_point1.toString() + "," + _point2.toString());
    }//return a string with the name of the class and the points of the shape
    public  double getSlope(){
        return ((_point2.y()-_point1.y())/(_point2.x()-_point1.x()));
    }//returns the slope of the linear the segment representing
    public double getB(){
        return _point1.y()-getSlope()*_point1.x();
        //returns the intersection of the linear that the segment representing with tha Y axis

    }


    @Override
    public boolean contains(Point2D ot) {
        //First we check if the x value of the point given is in the range of the segment x's. if so,
        //using the formula y=mx+b, we check if the point is on the linear the segment representing.
        double real_y = getSlope()* ot.x()+getB();
        if (!(ot.x()>=Math.min(_point1.x(),_point2.x())&&ot.x()<=Math.max(_point1.x(),_point2.x())))return false;
        else{
            if (Math.round(ot.y()/ Ex4_Const.EPS1)==Math.round(real_y/Ex4_Const.EPS1))return true;
            else return false;
        }


    }

    @Override
    public double area() {
        //defined to be 0
        return 0;
    }

    @Override
    public double perimeter() {
        //defined to be the distance between the points*2
        return (_point2.distance(_point1)*2);
    }

    @Override
    public void move(Point2D vec) {
        //given a vector vec,we move each point of the object by vec, using move method in Point2D
        _point1.move(vec);
        _point2.move(vec);
    }

    @Override
    public GeoShapeable copy() {
       //returning a GeoShapeable deep copy of the object
        return new Segment2D(_point1, _point2);

    }

    @Override
    public void scale(Point2D center, double ratio) {
       //given a point and a ratio, we scale each point of the object using scale method in Point2D
        _point1.scale(center,ratio);
        _point2.scale(center,ratio);
    }

    @Override
    public void rotate(Point2D center, double angleDegrees) {
        //given a point that representing the center to rotate around, and an angle, we rotate each point of the object using rotate method in Point2D
        _point1.rotate(center,angleDegrees);
        _point2.rotate(center,angleDegrees);

    }

    @Override
    public Point2D[] getPoints() {
        //returns an array of all the points creating the object, while keeping the order of the points.
        Point2D[] ans = new Point2D[2];
        ans[0] = new Point2D(get_point1());
        ans[1] = new Point2D(get_point2());
        return ans;
    }

}