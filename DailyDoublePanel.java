import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 

public class DailyDoublePanel extends JPanel{

	private JLabel dailyDouble; 
	private Random rand; 
	private javax.swing.Timer timer;
	private JLabel wagerAmount; 
	private JTextField wagerAmountField;  
	private JPanel wagerPanel; 
	private JButton next; 
	private final Question question; 

	public DailyDoublePanel(Question givenquestion){

		setBackground(Color.WHITE); 

		this.question = givenquestion; 
		this.setLayout(new BorderLayout()); 
		wagerPanel = new JPanel(); 
		wagerAmount = new JLabel("Enter Wager Amount");
		wagerAmount.setFont(new Font("Serif",Font.BOLD,40)); 
		wagerAmountField = new JTextField("Maximum Wager Amount: " + question.getPointValue()*2);  
		wagerAmountField.setPreferredSize(new Dimension(300,50));
		wagerAmountField.setFont(new Font("Serif",Font.ITALIC,20)); 
		wagerAmountField.setHorizontalAlignment(JTextField.CENTER);
		next = new JButton("Continue"); 
		next.setPreferredSize(new Dimension(100,100));
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					if(Integer.parseInt(wagerAmountField.getText()) > question.getPointValue()*2){
						throw new Exception("Wager Value Too High");
					}
					question.setPointValue(Integer.parseInt(wagerAmountField.getText()));
					JeopardyPanel.questionPanel.restartTimer(); 
					JeopardyPanel.dailyDoubleFrame.dispose(); 
				}catch(Exception w){
					JOptionPane.showMessageDialog(JeopardyPanel.dailyDoubleFrame,"Invalid Entry to Text Box\nReason: " + w.getMessage()); 
					//return; 
				} 
			}
		});
		rand = new Random(); 
		dailyDouble = new JLabel("DAILY DOUBLE!"); 
		dailyDouble.setFont(new Font("Serif",Font.BOLD,150));
        dailyDouble.setHorizontalAlignment(SwingConstants.CENTER);
        wagerPanel.add(wagerAmount); 
        wagerPanel.add(wagerAmountField); 
        wagerPanel.add(next); 
        this.add(dailyDouble,BorderLayout.CENTER);
        this.add(wagerPanel,BorderLayout.SOUTH);
	}

	public void goForGold(){
        	int delay = 100; 
        	ActionListener taskPerformer = new ActionListener() {
      			public void actionPerformed(ActionEvent evt) {
      				dailyDouble.setForeground(new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));  
        			revalidate();

      			}
      		}; 
        	timer = new javax.swing.Timer(delay, taskPerformer); 
  			timer.start(); 
	}
}