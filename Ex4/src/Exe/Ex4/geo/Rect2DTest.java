package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.gui.Ex4;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.color.ICC_ProfileRGB;

public class Rect2DTest {
    Point2D p1 = new Point2D(0, 0);
    Point2D p2 = new Point2D(10, 10);
    Point2D p3 = new Point2D(0, 10);
    Point2D p4 = new Point2D(10, 0);
    Rect2D rect = new Rect2D(p1, p2, p3, p4);

    @Test
    void testArea() {

        for (int i = 0; i < 50; i++) {
            rect.rotate(new Point2D(Math.random(), Math.random()), Math.random());
            assertEquals(Math.round(rect.area() / Ex4_Const.EPS1) * Ex4_Const.EPS1, 100);
        }
        rect.scale(new Point2D(0, 0), 1.1);
        assertEquals(Math.round(rect.area() / Ex4_Const.EPS1) * Ex4_Const.EPS1, 121);

    }

    @Test
    void testContains() {
        for (int i = 0; i < 50; i++) {
            rect.rotate(new Point2D(Math.random(), Math.random()), Math.random());
            double centerX = Math.min(rect.get_p1().x(), rect.get_p3().x())+((Math.abs(rect.get_p1().x())+Math.abs(rect.get_p3().x()))/2);
            double centerY = Math.min(rect.get_p1().y(), rect.get_p3().y())+((Math.abs(rect.get_p1().y())+Math.abs(rect.get_p3().y()))/2);
            Point2D center = new Point2D(centerX,centerY);
            boolean check = rect.contains(center);
            assertTrue(check);
        }

    }

    @Test
    void testMove() {
        rect.move(new Point2D(7.5, 19));
        assertEquals(rect.get_p1().x(), 7.5);
        assertNotEquals(rect.get_p4().y(), 19.5);
    }

    @Test
    void testScale() {
        //might be a rounding error because of the random scale ratio
        double ogArea = rect.area();
        rect.scale(new Point2D(13,13),-1);
        rect.scale(new Point2D(13,13),-1);
        assertEquals(rect.area(),ogArea
        );
        for (int i = 0; i < 50; i++) {
            double r = Math.abs(Math.random());
            rect.scale(new Point2D(125, 15), r);
            assertEquals(Math.round(rect.area() / Ex4_Const.EPS1), Math.round(ogArea * r * r / Ex4_Const.EPS1));
            rect.scale(new Point2D(1345, 36), 1 / r);
            assertEquals(Math.round(rect.area() / Ex4_Const.EPS1) * Ex4_Const.EPS1, 100);
        }

    }

    @Test
    void testPerimeter() {
        for (int i = 0; i < 50; i++) {
            double r = Math.abs(Math.random());
            rect.rotate(new Point2D(80, 80), r);
            rect.move(new Point2D(Math.random(),Math.random()));
            assertEquals(Math.round(rect.perimeter() / Ex4_Const.EPS1) * Ex4_Const.EPS1, 40);

        }
    }

    @Test
    void testGetPoints() {
        Point2D[] points = rect.getPoints();
        assertEquals(p1, points[0]);
        assertNotEquals(p2, points[3]);
    }

    @Test
    void testToString() {
        String str = "Rect2D,0.0,0.0,0.0,10.0,10.0,10.0,10.0,0.0";
        assertEquals(rect.toString(), str);
    }

    @Test
    void testCopy() {

        GeoShapeable rect1 = rect.copy();
        Rect2D rect2 = rect;
        assertNotEquals(System.identityHashCode(rect), System.identityHashCode(rect1));
        assertEquals(System.identityHashCode(rect), System.identityHashCode(rect2));
    }

    @Test
    void testRotate() {
        rect.rotate(new Point2D(0, 0), 180);
        assertTrue(rect.get_p1().close2equals(new Point2D(0, 0)));
        rect.rotate(new Point2D(0, 0), 180);
        assertTrue(rect.get_p3().close2equals(new Point2D(10, 10)));
        assertTrue(rect.get_p2().close2equals(new Point2D(0, 10)));
        assertFalse(rect.get_p3().close2equals(p1));

    }


}
