package graph;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;


/**
 *
 * @author theboss
 */
public abstract class Graph extends JPanel {

    protected abstract void drawAxes(Graphics g);
    
    protected abstract void drawTitles(Graphics g);
    
    protected abstract Point getMaxXY();
    
    protected abstract Point getMinXY();
    
}
