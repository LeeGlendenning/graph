package graph;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        p.add(new Point(10,20));
        p.add(new Point(20,20));
        p.add(new Point(50,90));
        p.add(new Point(70,40));
        p.add(new Point(90,150));
        p.add(new Point(110,140));
        p.add(new Point(130,110));
        p.add(new Point(150,110));
        LineGraph lg = new LineGraph(p, "Test Graph", "X axis title", "Y", Color.BLUE);
        
        JFrame f = new JFrame("A Graph");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(3,3));
        contentPane.add(jp);
        jp.add(new JLabel("a"));
        jp.add(new JLabel("b"));
        jp.add(new JLabel("c"));
        jp.add(new JLabel("d"));
        jp.add(lg);
        jp.add(new JLabel("f"));
        jp.add(new JLabel("g"));
        jp.add(new JLabel("h"));
        jp.add(new JLabel("i"));
        
        f.setPreferredSize(new Dimension(600, 700));
        f.setLocationRelativeTo(null);
        f.pack();
        f.setVisible(true);
    }
    
}
