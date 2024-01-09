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

public class Point2DTest {
    Point2D p1 = new Point2D(0, 0);
    Point2D p2 = new Point2D(5, 5);
    Point2D p3 = new Point2D(-22, -18);


    @Test
    void testMove() {
        p1.move(p2);
        assertEquals(p1.x(),5);
        assertNotEquals(p1.y(),-18);
    }
    @Test
    void testToString(){
        assertEquals(p1.toString(),"0.0,0.0");
        assertEquals(p2.toString(),"5.0,5.0");
        assertNotEquals(p3.toString(),"22.0,-18.0");
    }
    @Test
    void testScale(){
       Point2D p31 = new Point2D(p3);
        p1.scale(p2,2);
       assertEquals(p1,new Point2D(-5.0,-5.0));
       p3.scale(p2,-1);
       assertEquals(p3,new Point2D(32,28));
       p3.scale(p2,-1);
       assertEquals(p3,p31);
    }
    @Test
    void testRotate(){
        p2.rotate(p1,180);
        assertTrue(p2.close2equals(new Point2D(-5,-5)));
        p2.rotate(p1,180);
        assertTrue(p2.close2equals(new Point2D(5,5)));
        p3.rotate(p1,-360);
        assertTrue(p3.close2equals(new Point2D(-22,-18)));
    }
    @Test
    void testFromString(){
        Point2D p4 =new Point2D("5,5");
        assertEquals(p4,p2);
    }
}
