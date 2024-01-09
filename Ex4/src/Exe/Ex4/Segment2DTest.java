package Exe.Ex4;
import static org.junit.jupiter.api.Assertions.*;

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
    void contain(){
        Segment2D seg = new Segment2D(p1,p2);
        boolean test  =seg.contains(new Point2D(2,6));
        assertFalse(test);
        assertTrue(seg.contains(new Point2D(4,8)));
    }

}
