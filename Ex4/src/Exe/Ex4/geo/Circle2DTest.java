package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Triangle2D;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Circle2DTest {
    Point2D center = new Point2D(5, 5);
    double radius = 3;
    Circle2D c1 = new Circle2D(center, radius);

    @Test
    void testArea() {
        for (int i = 0; i < 50; i++) {
            c1.rotate(new Point2D(Math.random(), Math.random()), Math.random());
            assertEquals(c1.area(), 9 * Math.PI);
            c1.scale(new Point2D(0, 0), 2);
            assertEquals(Math.round(c1.area() / Ex4_Const.EPS1), Math.round(36 * Math.PI / Ex4_Const.EPS1));
            c1.set_radius(radius);
        }
    }

    @Test
    void testContains() {
        for (int i = 0; i < 50; i++) {
            c1.rotate(new Point2D(Math.random(), Math.random()), Math.random());
            c1.scale(new Point2D(Math.random(), Math.random()), Math.random());
            assertTrue(c1.contains(c1.get_center()));
        }
        c1 = new Circle2D(center, radius);
        assertTrue(c1.contains(new Point2D(2, 5)));
        assertFalse(c1.contains(new Point2D(0, 0)));
    }

    @Test
    void testMove() {
        c1.move(new Point2D(0, 0));
        assertTrue(c1.get_center().close2equals(center));
        c1.move(new Point2D(5, 5));
        assertEquals(c1.getRadius(), 3);
        assertTrue(c1.get_center().close2equals(new Point2D(10, 10)));
    }

    @Test
    void testScale() {
        c1.scale(new Point2D(10, 10), -1);
        assertTrue(c1.get_center().close2equals(new Point2D(15, 15)));
        c1.scale(new Point2D(10, 10), -1);
        assertTrue(c1.get_center().close2equals(center));
    }

    @Test
    void testPerimeter() {
        double perimeter = c1.perimeter();
        for (int i = 0; i < 50; i++) {
            c1.move(new Point2D(Math.random(), Math.random()));
            c1.rotate(new Point2D(Math.random(), Math.random()), Math.random());
            assertEquals(c1.perimeter(), perimeter);
        }
    }

    @Test
    void testGetPoint() {
        Point2D[] points = c1.getPoints();
        assertEquals(c1.get_center(), points[0]);
        assertEquals(new Point2D(5, 8), points[1]);
    }

    @Test
    void testToString() {
        c1.move(new Point2D(5, 5));
        String str = "Circle2D,10.0,10.0,3.0";
        assertEquals(c1.toString(), str);
    }

    @Test
    void testCopy() {
        GeoShapeable c2 = c1.copy();
        Circle2D c3 = c1;
        assertNotEquals(System.identityHashCode(c1), System.identityHashCode(c2));
        assertEquals(System.identityHashCode(c1), System.identityHashCode(c3));
    }

    @Test
    void testRotate() {
        for (int i = 1; i < 50; i++) {
            c1.rotate(new Point2D(0,0),90);
            if (i%4==0)assertTrue(c1.get_center().close2equals(new Point2D(5,5)));
            else if (i%4==1)assertTrue(c1.get_center().close2equals(new Point2D(-5,5)));
            else if (i%4==2)assertTrue(c1.get_center().close2equals(new Point2D(-5,-5)));
            else assertTrue(c1.get_center().close2equals(new Point2D(5,-5)));
        }

    }
}

