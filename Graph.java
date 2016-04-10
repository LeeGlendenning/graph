package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author theboss
 */
public class Graph {

    public static void main(String[] args) {
        test();
    }
    
    private static void test(){
        ArrayList<Point> p = new ArrayList();
        LineGraph lg = new LineGraph(p, "X", "Y", Color.BLUE);
        
        JFrame f = new JFrame("A Graph");
        
        f.add(lg);
        
        f.setPreferredSize(new Dimension(600, 700));
        f.setLocationRelativeTo(null);
        f.pack();
        f.setVisible(true);
    }
    
}
