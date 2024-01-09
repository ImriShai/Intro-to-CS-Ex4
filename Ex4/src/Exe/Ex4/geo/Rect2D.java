package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class Rect2D implements GeoShapeable {
    private Point2D _p1, _p2, _p3, _p4;


    public Rect2D(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
        _p1 = p1;
        _p2 = p2;
        _p3 = p3;
        _p4 = p4;
        sortPoints();
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

    public Point2D get_p4() {
        return _p4;
    }


    @Override
    public String toString() {

        //return a string with the name of the class and the points of the shape
        String ans = "Rect2D," + _p1.toString() + "," + _p2.toString() + "," + _p3.toString() + "," + _p4.toString();
        return ans;
    }


    @Override
    public boolean contains(Point2D ot) {
        //dividing the rect to 2 triangles by the diagonal of the rect, and check if at least one of the triangles
        //contains the point
        Triangle2D t1 = new Triangle2D(_p1, _p2, _p3);
        Triangle2D t2 = new Triangle2D(_p1, _p3, _p4);
        return t1.contains(ot) || t2.contains(ot);

    }

    @Override
    public double area() {
        //Using the polygon function to calculate the area
        Polygon2D poly = new Polygon2D(this.getPoints());
        return poly.area();
    }

    @Override
    public double perimeter() {
        //Using the polygon function to calculate the perimeter
        Polygon2D poly = new Polygon2D(this.getPoints());
        return poly.perimeter();
    }

    @Override
    public void move(Point2D vec) {

        //given a vector vec,we move each point of the object by vec, using move method in Point2D
        _p1.move(vec);
        _p2.move(vec);
        _p3.move(vec);
        _p4.move(vec);

    }

    @Override
    public GeoShapeable copy() {
        //returning a GeoShapeable deep copy of the object
        return new Rect2D(_p1, _p2, _p3, _p4);
    }


    @Override
    public void scale(Point2D center, double ratio) {
        //given a point and a ratio, we scale each point of the object using scale method in Point2D
        _p1.scale(center, ratio);
        _p2.scale(center, ratio);
        _p3.scale(center, ratio);
        _p4.scale(center, ratio);
    }

    @Override
    public void rotate(Point2D center, double angleDegrees) {
//given a point and a ratio, we scale each point of the object using scale method in Point2D
        _p1.rotate(center, angleDegrees);
        _p2.rotate(center, angleDegrees);
        _p3.rotate(center, angleDegrees);
        _p4.rotate(center, angleDegrees);
        sortPoints();

    }

    @Override
    public Point2D[] getPoints() {
        //returns an array of all the points creating the object, while keeping the order of the points.
        Point2D[] ans = new Point2D[4];
        ans[0] = new Point2D(get_p1());
        ans[1] = new Point2D(get_p2());
        ans[2] = new Point2D(get_p3());
        ans[3] = new Point2D(get_p4());
        return ans;
    }

    public void sortPoints() {
        // a private function to make sure the points stays at the correct order of the rect, meaning each side of the
        // rect is linked to the corresponding one, from each direction: p1-->p2-->p3-->p4-->p1, such that p1 and p3
        // creates a diagonal
        double p1ToP2 = _p1.distance(_p2);
        double p1Top3 = _p1.distance(_p3);
        double p1Top4 = _p1.distance(_p4);
        Point2D temp;
        if (p1ToP2 > p1Top3 && p1ToP2 > p1Top4) {
            temp = new Point2D(_p2);
            _p2 = new Point2D(_p3);
            _p3 = new Point2D(temp);
        } else if (p1Top4 > p1ToP2 && p1Top4 > p1Top3) {
            temp = new Point2D(_p4);
            _p3 = new Point2D(_p4);
            _p4 = new Point2D(temp);
        }

    }
}



