package Exe.Ex4.geo;
import Exe.Ex4.Ex4_Const;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.gui.Ex4;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Polygon2DTest {
    Point2D[] points = {new Point2D(4,0),new Point2D(2,-4),new Point2D(-2,-4),new Point2D(-4,0),new Point2D(-2,4),new Point2D(2,4)};
    Polygon2D poly = new Polygon2D(points);
    @Test
    void testArea(){


        Triangle2D t1 = new Triangle2D(new Point2D(0,0),new Point2D(4,0),new Point2D(2,-4));
        Triangle2D t2 = new Triangle2D(new Point2D(0,0),new Point2D(-2,-4),new Point2D(2,-4));
        double area = 4*t2.area()+2*t1.area();
        double p_area = poly.area();
        assertEquals(p_area, area);
        for (int i = 0; i < 50; i++) {
            poly.rotate(new Point2D(Math.random(), Math.random()), Math.random());
            assertEquals(Math.round(poly.area() / Ex4_Const.EPS1) * Ex4_Const.EPS1, area);
        }
    }
    @Test
    void testContains(){
        assertTrue(poly.contains(new Point2D(0,0)));
        assertTrue(poly.contains(new Point2D(4,0)));
        assertFalse(poly.contains(new Point2D(-5,0)));
        assertFalse(poly.contains(new Point2D(6,0)));
    }
    @Test
    void testMove(){
        Point2D[] points2 = poly.getPoints();
        poly.move(new Point2D(5,5));
        for (int i=0;i<points.length;i++){
            assertEquals(points[i].x(),points2[i].x()+5);
            assertNotEquals(points2[i].y(),points[i].y());

        }
    }
    @Test
    void testScale(){
        double perimeter = poly.perimeter();
        Point2D[] points2 = poly.getPoints();
        poly.scale(new Point2D(13,13),-1);
        poly.scale(new Point2D(13,13),-1);
        assertTrue(Arrays.equals(points2, points));
        for (int i = 0; i < 50; i++) {
            double r = Math.abs(Math.random());
            poly.scale(new Point2D(Math.random(), Math.random()), r);
            poly.move(new Point2D(Math.random(), Math.random()));
            poly.scale(new Point2D(Math.random(), Math.random()), 1 / r);
            assertEquals(Math.round(poly.area() / Ex4_Const.EPS1) * Ex4_Const.EPS1, 48);
        }

    }
    @Test
    void testPerimeter(){

        double ogPerimeter = poly.perimeter();
        double perimeter = 4*points[0].distance(points[1])+8;
        assertEquals(Math.round(ogPerimeter/Ex4_Const.EPS1),Math.round(perimeter/Ex4_Const.EPS1));
        for (int i = 0; i < 50; i++) {
            double r = Math.abs(Math.random());
            poly.scale(new Point2D(125, 15), r);
            assertEquals(Math.round(poly.perimeter() / Ex4_Const.EPS1), Math.round(ogPerimeter * r / Ex4_Const.EPS1));
            poly.scale(new Point2D(1345, 36), 1 / r);
            assertEquals(Math.round(poly.perimeter() / Ex4_Const.EPS1) , Math.round(ogPerimeter/Ex4_Const.EPS1));
        }

    }
    @Test
    void testGetPoints(){
        Point2D[] points1 = poly.getPoints();
        assertTrue(Arrays.equals(points1,points));
    }
    @Test
    void testCopy(){
        GeoShapeable poly2 = poly.copy();
        Polygon2D poly1 = poly;
        assertNotEquals(System.identityHashCode(poly), System.identityHashCode(poly2));
        assertEquals(System.identityHashCode(poly1),System.identityHashCode(poly1));

    }
    @Test
    void testRotate(){
        poly.rotate(new Point2D(0,0),60);
        poly.rotate(new Point2D(0,0),-60);
        assertTrue(points[0].close2equals(new Point2D(4,0)));
        poly.rotate(new Point2D(0,0),180);
        assertTrue(points[3].close2equals(new Point2D(4,0)));
        poly.rotate(new Point2D(0,0),360);
        assertTrue(points[3].close2equals(new Point2D(4,0)));
        poly.rotate(new Point2D(0,0),180);
        assertTrue(points[0].close2equals(new Point2D(4,0)));
    }
    @Test
    void testToString(){
        String str ="Polygon2D,4.0,0.0,2.0,-4.0,-2.0,-4.0,-4.0,0.0,-2.0,4.0,2.0,4.0";
        assertEquals(poly.toString(),(str));
    }
}
