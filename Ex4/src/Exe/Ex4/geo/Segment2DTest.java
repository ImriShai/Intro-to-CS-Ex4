package Exe.Ex4.geo;
import static org.junit.jupiter.api.Assertions.*;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Segment2D;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class Segment2DTest {
    Point2D p1 = new Point2D(1,2);
    Point2D p2 = new Point2D(5,10);
    @Test
    void testContain(){
        Segment2D seg = new Segment2D(p1,p2);
        boolean test  =seg.contains(new Point2D(2,6));
        assertFalse(test);
        assertTrue(seg.contains(new Point2D(4,8)));
    }
    @Test
    void testArea(){
        Segment2D  seg = new Segment2D(p1,p2);
        assertNotEquals(seg.area(),234);
        assertEquals(seg.area(),0);
    }
    @Test
    void testPerimeter(){
        Segment2D seg = new Segment2D(p1,p2);
        seg.rotate(new Point2D(34,-52),52.252);
        assertEquals(Math.round(seg.perimeter()/ Ex4_Const.EPS1),Math.round(17.889/Ex4_Const.EPS1));
        assertNotEquals(seg.perimeter(),18);
    }
    @Test
    void testToString(){
        String check  = "Segment2D,1.0,2.0,5.0,10.0";
        assertEquals(check,new Segment2D(p1,p2).toString());
    }
    @Test
    void testMove(){
        for(int i =0;i<500;i++)
        {
            double x1 = Math.random();
            double x2 = Math.random();
            double y1 = Math.random();
            double y2 = Math.random();
            Segment2D seg1 = new Segment2D(new Point2D(x1,y1),new Point2D(x2,y2));
            double vecX = Math.random();
            double vecY = Math.random();
            seg1.move(new Point2D(vecX,vecY));
            assertEquals(seg1.get_point1().x(),x1+vecX);
            assertEquals(seg1.get_point2().y(),y2+vecY);
        }
        Segment2D seg = new Segment2D(new Point2D(7,8),new Point2D(941,4));
        seg.move(new Point2D(0,0));
        assertTrue(seg.get_point2().close2equals(new Point2D(941,4)));

    }
    @Test
    void testRotate(){
        Segment2D seg = new Segment2D(new Point2D(3,3),new Point2D(9,9));
        seg.rotate(new Point2D(0,0),180);
        assertTrue(seg.get_point2().close2equals(new Point2D(-9,-9)));
        seg.rotate(new Point2D(0,0),360);
        assertTrue(seg.get_point1().close2equals(new Point2D(-3,-3)));
    }
    @Test
    void testScale(){
        Segment2D seg = new Segment2D(new Point2D(3,3),new Point2D(9,9));
        seg.scale(new Point2D(13,13),-1);
        seg.scale(new Point2D(13,13),-1);
        assertTrue(seg.get_point2().close2equals(new Point2D(9,9)));
        seg.scale(new Point2D(0,0),0.9);
        assertEquals(seg.getSlope(),1.0);
        seg.scale(new Point2D(0,0),0.9);
        assertEquals(Math.round(seg.perimeter()/Ex4_Const.EPS1),Math.round(13.746/Ex4_Const.EPS1));
    }
    @Test
    void testCopy(){
        Segment2D seg = new Segment2D(p1,p2);
        GeoShapeable seg1 = seg.copy();
        Segment2D seg2 = seg;
        assertNotEquals(System.identityHashCode(seg),System.identityHashCode(seg1));
        assertEquals(System.identityHashCode(seg),System.identityHashCode(seg2));
    }
    @Test
    void testGetPoints(){
        Segment2D seg1 = new Segment2D(p1,p2);
        Point2D[] points = seg1.getPoints();
        assertEquals(p1,points[0]);
        assertEquals(p2,points[1]);
    }



}
