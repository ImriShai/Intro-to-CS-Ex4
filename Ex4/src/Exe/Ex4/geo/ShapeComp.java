package Exe.Ex4.geo;

import java.util.Comparator;

import Exe.Ex4.*;

/**
 * This class represents a Comparator over GUI_Shapes -
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class ShapeComp implements Comparator<GUI_Shapeable> {
    //////////add your code below ///////////


    private int _flag;

    public ShapeComp(int flag) {
        _flag = flag;

    }

    @Override
    public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
        int ans = 0;
        if (_flag == Ex4_Const.Sort_By_toString) {
            ans = o1.toString().compareTo(o2.toString());
        }
        //////////add your code below ///////////
        if (_flag == Ex4_Const.Sort_By_Anti_toString) {
            ans = o2.toString().compareTo(o1.toString());
        }
        if (_flag == Ex4_Const.Sort_By_Area) {
            double d1 = o1.getShape().area();
            double d2 = o2.getShape().area();
            if (d1 < d2) {
                ans = -1;
            }
            if (d2 < d1) {
                ans = 1;
            }
        }

        if (_flag == Ex4_Const.Sort_By_Anti_Area) {
            double d1 = o2.getShape().area();
            double d2 = o1.getShape().area();
            if (d1 < d2) {
                ans = -1;
            }
            if (d2 < d1) {
                ans = 1;
            }
        }
        if (_flag == Ex4_Const.Sort_By_Perimeter) {
            double d1 = o1.getShape().perimeter();
            double d2 = o2.getShape().perimeter();
            if (d1 < d2) {
                ans = -1;
            }
            if (d2 < d1) {
                ans = 1;
            }
        }
        if (_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
            double d1 = o2.getShape().perimeter();
            double d2 = o1.getShape().perimeter();
            if (d1 < d2) {
                ans = -1;
            }
            if (d2 < d1) {
                ans = 1;
            }
        }
        if (_flag == Ex4_Const.Sort_By_Tag) {
            double d1 = o1.getTag();
            double d2 = o2.getTag();
            if (d1 < d2) {
                ans = -1;
            }
            if (d2 < d1) {
                ans = 1;
            }
        }
        if (_flag == Ex4_Const.Sort_By_Anti_Tag) {
            double d1 = o2.getTag();
            double d2 = o1.getTag();
            if (d1 < d2) {
                ans = -1;
            }
            if (d2 < d1) {
                ans = 1;
            }
        }

        //////////////////////////////////////////
        return ans;
    }
}


