package code_etarlton_assigment2;
import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class GUI extends JFrame{
	private JFrame frame;
	private JButton scrambleButton;
	private JButton selectionSortButton;
	private JButton mergeSortButton;
	private JButton resetButton;
	private JPanel buttonPanel;
	private char selectionSortCounter;
	private char mergeSortCounter;
	public DrawingPanel linesPanel;
	private Sort sort;
	private int[] copyArray;
	private Color[] copyColorArray;
	
	
	/**
	 * Default constructor for GUI class
	 * Sets the frame aspects
	 */
	public GUI() 
	{
		copyArray = new int[256];
		copyColorArray = new Color[256];
		sort = new Sort();
		selectionSortCounter = 'n';
		mergeSortCounter = 'n';
		linesPanel = new DrawingPanel();
		
		Random rand = new Random();
		
		for(int i = 0; i < 256; i++)
		{
			linesPanel.array[i] = 400 - i;
			float r = rand.nextFloat();
			float c = rand.nextFloat();
			float b = rand.nextFloat();
			Color randColor = new Color(r, c, b);
			linesPanel.colorArray[i] = randColor;
		}
		
		frame = new JFrame("Sorting Assingment");
		frame.setLayout(new BorderLayout());
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		//scrambles the positions of the lines so their heights are not in order
		//executes if the "scramble lines" button is clicked
		//disables the scramble button so it can not be clicked until both the merge sort
		//and selection sorts have taken place
		scrambleButton = new JButton("Scrable Lines");
		scrambleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				scrambleButton.setEnabled(false);
				mergeSortCounter = 'n';
				selectionSortCounter = 'n';
				
				int temp;
				Color colorTemp;
				Random rand = ThreadLocalRandom.current();
				
				for(int i = 255; i >= 0; i--)
				{
					int randIndex = rand.nextInt(i + 1);
					temp = linesPanel.array[randIndex];
					linesPanel.array[randIndex] = linesPanel.array[i];
					linesPanel.array[i] = temp;
					
					colorTemp = linesPanel.colorArray[randIndex];
					linesPanel.colorArray[randIndex] = linesPanel.colorArray[i];
					linesPanel.colorArray[i] = colorTemp;
					
					copyArray[i] = linesPanel.array[i];
					copyColorArray[i] = linesPanel.colorArray[i];
					
				}
				linesPanel.repaint();
			}
		});
		
		//calls method for selection sort on the array of lines
		//executes if the "Selection sort button is clicked"
		//if the merge sort button has also been clicked, the scramble button is enabled
		selectionSortButton = new JButton("Selection Sort");
		selectionSortButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				selectionSortCounter = 'y';
				if(mergeSortCounter == 'n')
				{
					scrambleButton.setEnabled(false);
				}
				if(mergeSortCounter == 'y')
				{
					scrambleButton.setEnabled(true);
				}
				
				sort.selectionSort(linesPanel, 256);
		      
			}
		});
		
		//calls method for merge sort for the lines
		//executes if the "Merge sort" button is clicked
		//if the selection sort button has also been clicked, the scramble button is enabled
		mergeSortButton = new JButton("Merge Sort");
		mergeSortButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				mergeSortCounter = 'y';
				if(selectionSortCounter == 'n')
				{
					scrambleButton.setEnabled(false);
				}
				if(selectionSortCounter == 'y')
				{
					scrambleButton.setEnabled(true);
				}
				
				//two array variables that act as temporary containers for the merge sort method:
				Color[] ctemp =  new Color[256];
				int[] temp = new int[256];
				
			sort.mergeSort(0, 255, linesPanel, ctemp, temp);
			}
		});
		
		//Resets the lines to the way they were before they were sorted
		//executes if the "reset lines" button is clicked
		resetButton = new JButton("Reset Lines");
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				for(int i = 0; i < 256; i++)
				{
					linesPanel.array[i] = copyArray[i];
					linesPanel.colorArray[i] = copyColorArray[i];
				}
				linesPanel.repaint();
			}
		});
	}//end of GUI constructor
	
	/**
	 * Method that places the frame on the screen
	 * and panels on the frame
	 */
	public void drawFrame(){
		frame.setSize(900, 600);
		frame.setVisible(true);
		frame.setLocation(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buttonPanel.add(scrambleButton);
		buttonPanel.add(selectionSortButton);
		buttonPanel.add(mergeSortButton);
		buttonPanel.add(resetButton);
		
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(linesPanel);
	}
	
	/**
	 * Main method for GUI class
	 * @param args
	 */
	 public static void main(String args[])
	 { 
		 GUI gui = new GUI();
		 gui.drawFrame();
	 }

}//end GUI class



