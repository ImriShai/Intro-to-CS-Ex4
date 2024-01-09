package Exe.Ex4.gui;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.*;

/**
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 *
 * @author boaz.benmoshe
 */
public class Ex4 implements Ex4_GUI {
    private ShapeCollectionable _shapes = new ShapeCollection();
    private GUI_Shapeable _gs;
    private Color _color = Color.blue;
    private boolean _fill = false;
    private String _mode = "";
    private Point2D _p1;
    private Point2D _p2;
    private boolean _is_done = false;
    public static int _tag = 0;
    private ArrayList<Point2D> points = new ArrayList<>();

    private static Ex4 _winEx4 = null;

    private Ex4() {
        init(null);
    }

    public void init(ShapeCollectionable s) {
        if (s == null) {
            _shapes = new ShapeCollection();
        } else {
            _shapes = s.copy();
        }
        GUI_Shapeable _gs = null;
        Polygon2D _pp = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        Point2D _p1 = null;
    }

    public void show(double d) {
        StdDraw_Ex4.setScale(0, d);
        StdDraw_Ex4.show();
        drawShapes();
    }

    public static Ex4 getInstance() {
        if (_winEx4 == null) {
            _winEx4 = new Ex4();
        }
        return _winEx4;
    }

    public void drawShapes() {
        StdDraw_Ex4.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable sh = _shapes.get(i);

            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        StdDraw_Ex4.show();
    }

    private static void drawShape(GUI_Shapeable g) {
        //drawing shapes using StdDraw given
        if (g != null) {
            StdDraw_Ex4.setPenColor(g.getColor());
            if (g.isSelected()) {
                StdDraw_Ex4.setPenColor(Color.gray);
            }
            GeoShapeable gs = g.getShape();
            boolean isFill = g.isFilled();
            if (gs instanceof Circle2D) {
                Circle2D c = (Circle2D) gs;
                Point2D cen = c.getPoints()[0];
                double rad = c.getRadius();
                if (isFill) {
                    StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
                } else {
                    StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
                }
            }
            if (gs instanceof Segment2D) {
                Segment2D s = (Segment2D) gs;
                Point2D p1 = s.get_point1();
                Point2D p2 = s.get_point2();
                StdDraw_Ex4.line(p1.x(), p1.y(), p2.x(), p2.y());
            }
            if (gs instanceof Rect2D) {
                Rect2D rect = (Rect2D) gs;
                rect.sortPoints();
                double[] x = {rect.get_p1().x(), rect.get_p2().x(), rect.get_p3().x(), rect.get_p4().x()};
                double[] y = {rect.get_p1().y(), rect.get_p2().y(), rect.get_p3().y(), rect.get_p4().y()};
                if (isFill) {
                    StdDraw_Ex4.filledPolygon(x, y);
                } else {
                    StdDraw_Ex4.polygon(x, y);
                }
            }
            if (gs instanceof Triangle2D) {
                Triangle2D t = (Triangle2D) gs;
                double[] x = new double[3];
                double[] y = new double[3];
                Point2D[] points = t.getPoints();
                for (int i = 0; i < 3; i++) {
                    x[i] = points[i].x();
                    y[i] = points[i].y();
                }

                if (isFill) {
                    StdDraw_Ex4.filledPolygon(x, y);
                } else {
                    StdDraw_Ex4.polygon(x, y);


                }
            }
            if (gs instanceof Polygon2D) {
                Polygon2D poly = (Polygon2D) gs;
                Point2D[] points_arr = poly.getPoints();
                double[] poly_x = new double[points_arr.length];
                double[] poly_y = new double[points_arr.length];
                for (int i = 0; i < points_arr.length; i++) {
                    poly_x[i] = points_arr[i].x();
                    poly_y[i] = points_arr[i].y();
                }
                if (isFill) {
                    StdDraw_Ex4.filledPolygon(poly_x, poly_y);
                } else {
                    StdDraw_Ex4.polygon(poly_x, poly_y);
                }

            }
        }
    }


    private void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }

    private void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }

    public void actionPerformed(String p) {
        //when the user clicks on one the below, we take the action accordingly
        _mode = p;
        if (p.equals("Blue")) {
            _color = Color.BLUE;
            setColor(_color);
        }
        if (p.equals("Red")) {
            _color = Color.RED;
            setColor(_color);
        }
        if (p.equals("Green")) {
            _color = Color.GREEN;
            setColor(_color);
        }
        if (p.equals("White")) {
            _color = Color.WHITE;
            setColor(_color);
        }
        if (p.equals("Black")) {
            _color = Color.BLACK;
            setColor(_color);
        }
        if (p.equals("Yellow")) {
            _color = Color.YELLOW;
            setColor(_color);
        }
        if (p.equals("Fill")) {
            _fill = true;
            setFill();
        }
        if (p.equals("Empty")) {
            _fill = false;
            setFill();
        }
        if (p.equals("Clear")) {
            _shapes.removeAll();
        }
        if (p.equals("ByArea")) {
            _shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Area));
        }
        if (p.equals("ByAntiArea")) {
            _shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Area));
        }
        if (p.equals("ByToString")) {
            _shapes.sort(new ShapeComp(Ex4_Const.Sort_By_toString));
        }
        if (p.equals("ByAntiToString")) {
            _shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_toString));
        }
        if (p.equals("ByPerimeter")) {
            _shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Perimeter));
        }
        if (p.equals("ByAntiPerimeter")) {
            _shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter));
        }
        if (p.equals("ByTag")) {
            _shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Tag));
        }
        if (p.equals("ByAntiTag")) {
            _shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Tag));
        }
        if (p.equals("Load")) {
            int size = _shapes.size();
            String file = loadFile();//calling the FileDialog to get the file dir and name
            if (file != null) {
                _shapes.load(file);
                if (_shapes.size() != size) {
                    for (int i = 0; i < size; i++) {
                        _shapes.removeElementAt(0);//if the load was successful, remove the shapes that was there before
                    }
                }
            }
        }
        if (p.equals("Save")) {

            String file = save_loc();//calling FileDialog
            if (file != null) {
                _shapes.save(file);
            }
        }
        if (p.equals("Remove")) {

            for (int i = _shapes.size(); i > 0; i--) {
                if (_shapes.get(i - 1).isSelected()) {
                    _shapes.removeElementAt(i - 1);
                }
            }

        }
        if (p.equals("All")) {
            for (int i = 0; i < _shapes.size(); i++) {
                _shapes.get(i).setSelected(true);
            }
        }
        if (p.equals("None")) {
            for (int i = 0; i < _shapes.size(); i++) {
                _shapes.get(i).setSelected(false);
            }
        }
        if (p.equals("Anti")) {
            for (int i = 0; i < _shapes.size(); i++) {
                if (_shapes.get(i).isSelected()) {
                    _shapes.get(i).setSelected(false);

                } else
                    _shapes.get(i).setSelected(true);
            }
        }
        if (p.equals("Info")) {
            System.out.println(getInfo());
        }


        drawShapes();

    }


    public void mouseClicked(Point2D p) {
        //Actions based on user clicks location and mode
        System.out.println("Mode: " + _mode + "  " + p);
        if (_mode.equals("Circle")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _gs.setTag(_tag);
                _tag++;
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }

        }
        if (_mode.equals("Segment")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _gs.setTag(_tag);
                _tag++;
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;

            }
        }
        if (_mode.equals("Rect")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _gs.setTag(_tag);
                _tag++;
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point2D(p);
            } else {
                _p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }
        if (_mode.equals("Copy")) {
            if (_p1 == null) {
                _p1 = new Point2D(p);
            } else {
                _p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
                for (int i = 0; i < _shapes.size(); i++) {
                    if (_shapes.get(i).isSelected()) {
                        _shapes.add(_shapes.get(i).copy());
                        _shapes.get(_shapes.size() - 1).getShape().move(_p1);//creating a new shape, then moving it to the
                        //provided location on the canvas
                    }
                }
                _p1 = null;

            }
        }

        if (_mode.equals("Rotate")) {
            if (_p1 == null) {
                _p1 = new Point2D(p);
            } else {
                Point2D polar = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
                //calculate the angle between the two points,then calling rotate function with the center and the angle found
                double angle = Math.atan2(polar.y(), polar.x());
                angle = 90 - Math.toDegrees(angle);
                if (angle < 0) angle += 360;
                for (int i = 0; i < _shapes.size(); i++) {
                    if (_shapes.get(i).isSelected() && _shapes.get(i) != null) {
                        _shapes.get(i).getShape().rotate(_p1, angle);
                    }
                }

                _p1 = null;
            }
        }


        if (_mode.equals("Triangle") || _mode.equals("Triangle1")) {
            //3 clicks needed, so we change the mode to "Triangle1", to keep track of how many points we got already
            if (_gs == null) {
                _p1 = new Point2D(p);
                _is_done = false;
            } else if (!_is_done) {
                _p2 = new Point2D(p);
                _is_done = true;
                _mode = "Triangle1";

            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _gs.setTag(_tag);
                _tag++;
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
                _p2 = null;
                _is_done = false;
                _mode = "Triangle";
            }

        }
        if (_mode.equals("Polygon")) {
            _p1 = new Point2D(p);
            points.add(_p1);

        }
        if (_mode.equals("Scale_90%")) {
            _p1 = new Point2D(p);
            double ratio = 0.9;
            for (int i = 0; i < _shapes.size(); i++) {
                GUI_Shapeable s = _shapes.get(i);
                GeoShapeable g = s.getShape();
                if (s.isSelected() && g != null) {
                    g.scale(_p1, ratio);
                }
            }
        }
        if (_mode.equals("Scale_110%")) {
            _p1 = new Point2D(p);
            double ratio = 1.1;
            for (int i = 0; i < _shapes.size(); i++) {
                GUI_Shapeable s = _shapes.get(i);
                GeoShapeable g = s.getShape();
                if (s.isSelected() && g != null) {
                    g.scale(_p1, ratio);
                }
            }
        }


        if (_mode.equals("Point")) {
            select(p);
        }

        drawShapes();
    }

    private void select(Point2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            GeoShapeable g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }

    private void move() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            GeoShapeable g = s.getShape();
            if (s.isSelected() && g != null) {
                g.move(_p1);
            }
        }

    }


    public void mouseRightClicked(Point2D p) {
        System.out.println("right click!");
        //if the mode is different than polygon, we cancel the ongoing drawing
        if (!_mode.equals("Polygon")) {

            _gs = null;
            _p2 = null;
            _p1 = null;
            if (_mode.equals("Triangle1")) _mode = "Triangle";
            drawShapes();
        } else {
            if (_gs != null) {
                //else,completes the polygon creation and adding it to our collection
                Point2D[] pointsarr = new Point2D[points.size()];
                pointsarr = points.toArray(pointsarr);
                _gs = (new GUIShape(new Polygon2D(pointsarr), _fill, _color, _tag));
                _tag++;
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
                points.clear();
                drawShapes();

            }
        }


    }

    public void mouseMoved(MouseEvent e) {
        if (_p1 != null) {
            //drawing the ongoing mouse movement so the user could see his drawing
            double x1 = StdDraw_Ex4.mouseX();
            double y1 = StdDraw_Ex4.mouseY();
            GeoShapeable gs = null;
            //	System.out.println("M: "+x1+","+y1);
            Point2D p = new Point2D(x1, y1);
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle2D(_p1, r);
            }
            if (_mode.equals("Segment")) {
                gs = new Segment2D(_p1, p);
            }
            if (_mode.equals("Rect")) {
                Point2D p3 = new Point2D(_p1.x(), p.y());
                Point2D p4 = new Point2D(p.x(), _p1.y());
                gs = new Rect2D(_p1, p, p3, p4);
            }
            if (_mode.equals("Triangle")) {
                gs = new Segment2D(_p1, p);
            }
            if (_mode.equals("Triangle1")) {
                gs = new Triangle2D(_p1, _p2, p);
            }
            if (_mode.equals("Polygon")) {
                points.add(p);
                Point2D[] pointarr = new Point2D[points.size()];
                pointarr = points.toArray(pointarr);
                gs = new Polygon2D(pointarr);
                points.remove(points.size() - 1);
            }


            _gs = new GUIShape(gs, false, Color.pink, 0);
            drawShapes();
        }
    }

    @Override
    public ShapeCollectionable getShape_Collection() {

        return this._shapes;
    }

    @Override
    public void show() {
        show(Ex4_Const.DIM_SIZE);
    }

    @Override
    public String getInfo() {

        String ans = "";
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            ans += s.toString() + "\n";
        }
        return ans;
    }


    private String loadFile() {
        //creating a FileDialog to choose a file to load
        JFrame frame = new JFrame("Choose a file:");
        FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
        fd.setDirectory("C:\\");
        fd.setFile("");
        fd.setVisible(true);
        String filename = fd.getDirectory() + fd.getFile();
        if (filename == null) {
            System.out.println("You cancelled the choice");
            return null;
        } else
            System.out.println("You chose " + filename);
        return filename;
    }

    private String save_loc() {
        //creating a FileDialog to choose a location and name for the drawing to save
        JFrame frame = new JFrame("Choose a location:");
        FileDialog fd = new FileDialog(frame, "Choose a location", FileDialog.SAVE);
        fd.setDirectory("C:\\");
        fd.setFile("");
        fd.setVisible(true);
        String filename = fd.getDirectory() + fd.getFile();
        if (filename == null) {
            System.out.println("You cancelled the choice");
            return null;
        } else
            System.out.println("You chose " + filename);
        return filename;
    }
}


