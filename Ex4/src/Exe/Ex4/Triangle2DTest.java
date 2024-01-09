package Exe.Ex4;
import static org.junit.jupiter.api.Assertions.*;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Triangle2D;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
public class Triangle2DTest {
    Point2D p1 = new Point2D(3,0);
    Point2D p2 = new Point2D(3,3);
    Point2D p3 =new Point2D(7,0);
    @Test
   void testArea(){
        double area = 6;
        Triangle2D t = new Triangle2D(p1,p2,p3);
        double t_area = t.area();
        assertEquals(t_area,area);
    }
    @Test
    void testContains(){
        Point2D p  =new Point2D(7,1);
        Triangle2D t = new Triangle2D(p1,p2,p3);
        assertFalse(t.contains(p));
       assertTrue(t.contains(new Point2D(5,1.5)));
    }

}
