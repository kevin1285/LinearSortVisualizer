import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SortLab { //Since this is the Driver, I put all the sort functions in ColorPanel and called them here. I think this way is a lot cleaner because I can just use the ColorPanel variables in their own class and reserve driver for calling functions only
	static ColorPanel pencils;
	public static void main(String[] args) throws InterruptedException{
		JFrame frame = new JFrame();
		frame.setSize(1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		String input;
		do {
			input = JOptionPane.showInputDialog("Enter the number of pencils you wanna sort");
		}while(!isNumber(input));
		int numPencils = Integer.parseInt(input);
		pencils = new ColorPanel(numPencils);
		frame.getContentPane().add(pencils);
		do {
			String initialState;
			do {
				initialState = (String)JOptionPane.showInputDialog(null, "INITIAL STATE", "choose an initial state", JOptionPane.QUESTION_MESSAGE, null, new String[]{"random", "sorted reverse"}, null);
			}while(initialState == null);
			setInitialState(initialState);
			frame.setVisible(true);
			
			String method;
			do {
				method = (String)(JOptionPane.showInputDialog(null, "SORT METHOD", "choose a method", JOptionPane.QUESTION_MESSAGE, null, new String[] {"selection","bubble","insertion"}, null));
			}while(method == null);
			
			
			input = null;
			do {
				input = (String)JOptionPane.showInputDialog(null, "SORT SPEED", "pick a speed", JOptionPane.QUESTION_MESSAGE, null, new String[]{"slow","medium","fast","very fast"}, null);
			}while(input == null);
			double timeMultiplyier = convertSpeed(input);
			
			
			pencils.setTimeMultiplyier(timeMultiplyier);
			
			Thread.sleep(1000);
			sort(method);
			displayCounts(method);
		}while(JOptionPane.showConfirmDialog(null, "Click \"yes\" to repeat and \"no\" to end", "Repeat with same number of pencils?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
		
		
	}
	private static boolean isNumber(String s) { 
		  try {Integer.parseInt(s);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	} 
	private static double convertSpeed(String s) {
		switch(s) {
			case "slow":
				return 1.5;
			case "medium":
				return 1;
			case "fast":
				return .3;
			default:
				return .08;
		}
	}
	private static void displayCounts(String sortType) {
		JOptionPane.showMessageDialog(null, sortType + " sort completed with " + pencils.getAccessors() + " accessors and " + pencils.getModifiers() + " modifiers.");
	}
	private static void sort(String method) {
		if(method.equals("selection"))   
			pencils.selectionSort();
		else if(method.equals("bubble")) 
			pencils.bubbleSort();
		else                             
			pencils.insertionSort();
	}
	private static void setInitialState(String type) {
		if(type.equals("sorted reverse"))
			pencils.sortReverse();
		else
			pencils.randomize();
	}
}

