import java.awt.Color;
import javax.swing.*;
import java.util.Random;
/*
 *  A GUI component
 *
 *  A simple extension of JPanel which records its
 *  coordinates in xcoord and ycoord, NOT in 'x' and 'y'.
 *
 *  The game grid and allows the background colour to be set.
 *
 *  This is based on Simple GUI Demonstration 2 by Dr.Mark Hatcher
 *  @author dwijayasunda
 */
public class GridSquare extends JPanel
{
    private int xcoord, ycoord;  // location in the grid
	
	// constructor takes the x and y coordinates of the square
	public GridSquare(int xcoord, int ycoord)
	{
		super();
		this.setSize(50,50);
		this.xcoord = xcoord;
		this.ycoord = ycoord;
	}
	
	//A getter method that returns the color of the square
	public Color getColor()
	{
		return this.getBackground();
	}
	
	//A method to set the color of the square to white
    public void setColor()
    {
        this.setBackground( Color.white);
    }
    
    // A  method which turns the color of the square to red,if its player 1
    // or else to blue, if its player 2,
    // if the square is white
    public void switchColor(int playerNumber)
    {   
    	int player = playerNumber;
    	
    	if (player == 1)
    	{
    		if (getBackground() == Color.white)
    		{
    			this.setBackground(Color.red);
    		}
    	}
    	
    	if (player == 2)
    	{
    		if (getBackground() == Color.white)
    		{
    			this.setBackground(Color.blue);
    		}
    	}
    	
    }
    
    // simple setters and getters
    public void setXcoord(int value)    { xcoord = value; }   //sets the x-coordinate
    public void setYcoord(int value)    { ycoord = value; }   //sets the y-coordinate
    public int getXcoord()              { return xcoord; }    //returns the x-coordinate
    public int getYcoord()              { return ycoord; }    //returns the y-coordinate
}
