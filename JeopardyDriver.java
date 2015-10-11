import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.*;
import java.awt.Toolkit;   

public class JeopardyDriver{

	public static JudgePanel judgePanel;
	public static JFrame judgeFrame; 
	public static JFrame myFrame;  
	public static JeopardyPanel myPanel; 

	public static void main(String[] args){
		Toolkit tk = Toolkit.getDefaultToolkit();
		myFrame = new JFrame("Guard Games 2013 Jeopardy"); 
		myPanel = new JeopardyPanel(); 
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		myFrame.add(myPanel); 
		myFrame.pack(); 
		myFrame.setVisible(true); 
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight())-50;  
		myFrame.setSize(xSize,ySize);

		judgeFrame = new JFrame("JUDGE FRAME"); 
		judgePanel = new JudgePanel(); 
		judgeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		judgeFrame.add(judgePanel); 
		judgeFrame.setVisible(true); 
		judgeFrame.setSize(500,500);

		/*FinalJeopardyPanel testPanel = new FinalJeopardyPanel(); 
		JFrame testFrame = new JFrame("Test Frame"); 
		testFrame.add(testPanel); 
		testFrame.setVisible(true); 
		testFrame.pack(); 
		testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); */  
	}
}
