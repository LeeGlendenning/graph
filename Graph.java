package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;


/**
 *
 * @author theboss
 */
public abstract class Graph extends JComponent {
    
    protected String title, xAxisLabel, yAxisLabel;
    protected Color colour;

    protected abstract void drawAxes(Graphics g);
    
    protected abstract void drawTitles(Graphics g);
    
    protected abstract Point getMaxXY();
    
    protected abstract Point getMinXY();
    
    protected abstract void updateGraph(Graphics g);
    
    protected abstract void drawGraph(Graphics g);
    
    protected String getTitle(){
        return this.title;
    }
    
    protected void setTitle(String title){
        this.title = title;
    }
    
    protected void setAxesLabels(String xLabel, String yLabel){
        this.xAxisLabel = xLabel;
        this.yAxisLabel = yLabel;
    }
    
    protected String getXAxisLabel(){
        return this.xAxisLabel;
    }
    
    protected void setXAxisLabel(String xLabel){
        this.xAxisLabel = xLabel;
    }
    
    protected String getYAxisLabel(){
        return this.yAxisLabel;
    }
    
    protected void setYAxisLabel(String yLabel){
        this.yAxisLabel = yLabel;
    }
    
    protected Color setGraphColour(){
        return this.colour;
    }
    
    protected void setGraphColour(Color c){
        this.colour = c;
    }
    
}
