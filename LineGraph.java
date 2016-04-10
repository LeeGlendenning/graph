/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * LineGraph class should extend Graph which should extend JComponent but for now we will skip that
 */
public class LineGraph extends JComponent{
    
    ArrayList<Point> points;
    String xAxis;
    String yAxis;
    Color colour;
    
    /*
     * LineGraph constructor creates a LineGraph object to be drawn to the screen
     * 
     * @param points        ArrayList of Point objects. Data to display on the line graph
     * @param xAxis         String label for the x axis of the line graph
     * @param yAxis         String label for the y axis of the line graph
     * @param colour        Color object to use when drawing the line graph
     */
    public LineGraph(ArrayList<Point> points, String xAxis, String yAxis, Color colour){
        this.points = points;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.colour = colour;
    }
    
    /*
     * LineGraph empty constructor creates a blank LineGraph object with default values
     */
    public LineGraph(){
        this.points = new ArrayList();
        this.xAxis = "";
        this.yAxis = "";
        this.colour = Color.BLACK;
    }
    
}
