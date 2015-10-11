import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 

public class JudgePanel extends JPanel{
	
	private static JLabel currentAnswer; 
	private static JLabel hpacScore; 
	private static JLabel wracScore; 
	private static JLabel hmgcScore; 
	private static JPanel scorePanel; 
	private static JLabel judgeWarning; 

	public JudgePanel(){

		setBackground(Color.WHITE); 

		scorePanel = new JPanel(); 
		scorePanel.setBackground(Color.WHITE);

		this.setLayout(new BorderLayout()); 
		currentAnswer = new JLabel("No Question Selected Yet"); 
		currentAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		currentAnswer.setFont(new Font("Serif", Font.BOLD, 35));
		hpacScore = new JLabel("HPAC Current Score: 0"); 
		hpacScore.setFont(new Font("Serif", Font.PLAIN, 35));
		hpacScore.setHorizontalAlignment(SwingConstants.CENTER);
		wracScore = new JLabel("WRAC Current Score: 0"); 
		wracScore.setFont(new Font("Serif", Font.PLAIN, 35));
		wracScore.setHorizontalAlignment(SwingConstants.CENTER);
		hmgcScore = new JLabel("HMGC Current Score: 0");
		hmgcScore.setFont(new Font("Serif", Font.PLAIN, 35));
		hmgcScore.setHorizontalAlignment(SwingConstants.CENTER);
		judgeWarning = new JLabel("This Screen is for the Judge ONLY");
		judgeWarning.setFont(new Font("Serif", Font.ITALIC, 25));
		judgeWarning.setHorizontalAlignment(SwingConstants.CENTER); 
		this.add(currentAnswer,BorderLayout.CENTER); 

		scorePanel.setLayout(new GridLayout(3,1)); 
		scorePanel.add(wracScore); 
		scorePanel.add(hmgcScore); 
		scorePanel.add(hpacScore); 

		this.add(scorePanel,BorderLayout.SOUTH);
		this.add(judgeWarning,BorderLayout.NORTH);  

	}

	public void updatePanel(Question question){
		removeAll(); 
		scorePanel = new JPanel(); 
		scorePanel.setBackground(Color.WHITE);

		this.setLayout(new BorderLayout()); 
		currentAnswer = new JLabel("<html><center><font color=\"red\">Current Answer:<br /></font>" + question.getAnswer() + "</center></html>"); 
		currentAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		currentAnswer.setFont(new Font("Serif", Font.BOLD, 35));
		hpacScore = new JLabel("HPAC Current Score: " + JeopardyPanel.HPACSCORE); 
		if(JeopardyPanel.HPACSCORE == JeopardyPanel.WRACSCORE){
			hpacScore.setForeground(Color.YELLOW); 
		}else if(JeopardyPanel.HPACSCORE == JeopardyPanel.HMGCSCORE){
			hpacScore.setForeground(Color.YELLOW); 
		}else{
			if(JeopardyPanel.HPACSCORE > JeopardyPanel.WRACSCORE && JeopardyPanel.HPACSCORE > JeopardyPanel.HMGCSCORE){
				hpacScore.setForeground(Color.GREEN); 
			}else{
				hpacScore.setForeground(Color.RED); 
			}
		}
		hpacScore.setFont(new Font("Serif", Font.PLAIN, 35));
		hpacScore.setHorizontalAlignment(SwingConstants.CENTER);
		wracScore = new JLabel("WRAC Current Score: " + JeopardyPanel.WRACSCORE);
		if(JeopardyPanel.WRACSCORE == JeopardyPanel.HPACSCORE){
			wracScore.setForeground(Color.YELLOW); 
		}else if(JeopardyPanel.WRACSCORE == JeopardyPanel.HMGCSCORE){
			wracScore.setForeground(Color.YELLOW); 
		}else{
			if(JeopardyPanel.WRACSCORE > JeopardyPanel.HPACSCORE && JeopardyPanel.WRACSCORE > JeopardyPanel.HMGCSCORE){
				wracScore.setForeground(Color.GREEN); 
			}else{
				wracScore.setForeground(Color.RED); 
			} 
		}
		wracScore.setFont(new Font("Serif", Font.PLAIN, 35));
		wracScore.setHorizontalAlignment(SwingConstants.CENTER);
		hmgcScore = new JLabel("HMGC Current Score: " + JeopardyPanel.HMGCSCORE);
		if(JeopardyPanel.HMGCSCORE == JeopardyPanel.WRACSCORE){
			hmgcScore.setForeground(Color.YELLOW); 
		}else if(JeopardyPanel.HMGCSCORE == JeopardyPanel.HPACSCORE){
			hmgcScore.setForeground(Color.YELLOW); 
		}else{
			if(JeopardyPanel.HMGCSCORE > JeopardyPanel.WRACSCORE && JeopardyPanel.HMGCSCORE > JeopardyPanel.HPACSCORE){
				hmgcScore.setForeground(Color.GREEN); 
			}else{
				hmgcScore.setForeground(Color.RED); 
			}
		}
		hmgcScore.setFont(new Font("Serif", Font.PLAIN, 35));
		hmgcScore.setHorizontalAlignment(SwingConstants.CENTER);
		judgeWarning = new JLabel("This Screen is for the Judge ONLY");
		judgeWarning.setFont(new Font("Serif", Font.ITALIC, 25));
		judgeWarning.setHorizontalAlignment(SwingConstants.CENTER); 
		this.add(currentAnswer,BorderLayout.CENTER); 

		scorePanel.setLayout(new GridLayout(3,1)); 
		scorePanel.add(wracScore); 
		scorePanel.add(hmgcScore); 
		scorePanel.add(hpacScore); 

		this.add(scorePanel,BorderLayout.SOUTH);
		this.add(judgeWarning,BorderLayout.NORTH);

		this.revalidate();
		JeopardyDriver.judgeFrame.setVisible(false); 
		JeopardyDriver.judgeFrame.pack(); 
		if(JeopardyDriver.judgeFrame.getWidth() >=500){
			JeopardyDriver.judgeFrame.setSize(JeopardyDriver.judgeFrame.getWidth()+37,500); 
		}else{
			JeopardyDriver.judgeFrame.setSize(500,500); 
		}
		JeopardyDriver.judgeFrame.setVisible(true); 
	}
}