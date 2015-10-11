import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 
import javax.swing.UIManager.*;

public class JeopardyPanel extends JPanel {

	private Random rand1; 
	private int dailyDoubleRand1; 
	private int dailyDoubleRand2; 
	private int dailyDoubleRand3; 
	private int dailyDoubleRand4; 

	public static JFrame questionFrame;
	public static QuestionPanel questionPanel; 
	public static JFrame dailyDoubleFrame; 
	public int questionCount; 

	public static DailyDoublePanel dailyDoublePanel; 

	public static FinalJeopardyPanel finalJeopardyPanel; 
	public static JFrame finalJeopardyFrame; 

	public static int WRACSCORE; 
	public static int HPACSCORE; 
	public static int HMGCSCORE; 

	private JButton[] category1Questions; 
	private JButton[] category2Questions;
	private JButton[] category3Questions;
	private JButton[] category4Questions;
	private JButton[] category5Questions;

	private boolean bool; 

	private Question[] category1QuestionsObjects; 
	private Question[] category2QuestionsObjects; 
	private Question[] category3QuestionsObjects; 
	private Question[] category4QuestionsObjects; 
	private Question[] category5QuestionsObjects; 

	private JLabel[] categories;
	private static final String[] categorieList = {"COH History","LTS","Water Safety","Lifeguarding","Misc."};

	private static final String[] category1QuestionList = {"<html><center>Who is the current <br />City of Henderson Mayor?</center></html>",
		"<html><center>What is the oldest <br />City of Henderson pool?</center></html>","<html><center>What is the City of <br />Henderson Vision Statement?</center></html>",
		"<html><center>Who was the predecessor <br />to Adam Blackmore?</center></html>",
		"<html><center>When was the City of Henderson founded?</center></html>"}; 
	private static final String[] category1AnswerList = {"Andy A. Hafen","BMI (Basic Magnesium Indistries)","\"To be America's Premier Community\"","Brady Dehn","1953"}; 
	
	private static final String[] category2QuestionList = {"<html><center>What is the maxium number <br />of participants for levels 1-3?</center></html>",
	"<html><center>What is the maximum number <br />of participants PER CLASS for <br />levels 4-6?</center></html>",
	"<html><center>What is the youngest age <br />for a child to enroll in levels 1-3 <br />without an over-ride?</center></html>",
	"<html><center>What is the youngest age <br />for a child to enroll in Learn to <br />Swim without an over-ride?</center></html>",
	"<html><center>What does the acronym WHALE <br />stand for in \"WHALE TALES\"?</center></html>"};
	private static final String[] category2AnswerList = {"15","8","5 years old","6 months","Water Habits Are Learned Early"}; 
	
	private static final String[] category3QuestionList = {"<html><center>What is the lowest acceptable <br />SPF according to <br />WHALE Tales?</center></html>",
	"<html><center>How deep must a <br />pool be for diving to be allowed <br />(during open swim, NOT with a swim coach)?</center></html>",
	"<html><center>City of Henderson policy states <br />that any child _______ must wear be within <br />arms reach of a parent or guardian while at <br />any City pool</center></html>",
	"<html><center>What are the ABCD's <br />of drowning?</center></html>",
	"<html><center>Drowning is the _____ leading cause <br />of unintentional injury death in the United States</center></html>"}; 
	private static final String[] category3AnswerList = {"30 SPF","9 feet (7 feet is only with a swim coach)","8 AND Under","Adult Supervision, Barriers, Classes, Devices","5th Source: CDC"}; 
	
	private static final String[] category4QuestionList = {"<html><center>What does CPR stand for?</center></html>","<html><center>What does the acronym RID <br />in \"RID Factor\" stand for?</center></html>",
	"<html><center>What is the appropriate Liters Per <br />Minute setting for a Non-rebreather mask<br /> while using emergency oxygen?</center></html>",
	"<html><center>What are 3 of the 4 factors needed <br />for a pathogen to spread?</center></html>","<html><center>What are the four types of open wounds?</center></html>"}; 
	private static final String[] category4AnswerList = {"Cardiopulmonary resuscitation","Recognition, Intrusion, Distraction",
	"10-15","Present,Entry Site, Quantity, Suseptability","Abrasion,Puncture,Avulsion,Laceration (No Certain Order)"}; 
	
	private static final String[] category5QuestionList = {"<html><center>How long do American <br />Red Cross Lifeguarding Certifications <br />last?</center></html>",
	"<html><center>How many City of <br />Henderson pools have diving boards?</center></html>","<html><center>How much does a 60 punch <br />pass card cost?</center></html>",
	"<html><center>Who are 5 of the 6 assistant <br />pool managers?</center></html>",
	"<html><center>What is Adam Blackmore's <br />City phone extension?</center></html>"}; 
	private static final String[] category5AnswerList = {"2 years","4","36 dollars","<html><center>Collin McGee, Alex Crocker,<br /> Samuel Gilek, Danielle Havis, <br />Schuyler Roberson, Colleen O'Brien</center></html>",
	"5806"}; 
	
	public JeopardyPanel(){

		/*try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		}catch(Exception e){
			System.out.println("You Suck"); 
		}*/
		try {
    		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
       			if ("Nimbus".equals(info.getName())) {
           		UIManager.setLookAndFeel(info.getClassName());
            	break;
        		}
    		}
		} catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
		}

		rand1 = new Random(); 
		dailyDoubleRand1 = rand1.nextInt(5); 
		dailyDoubleRand2 = rand1.nextInt(5); 
		dailyDoubleRand3 = rand1.nextInt(5); 
		dailyDoubleRand4 = rand1.nextInt(5); 
		while(dailyDoubleRand2 == dailyDoubleRand4){
			dailyDoubleRand4 = rand1.nextInt(5); 
		}
		while(dailyDoubleRand1 == dailyDoubleRand3){
			dailyDoubleRand3 = rand1.nextInt(5); 
		}
		//System.out.println(dailyDoubleRand1 + " " + dailyDoubleRand2 + " " + dailyDoubleRand3 + " " + dailyDoubleRand4); 


		setBackground(Color.BLACK); 

		HPACSCORE = 0; 
		HMGCSCORE = 0; 
		WRACSCORE = 0; 

		categories = new JLabel[5]; 

		category1Questions = new JButton[5]; 
		category2Questions = new JButton[5];
		category3Questions = new JButton[5];
		category4Questions = new JButton[5];
		category5Questions = new JButton[5];
		category1QuestionsObjects = new Question[5]; 
		category2QuestionsObjects = new Question[5];
		category3QuestionsObjects = new Question[5];
		category4QuestionsObjects = new Question[5];
		category5QuestionsObjects = new Question[5];
		this.setLayout(new GridLayout(6,5));
		updateBoard(false); 

	}

	public void updateBoard(boolean bool){
		if(questionCount<25){
			this.removeAll(); 
			for(int i = 0; i<=4; i++){
				categories[i] = new JLabel(categorieList[i]); 
				categories[i].setHorizontalAlignment(SwingConstants.CENTER);
				categories[i].setFont(new Font("Serif", Font.BOLD, 25)); 
				categories[i].setOpaque(true); 
				categories[i].setBackground(new Color(6,12,233));
				categories[i].setForeground(Color.YELLOW);  
				categories[i].setBorder(BorderFactory.createLineBorder(Color.black,8)); 
				this.add(categories[i]); 
			}
			for(int i = 0;i<=4;i++){
				category1Questions[i] = new JButton(Integer.toString(i*100 + 100));  
				this.add(category1Questions[i]); 
				if(!bool){
					category1QuestionsObjects[i] = new Question(i*100 + 100,category1QuestionList[i],category1AnswerList[i]);
				} 
				if(category1QuestionsObjects[i].isAnswered()){
					category1Questions[i].setBackground(new Color(6,12,233));
					category1Questions[i].setFont(new Font("Serif", Font.BOLD, 45));
					category1Questions[i].setForeground(Color.WHITE);
					category1Questions[i].setBorder(BorderFactory.createLineBorder(Color.black,8));  
				}else{
					category1Questions[i].setBackground(new Color(6,12,233));
					category1Questions[i].setFont(new Font("Serif", Font.BOLD, 45));
					category1Questions[i].setForeground(Color.YELLOW);
					category1Questions[i].addActionListener(new Category1ButtonListener(category1QuestionList[i]));
					category1Questions[i].setBorder(BorderFactory.createLineBorder(Color.black,8));
				}


				category2Questions[i] = new JButton(Integer.toString(i*100 + 100));
				this.add(category2Questions[i]);
				if(!bool){
					category2QuestionsObjects[i] = new Question(i*100 + 100,category2QuestionList[i],category2AnswerList[i]);
				} 
				if(category2QuestionsObjects[i].isAnswered()){
					category2Questions[i].setBackground(new Color(6,12,233));
					category2Questions[i].setFont(new Font("Serif", Font.BOLD, 45));
					category2Questions[i].setForeground(Color.WHITE);  
					category2Questions[i].setBorder(BorderFactory.createLineBorder(Color.black,8));
				}else{
					category2Questions[i].setBackground(new Color(6,12,233));
					category2Questions[i].setFont(new Font("Serif", Font.BOLD, 45));
					category2Questions[i].setForeground(Color.YELLOW);
					category2Questions[i].addActionListener(new Category2ButtonListener(category2QuestionList[i]));
					category2Questions[i].setBorder(BorderFactory.createLineBorder(Color.black,8));
				}

				category3Questions[i] = new JButton(Integer.toString(i*100 + 100));
				this.add(category3Questions[i]);
				if(!bool){
					category3QuestionsObjects[i] = new Question(i*100 + 100,category3QuestionList[i],category3AnswerList[i]);
				}
				if(category3QuestionsObjects[i].isAnswered()){
					category3Questions[i].setBackground(new Color(6,12,233));
					category3Questions[i].setFont(new Font("Serif", Font.BOLD, 45));
					category3Questions[i].setForeground(Color.WHITE);  
					category3Questions[i].setBorder(BorderFactory.createLineBorder(Color.black,8));
				}else{
					category3Questions[i].setBackground(new Color(6,12,233));
					category3Questions[i].setFont(new Font("Serif", Font.BOLD, 45));
					category3Questions[i].setForeground(Color.YELLOW);
					category3Questions[i].addActionListener(new Category3ButtonListener(category3QuestionList[i]));
					category3Questions[i].setBorder(BorderFactory.createLineBorder(Color.black,8));
				}

				category4Questions[i] = new JButton(Integer.toString(i*100 + 100));
				this.add(category4Questions[i]);
				if(!bool){
					category4QuestionsObjects[i] = new Question(i*100 + 100,category4QuestionList[i],category4AnswerList[i]);
				}
				if(category4QuestionsObjects[i].isAnswered()){
					category4Questions[i].setBackground(new Color(6,12,233));
					category4Questions[i].setFont(new Font("Serif", Font.BOLD, 45));
					category4Questions[i].setForeground(Color.WHITE);  
					category4Questions[i].setBorder(BorderFactory.createLineBorder(Color.black,8));
				}else{
					category4Questions[i].setBackground(new Color(6,12,233));
					category4Questions[i].setFont(new Font("Serif", Font.BOLD, 45));
					category4Questions[i].setForeground(Color.YELLOW);
					category4Questions[i].addActionListener(new Category4ButtonListener(category4QuestionList[i]));
					category4Questions[i].setBorder(BorderFactory.createLineBorder(Color.black,8));
				}

				category5Questions[i] = new JButton(Integer.toString(i*100 + 100));
				this.add(category5Questions[i]);
				if(!bool){
					category5QuestionsObjects[i] = new Question(i*100 + 100,category5QuestionList[i],category5AnswerList[i]);
				}
				if(category5QuestionsObjects[i].isAnswered()){
					category5Questions[i].setBackground(new Color(6,12,233));
					category5Questions[i].setFont(new Font("Serif", Font.BOLD, 45));
					category5Questions[i].setForeground(Color.WHITE);   
					category5Questions[i].setBorder(BorderFactory.createLineBorder(Color.black,8));
				}else{
					category5Questions[i].setBackground(new Color(6,12,233));
					category5Questions[i].setFont(new Font("Serif", Font.BOLD, 45));
					category5Questions[i].setForeground(Color.YELLOW);
					category5Questions[i].addActionListener(new Category5ButtonListener(category5QuestionList[i]));
					category5Questions[i].setBorder(BorderFactory.createLineBorder(Color.black,8));
				}
				this.revalidate(); 
			}
		}else{
			JeopardyDriver.myFrame.dispose(); 
			finalJeopardyFrame = new JFrame("Final Jeopardy");
			finalJeopardyPanel = new FinalJeopardyPanel();  
			finalJeopardyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			finalJeopardyFrame.add(finalJeopardyPanel);  
			finalJeopardyFrame.setSize(500,500); 
			finalJeopardyFrame.setVisible(true); 
			finalJeopardyFrame.pack(); 
			finalJeopardyFrame.setLocationRelativeTo(getRootPane());
		}
	}

	private class Category1ButtonListener implements ActionListener{

		private String question;  

		public Category1ButtonListener(String question){
			this.question = question; 
		}

		public void actionPerformed(ActionEvent e){ 
			for(int i = 0; i<=4;i++){ 
				if(category1QuestionList[i].equals(question)){
					if(!category1QuestionsObjects[i].isAnswered()){
						questionCount++; 
					}
					category1QuestionsObjects[i].questionAnswered();
					JFrame.setDefaultLookAndFeelDecorated(true);
					questionFrame = new JFrame("Question");
					questionFrame.setResizable(true); 
					removeMinMaxClose(questionFrame); 
					questionPanel = new QuestionPanel(category1QuestionsObjects[i]);
					JeopardyDriver.judgePanel.updatePanel(category1QuestionsObjects[i]);  
					questionFrame.add(questionPanel);
					questionFrame.setVisible(true);
					questionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
					questionFrame.pack();  
					questionFrame.setLocationRelativeTo(getRootPane());
					JFrame.setDefaultLookAndFeelDecorated(false);
					if(dailyDoubleRand1 == 0 && dailyDoubleRand2 == (i)|| (dailyDoubleRand3 == 0 && dailyDoubleRand4 == (i))){
						dailyDoubleFrame = new JFrame("Daily Double"); 
						dailyDoublePanel = new DailyDoublePanel(category1QuestionsObjects[i]); 
						dailyDoubleFrame.add(dailyDoublePanel); 
						dailyDoubleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
						dailyDoubleFrame.pack(); 
						dailyDoubleFrame.setVisible(true);
						dailyDoubleFrame.setLocationRelativeTo(getRootPane());
						dailyDoublePanel.goForGold(); 
					}
					break; 
				}
			}
		} 
	}

	private class Category2ButtonListener implements ActionListener{

		private String question; 

		public Category2ButtonListener(String question){
			this.question = question; 
		}

		public void actionPerformed(ActionEvent e){
			for(int i = 0; i<=4;i++){
				if(category2QuestionList[i].equals(question)){
					if(!category2QuestionsObjects[i].isAnswered()){
						questionCount++; 
					}
					category2QuestionsObjects[i].questionAnswered();
					JFrame.setDefaultLookAndFeelDecorated(true); 
					questionFrame = new JFrame("Question");
					questionFrame.setResizable(false); 
					removeMinMaxClose(questionFrame);
					questionPanel = new QuestionPanel(category2QuestionsObjects[i]); 
					JeopardyDriver.judgePanel.updatePanel(category2QuestionsObjects[i]);
					questionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
					questionFrame.add(questionPanel); 
					questionFrame.pack(); 
					questionFrame.setVisible(true);
					questionFrame.setLocationRelativeTo(getRootPane());
					JFrame.setDefaultLookAndFeelDecorated(false); 
					if(dailyDoubleRand1 == 1 && dailyDoubleRand2 == (i) || (dailyDoubleRand3 == 1 && dailyDoubleRand4 == (i))){
						dailyDoubleFrame = new JFrame("Daily Double"); 
						dailyDoublePanel = new DailyDoublePanel(category2QuestionsObjects[i]); 
						dailyDoubleFrame.add(dailyDoublePanel); 
						dailyDoubleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
						dailyDoubleFrame.pack(); 
						dailyDoubleFrame.setVisible(true);
						dailyDoubleFrame.setLocationRelativeTo(getRootPane());
						dailyDoublePanel.goForGold(); 
					} 
					break; 
				}
			}
		} 
	}

	private class Category3ButtonListener implements ActionListener{

		private String question; 

		public Category3ButtonListener(String question){
			this.question = question; 
		}

		public void actionPerformed(ActionEvent e){
			for(int i = 0; i<=4;i++){
				if(category3QuestionList[i].equals(question)){
					if(!category3QuestionsObjects[i].isAnswered()){
						questionCount++; 
					}
					category3QuestionsObjects[i].questionAnswered();
					JFrame.setDefaultLookAndFeelDecorated(true); 
					questionFrame = new JFrame("Question"); 
					questionFrame.setResizable(false); 
					removeMinMaxClose(questionFrame);
					questionPanel = new QuestionPanel(category3QuestionsObjects[i]);
					JeopardyDriver.judgePanel.updatePanel(category3QuestionsObjects[i]);
					questionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
					questionFrame.add(questionPanel); 
					questionFrame.pack(); 
					questionFrame.setVisible(true);
					questionFrame.setLocationRelativeTo(getRootPane()); 
					JFrame.setDefaultLookAndFeelDecorated(false); 
					if(dailyDoubleRand1 == 2 && dailyDoubleRand2 == (i) || (dailyDoubleRand3 == 2 && dailyDoubleRand4 == (i))){
						dailyDoubleFrame = new JFrame("Daily Double"); 
						dailyDoublePanel = new DailyDoublePanel(category3QuestionsObjects[i]); 
						dailyDoubleFrame.add(dailyDoublePanel); 
						dailyDoubleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
						dailyDoubleFrame.pack(); 
						dailyDoubleFrame.setVisible(true);
						dailyDoubleFrame.setLocationRelativeTo(getRootPane());
						dailyDoublePanel.goForGold(); 
					}
					break;
				}
			}
		} 
	}

	private class Category4ButtonListener implements ActionListener{

		private String question; 

		public Category4ButtonListener(String question){
			this.question = question; 
		}

		public void actionPerformed(ActionEvent e){
			for(int i = 0; i<=4;i++){
				if(category4QuestionList[i].equals(question)){
					if(!category4QuestionsObjects[i].isAnswered()){
						questionCount++; 
					}
					category4QuestionsObjects[i].questionAnswered();
					JFrame.setDefaultLookAndFeelDecorated(true);
					questionFrame = new JFrame("Question"); 
					questionFrame.setResizable(false); 
					removeMinMaxClose(questionFrame);
					questionPanel = new QuestionPanel(category4QuestionsObjects[i]);
					JeopardyDriver.judgePanel.updatePanel(category4QuestionsObjects[i]);  
					questionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
					questionFrame.add(questionPanel); 
					questionFrame.pack(); 
					questionFrame.setVisible(true); 
					questionFrame.setLocationRelativeTo(getRootPane());
					JFrame.setDefaultLookAndFeelDecorated(false); 
					if(dailyDoubleRand1 == 3 && dailyDoubleRand2 == (i)|| (dailyDoubleRand3 == 3 && dailyDoubleRand4 == (i))){
						dailyDoubleFrame = new JFrame("Daily Double"); 
						dailyDoublePanel = new DailyDoublePanel(category4QuestionsObjects[i]); 
						dailyDoubleFrame.add(dailyDoublePanel); 
						dailyDoubleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
						dailyDoubleFrame.pack(); 
						dailyDoubleFrame.setVisible(true);
						dailyDoubleFrame.setLocationRelativeTo(getRootPane());
						dailyDoublePanel.goForGold(); 
					}
					break; 
				}
			}
		}
	}

	private class Category5ButtonListener implements ActionListener{

		private String question; 

		public Category5ButtonListener(String question){
			this.question = question; 
		}

		public void actionPerformed(ActionEvent e){
			for(int i = 0; i<=4;i++){
				if(category5QuestionList[i].equals(question)){
					if(!category5QuestionsObjects[i].isAnswered()){
						questionCount++; 
					}
					category5QuestionsObjects[i].questionAnswered();
					JFrame.setDefaultLookAndFeelDecorated(true); 
					questionFrame = new JFrame("Question");
					questionFrame.setResizable(false); 
					removeMinMaxClose(questionFrame); 
					questionPanel = new QuestionPanel(category5QuestionsObjects[i]);
					JeopardyDriver.judgePanel.updatePanel(category5QuestionsObjects[i]); 
					questionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
					questionFrame.add(questionPanel); 
					questionFrame.pack(); 
					questionFrame.setVisible(true); 
					questionFrame.setLocationRelativeTo(getRootPane());
					JFrame.setDefaultLookAndFeelDecorated(false); 
					if(dailyDoubleRand1 == 4 && dailyDoubleRand2 == (i)|| (dailyDoubleRand3 == 4 && dailyDoubleRand4 == (i))){
						dailyDoubleFrame = new JFrame("Daily Double"); 
						dailyDoublePanel = new DailyDoublePanel(category5QuestionsObjects[i]); 
						dailyDoubleFrame.add(dailyDoublePanel); 
						dailyDoubleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
						dailyDoubleFrame.pack(); 
						dailyDoubleFrame.setVisible(true);
						dailyDoubleFrame.setLocationRelativeTo(getRootPane());
						dailyDoublePanel.goForGold(); 
					}
					break; 
				}
			}
		}
	}

	public static void disposePanel(){
		questionFrame.dispose(); 
	}

	public void removeMinMaxClose(Component comp){  
		if(comp instanceof JButton){  
    		String accName = ((JButton) comp).getAccessibleContext().getAccessibleName();   
    		if(accName.equals("Maximize")|| accName.equals("Iconify")||accName.equals("Close")) 
    			comp.getParent().remove(comp);  
  		}  
  		if (comp instanceof Container){  
    		Component[] comps = ((Container)comp).getComponents();  
    		for(int x = 0, y = comps.length; x < y; x++){  
      			removeMinMaxClose(comps[x]);  
    		}  
  		}  
	}
}