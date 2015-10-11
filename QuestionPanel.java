import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 

public class QuestionPanel extends JPanel{

	private JLabel question; 
	private JButton wracButton; 
	private JButton hmgcButton; 
	private JButton hpacButton; 
	private JButton noneButton; 
	private JButton wracIncorrectButton; 
	private JButton hmgcIncorrectButton; 
	private JButton hpacIncorrectButton;
	private javax.swing.Timer timer;  
	private int i; 
	private JLabel timerLabel; 
	private final String fullQuestion; 

	private JPanel buttonPanel;

	  

	public QuestionPanel(Question questionObj){

		setBackground(new Color(6,12,233)); 

		final Question questionObject = questionObj;
		fullQuestion = questionObject.getQuestion(); 

		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(6,12,233));  

		question = new JLabel(questionObject.getQuestion());
		question.setHorizontalAlignment(SwingConstants.CENTER); 
		question.setFont(new Font("Serif", Font.BOLD, 65));
		wracButton = new JButton("<html><font color=\"green\"><center>WRAC CORRECT</center></font></html>"); 
		wracButton.setFont(new Font("Serif", Font.BOLD, 25));
		wracButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JeopardyPanel.WRACSCORE += questionObject.getPointValue();
				JeopardyDriver.judgePanel.updatePanel(questionObject); 
				JeopardyPanel.disposePanel(); 
				JeopardyDriver.myPanel.updateBoard(true);  
			}
		}); 
		wracIncorrectButton = new JButton("<html><font color=\"red\"><center><nobr>WRAC INCORRECT</nobr></center></font></html>"); 
		wracIncorrectButton.setFont(new Font("Serif", Font.BOLD, 25));
		wracIncorrectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JeopardyPanel.WRACSCORE -= questionObject.getPointValue(); 
				JeopardyDriver.judgePanel.updatePanel(questionObject);   
				restartTimer(); 
			}
		}); 
		hmgcButton = new JButton("<html><font color=\"green\"><nobr>HMGC CORRECT</nobr></font></html>");
		hmgcButton.setFont(new Font("Serif", Font.BOLD, 25));
		hmgcButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JeopardyPanel.HMGCSCORE += questionObject.getPointValue();
				JeopardyDriver.judgePanel.updatePanel(questionObject);  
				JeopardyPanel.disposePanel(); 
				JeopardyDriver.myPanel.updateBoard(true);
			}
		});  
		hmgcIncorrectButton = new JButton("<html><font color=\"red\"><nobr>HMGC INCORRECT</nobr></font></html>"); 
		hmgcIncorrectButton.setFont(new Font("Serif", Font.BOLD, 25));
		hmgcIncorrectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JeopardyPanel.HMGCSCORE -= questionObject.getPointValue(); 
				JeopardyDriver.judgePanel.updatePanel(questionObject);  
				restartTimer(); 
			}
		}); 
		hpacButton = new JButton("<html><font color=\"green\"><nobr>HPAC CORRECT</nobr></font></html>");
		hpacButton.setFont(new Font("Serif", Font.BOLD, 25));
		hpacButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JeopardyPanel.HPACSCORE += questionObject.getPointValue();
				JeopardyDriver.judgePanel.updatePanel(questionObject); 
				JeopardyPanel.disposePanel();
				JeopardyDriver.myPanel.updateBoard(true);
			}
		}); 
		hpacIncorrectButton = new JButton("<html><font color=\"red\"><center><nobr>HPAC INCORRECT</font></nobr></center></html>");  
		hpacIncorrectButton.setFont(new Font("Serif", Font.BOLD, 25));
		hpacIncorrectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JeopardyPanel.HPACSCORE -= questionObject.getPointValue(); 
				JeopardyDriver.judgePanel.updatePanel(questionObject); 
				restartTimer();  
			}
		}); 
		noneButton = new JButton("NO ANSWER"); 
		noneButton.setFont(new Font("Serif", Font.BOLD, 25));
		noneButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JeopardyPanel.disposePanel();
				JeopardyDriver.myPanel.updateBoard(true); 
			}
		});  
		timerLabel = new JLabel("10"); 

		buttonPanel.setLayout(new GridLayout(2,3)); 
		buttonPanel.add(wracButton); 
		buttonPanel.add(hmgcButton); 
		buttonPanel.add(hpacButton);
		buttonPanel.add(wracIncorrectButton); 
		buttonPanel.add(hmgcIncorrectButton); 
		buttonPanel.add(hpacIncorrectButton);

		this.setLayout(new BorderLayout()); 
		this.add(question,BorderLayout.NORTH);
		this.add(buttonPanel,BorderLayout.CENTER);
		this.add(noneButton,BorderLayout.SOUTH); 

		i = 15; 
		int delay = 1000; 
        	ActionListener taskPerformer = new ActionListener() {
      			public void actionPerformed(ActionEvent evt) {
      				if(i<=10){
      				String questionText = fullQuestion.substring(0,fullQuestion.length()-16) + "<br />" + Integer.toString(i) + fullQuestion.substring(fullQuestion.length()-16); 
      				question.setText(questionText);
      				JeopardyPanel.questionFrame.revalidate(); 
      				JeopardyPanel.questionFrame.pack(); 
      				if(i == 0){
      					timer.stop(); 
      				}
      				}
					i--; 
      		}
      	}; 
        timer = new javax.swing.Timer(delay, taskPerformer); 
  		timer.start();  

	}

	private QuestionPanel getPanel(){
		return this; 
	}

	public void restartTimer(){
		timer.stop(); 
		i = 10; 
		int delay = 1000; 
        	ActionListener taskPerformer = new ActionListener() {
      			public void actionPerformed(ActionEvent evt) {
      				String questionText = fullQuestion.substring(0,fullQuestion.length()-16) + "<br />" + Integer.toString(i) + fullQuestion.substring(fullQuestion.length()-16); 
      				question.setText(questionText);
      				JeopardyPanel.questionFrame.pack(); 
      				if(i == 0){
      					timer.stop(); 
      				}
					i--; 
      		}
      	}; 
        timer = new javax.swing.Timer(delay, taskPerformer); 
  		timer.start(); 
	}
}
