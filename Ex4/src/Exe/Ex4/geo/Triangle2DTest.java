package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Triangle2D;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Triangle2DTest {
    Point2D p1 = new Point2D(3, 0);
    Point2D p2 = new Point2D(3, 3);
    Point2D p3 = new Point2D(7, 0);

    @Test
    void testArea() {
        double area = 6;
        Triangle2D t = new Triangle2D(p1, p2, p3);
        double t_area = t.area();
        assertEquals(t_area, area);
        for (int i = 0; i < 50; i++) {
            t.rotate(new Point2D(Math.random(), Math.random()), Math.random());
            assertEquals(Math.round(t.area() / Ex4_Const.EPS1) * Ex4_Const.EPS1, 6);
        }
        t.scale(new Point2D(0, 0), 2);
        assertEquals(Math.round(t.area() / Ex4_Const.EPS1) * Ex4_Const.EPS1, 24);

    }

    @Test
    void testContains() {
        Point2D p = new Point2D(7, 1);
        Triangle2D t = new Triangle2D(p1, p2, p3);
        assertFalse(t.contains(p));
        assertTrue(t.contains(new Point2D(5, 1.5)));
        for (int i = 0; i < 50; i++) {
            t.rotate(new Point2D(Math.random(), Math.random()), Math.random());
            Point2D center = new Point2D((t.getCenter()));
            assertTrue(t.contains(center));
        }
        assertTrue(t.contains(new Point2D(3, 1.5)));
    }

    @Test
    void testMove() {
        Triangle2D t = new Triangle2D(p1, p2, p3);
        double area = t.area();
        t.move(new Point2D(10, 5));
        assertEquals(t.get_p1().x(), 13);
        assertNotEquals(t._p3.y(), 0);
        assertEquals(t.area(), area);
    }

    @Test
    void testScale() {
        Triangle2D t = new Triangle2D(p1, p2, p3);
        Point2D center = t.getCenter();
        t.scale(new Point2D(13,13),-1);
        t.scale(new Point2D(13,13),-1);
        assertTrue(t.getCenter().close2equals(center));
        for (int i = 0; i < 50; i++) {
            double r = Math.abs(Math.random());
            t.scale(new Point2D(Math.random(), Math.random()), r);
            t.move(new Point2D(Math.random(), Math.random()));
            t.scale(new Point2D(Math.random(), Math.random()), 1 / r);
            assertEquals(Math.round(t.area() / Ex4_Const.EPS1) * Ex4_Const.EPS1, 6);
        }

    }

    @Test
    void testPerimeter() {
        Triangle2D t = new Triangle2D(p1, p2, p3);
        double ogPerimeter = t.perimeter();
        for (int i = 0; i < 50; i++) {
            double r = Math.abs(Math.random());
            t.scale(new Point2D(125, 15), r);
            assertEquals(Math.round(t.perimeter() / Ex4_Const.EPS1), Math.round(ogPerimeter * r / Ex4_Const.EPS1));
            t.scale(new Point2D(1345, 36), 1 / r);
            assertEquals(Math.round(t.perimeter() / Ex4_Const.EPS1) * Ex4_Const.EPS1, 12);
        }

    }

    @Test
    void testGetPoints() {
        Triangle2D t = new Triangle2D(p1, p2, p3);
        Point2D[] points = t.getPoints();
        assertEquals(p1, points[0]);
        assertNotEquals(p2, points[2]);
    }

    @Test
    void testToString() {
        Triangle2D t = new Triangle2D(p1, p2, p3);
        String str = "Triangle2D,3.0,0.0,3.0,3.0,7.0,0.0";
        assertEquals(t.toString(), str);
    }

    @Test
    void testCopy() {
        Triangle2D t = new Triangle2D(p1, p2, p3);
        GeoShapeable t1 = t.copy();
        Triangle2D t2 = t;
        assertNotEquals(System.identityHashCode(t), System.identityHashCode(t1));
        assertEquals(System.identityHashCode(t), System.identityHashCode(t2));
    }
    @Test
    void testRotate(){
        Triangle2D t = new Triangle2D(p1,p2,p3);
        t.rotate(new Point2D(15,25),90);
        assertTrue(t.get_p1().close2equals(new Point2D(40,13)));
        t.rotate(new Point2D(15,25),-90);
        assertTrue(t.get_p2().close2equals(p2));
        assertFalse(t.get_p1().close2equals(p3));
    }
}
