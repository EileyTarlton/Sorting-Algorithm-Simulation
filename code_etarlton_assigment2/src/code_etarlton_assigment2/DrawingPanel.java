package code_etarlton_assigment2;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
	public int[] array;
	public Color[] colorArray;
	
	/**
	 * Default constructor for DrawingPanel class
	 * initializes array and colorArray
	 */
	public DrawingPanel()
	{
		array = new int[256];
		colorArray = new Color[256];
	}
	
	/**
	 * Task: draws the lines on the frame
	 */
	protected void paintComponent(Graphics g)
	{	super.paintComponent(g);
		
		for(int i = 0; i < 256; i++)
		{
			g.setColor(colorArray[i]);
			g.drawLine((50 + (i*3)), 400 , (50 + (i*3)), array[i]);
		}
	}
	
}
