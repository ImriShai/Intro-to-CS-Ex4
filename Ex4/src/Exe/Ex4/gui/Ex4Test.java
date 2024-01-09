package Exe.Ex4.gui;
import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.geo.*;
import Exe.Ex4.gui.Ex4;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.awt.*;

public class Ex4Test {
    Point2D p1 = new Point2D(0, 0);
    Point2D p2 = new Point2D(10, 10);
    Point2D p3 = new Point2D(0, 10);
    Point2D p4 = new Point2D(10, 0);
    Rect2D rect = new Rect2D(p1, p2, p3, p4);
    Point2D p5 = new Point2D(3, 0);
    Point2D p6 = new Point2D(3, 3);
    Point2D p7 = new Point2D(7, 0);
    Triangle2D t = new Triangle2D(p5, p6, p7);
    Segment2D seg = new Segment2D(p1,p3);
    Point2D[] points = {new Point2D(4,0),new Point2D(2,-4),new Point2D(-2,-4),new Point2D(-4,0),new Point2D(-2,4),new Point2D(2,4)};
    Polygon2D poly = new Polygon2D(points);
    ShapeCollection s = new ShapeCollection();

    @Test
    void testInit(){

        for(int i =0;i<3;i++){

        }
    }
    @Test
    void testGetCollection(){
        s.add(new GUIShape(rect,false,Color.BLACK,0));
        s.add(new GUIShape(seg,false,Color.RED,0));
        ShapeCollection s1;
    }
}

