package Exe.Ex4.geo;

import java.util.Arrays;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * <p>
 * You should update this class!
 *
 * @author boaz.benmoshe
 */
public class Polygon2D implements GeoShapeable {
    Point2D[] _points;

    public Polygon2D(Point2D[] points) {
        _points = points;
    }


    public String toString() {
        //return a string with the name of the class and the points of the shape
        String to_return = "Polygon2D,";
        Point2D[] points = getPoints();
        for (int i = 0; i < points.length-1; i++) {
            to_return = to_return + points[i] + ",";
        }
        to_return+=points[points.length-1].toString();
        return to_return;
    }


    @Override
    public boolean contains(Point2D ot) {
        //based on ray casting algorithm - for each side of the polygon we check if and where it intersects with
        //the "ray" linear. then we check if the point is on the side of the polygon, meaning it in the correct x position,
        //then we check if the intersection is to the RIGHT of the point given. for each side we count if the intersection
        //was successful , if so we add 1 to our count. after we completed the iterations, we check if the number of the
        //intersection is odd. if so, the point is inside.
        //We check also for a case when the linear we created intersects exactly on one of the vertexes.

        Segment2D seg = new Segment2D(ot, new Point2D(findMaxX()+1, ot.y()));
        int intersections = 0;
        Segment2D to_check;
        for (int i = 0; i < _points.length - 1; i++) {
            to_check = new Segment2D(_points[i], _points[i + 1]);
            if (to_check.contains(ot)) return true;
            else {
                Point2D inter_loc = intersection(seg, to_check);

                if (inter_loc!=null&&to_check.contains(inter_loc) && ot.x() <= inter_loc.x()) {
                    intersections++;
                }
            }
        }
        to_check = new Segment2D(_points[0], _points[_points.length - 1]);
        Point2D inter_loc = new Point2D(intersection(seg, to_check));
        if (inter_loc!=null&&to_check.contains(inter_loc)&&ot.x()<=inter_loc.x()) intersections++;
        for (int i =0;i<_points.length;i++){
            if (ot.y()==_points[i].y()&&_points[i].x()>ot.x())intersections++;
        }
        if (intersections % 2 == 1) return true;
        return false;
    }

    @Override
    public double area() {
        //based on the algorithm boaz added to the docs.
        double area = 0;
        int last_index = _points.length - 1;
        for (int i = 0; i < _points.length -1; i++) {
            area = area + (_points[i].x() * _points[i + 1].y()) - (_points[i + 1].x() * _points[i].y());
        }
        area = area + (_points[last_index].x() * _points[0].y()) - (_points[0].x() * _points[last_index].y());
        return Math.abs(area / 2);
    }

    @Override
    public double perimeter() {
        //iterating over each side, summing the length of each one.
        double perimeter = 0;
        for (int i = 0; i < _points.length - 1; i++) {
            perimeter = perimeter + _points[i].distance(_points[i + 1]);
        }
        perimeter = perimeter + _points[0].distance(_points[_points.length - 1]);

        return perimeter;
    }

    @Override
    public void move(Point2D vec) {
        //given a vector vec,we move each point of the object by vec, using move method in Point2D
        for (int i = 0; i < _points.length; i++) {
            _points[i].move(vec);
        }
    }

    @Override
    public GeoShapeable copy() {
        //returning a GeoShapeable deep copy of the object
        Point2D[] points = new Point2D[_points.length];
        for (int i = 0; i < _points.length; i++) {
            points[i] = new Point2D(_points[i]);
        }
        Polygon2D poly = new Polygon2D(points);
        return poly;
    }

    @Override
    public void scale(Point2D center, double ratio) {
        //given a point and a ratio, we scale each point of the object using scale method in Point2D
        for (int i = 0; i < _points.length; i++) {
            _points[i].scale(center, ratio);
        }

    }

    @Override
    public void rotate(Point2D center, double angleDegrees) {
        //given a point and a ratio, we scale each point of the object using scale method in Point2D
        for (int i = 0; i < _points.length; i++) {
            _points[i].rotate(center, angleDegrees);
        }

    }

    @Override
    public Point2D[] getPoints() {
        //returns an array of all the points creating the object, while keeping the order of the points.
        Point2D[] points = new Point2D[_points.length];
        for (int i = 0; i < _points.length; i++) {
            points[i] = new Point2D(_points[i]);
        }
        return points;
    }

    private Point2D intersection(Segment2D seg1, Segment2D seg2) {
        //calculate the intersection point of two linear lines using the formula y=mx+b.
        //if the lines are parallel to each other,return null
        double b1 = seg1.getB();
        double b2 = seg2.getB();
        double slope1 = seg1.getSlope();
        double slope2 = seg2.getSlope();
        if (slope1 == slope2) return null;
        double x = (b2 - b1) / (slope1 - slope2);
        double y = slope1 * x + b1;
        return new Point2D(x, y);

    }
    private double findMaxX(){
        // a private function used to find the max x of the vertexes in order to know what is the minimum x value for
        //the ray cast
        double max = 0;
        for(int i =0;i< _points.length;i++){
            if (_points[i].x()>max)max = _points[i].x();
        }
        return max;
    }
}
