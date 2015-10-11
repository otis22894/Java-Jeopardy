import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.*;

import java.net.URL;
import java.util.Locale;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Transmitter;
import javax.swing.JOptionPane;
import java.io.*;
import java.net.MalformedURLException; 
import javax.sound.midi.InvalidMidiDataException; 

public class FinalJeopardyPanel extends JPanel{
	
	public static int whitneyWager; 
	public static int multiWater; 
	public static int heritageWager; 

	private int i; 
	public JLabel finalJeopardy; 
	public JButton seeQuestion; 
	public static final JLabel questionLabel = new JLabel("<html><center>What is the Cardiac Chain of Survival <br />(In No Specific Order)</center></html>");
	public javax.swing.Timer timer; 

	private JLabel wracWager; 
	private JLabel hmgcWager; 
	private JLabel hpacWager; 
	private JTextField wracWagerAmount; 
	private JTextField hmgcWagerAmount; 
	private JTextField hpacWagerAmount; 
	private JButton finalScore; 

	private JPanel wagerPanel; 

	private JLabel whitney; 
	private JLabel multigen; 
	private JLabel heritage; 
	private JRadioButton whitneyCorrect; 
	private JRadioButton whitneyIncorrect; 
	private JRadioButton multigenCorrect; 
	private JRadioButton multigenIncorrect; 
	private JRadioButton heritageCorrect; 
	private JRadioButton heritageIncorrect;
	private ButtonGroup whitneyButtonGroup; 
	private ButtonGroup multigenButtonGrouup; 
	private ButtonGroup heritageButtonGroup; 

	public FinalJeopardyPanel(){

		setBackground(Color.WHITE); 

		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setFont(new Font("Serif", Font.BOLD, 65));
		i = 60; 
		this.setLayout(new BorderLayout()); 
		finalJeopardy = new JLabel("<html><center>Welcome to Final Jeopardy<br />Please hand out paper to each team<br />Press Continue to see question and start timer<br /><font color=\"red\">INCLUDE ANSWER AND WAGER AMOUNT<br />ON PAPER</font><center></html>"); 
		finalJeopardy.setHorizontalAlignment(SwingConstants.CENTER);
		finalJeopardy.setFont(new Font("Serif", Font.BOLD, 45));
		this.add(finalJeopardy,BorderLayout.CENTER); 
		seeQuestion = new JButton("Continue");
		this.add(seeQuestion,BorderLayout.SOUTH); 
		seeQuestion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
					int delay = 1000; 
					JeopardyDriver.judgePanel.updatePanel(new Question(0,"","Early Recognition,Early CPR,Early Defibrilation,Early EMS"));  
  					ActionListener taskPerformer = new ActionListener() {
      					public void actionPerformed(ActionEvent evt) {
      						JeopardyPanel.finalJeopardyPanel.removeAll(); 
         					JeopardyPanel.finalJeopardyPanel.add(questionLabel,BorderLayout.CENTER); 
         					JLabel timerLabel = new JLabel(Integer.toString(i)); 
         					timerLabel.setFont(new Font("Serif",Font.BOLD,55));
         					timerLabel.setHorizontalAlignment(SwingConstants.CENTER);  
							JeopardyPanel.finalJeopardyPanel.add(timerLabel,BorderLayout.SOUTH); 
							JeopardyPanel.finalJeopardyPanel.revalidate();
							i--; 
							if(i == -1){
								timer.stop();
								JeopardyPanel.finalJeopardyPanel.removeAll(); 
								JLabel turnInLabel = new JLabel("<html><center>Turn your paper<br />into the judge</center></html>"); 
         						turnInLabel.setFont(new Font("Serif",Font.BOLD,55));
         						turnInLabel.setHorizontalAlignment(SwingConstants.CENTER);
         						JeopardyPanel.finalJeopardyPanel.add(turnInLabel,BorderLayout.NORTH); 

         						wagerPanel = new JPanel(); 
								wagerPanel.setLayout(new GridLayout(4,2)); 

         						wracWager = new JLabel("<html><right>Enter WRAC WAGER:</right></html>"); 
								wracWager.setFont(new Font("Serif", Font.BOLD, 35));
								wracWager.setHorizontalAlignment(SwingConstants.CENTER);
								hmgcWager = new JLabel("<html><right>Enter HMGC WAGER:</right></html>"); 
								hmgcWager.setFont(new Font("Serif", Font.BOLD, 35));
								hmgcWager.setHorizontalAlignment(SwingConstants.CENTER);
								hpacWager = new JLabel("<html><right>Enter HPAC WAGER:</right></html>"); 
								hpacWager.setFont(new Font("Serif", Font.BOLD, 35));
								hpacWager.setHorizontalAlignment(SwingConstants.CENTER);
								wracWagerAmount = new JTextField("MAXIMUM WRAC WAGER: " + JeopardyPanel.WRACSCORE); 
								hmgcWagerAmount = new JTextField("MAXIMUM HMGC WAGER: " + JeopardyPanel.HMGCSCORE); 
								hpacWagerAmount = new JTextField("MAXIMUM HPAC WAGER: " + JeopardyPanel.HPACSCORE);
								wracWagerAmount.setFont(new Font("Serif",Font.ITALIC,20)); 
								wracWagerAmount.setHorizontalAlignment(JTextField.CENTER);
								hmgcWagerAmount.setFont(new Font("Serif",Font.ITALIC,20)); 
								hmgcWagerAmount.setHorizontalAlignment(JTextField.CENTER);
								hpacWagerAmount.setFont(new Font("Serif",Font.ITALIC,20)); 
								hpacWagerAmount.setHorizontalAlignment(JTextField.CENTER);

								wagerPanel.setBackground(Color.WHITE); 
								wagerPanel.add(wracWager); 
								wagerPanel.add(wracWagerAmount); 
								wagerPanel.add(hmgcWager); 
								wagerPanel.add(hmgcWagerAmount); 
								wagerPanel.add(hpacWager); 
								wagerPanel.add(hpacWagerAmount);

								finalScore = new JButton("Continue"); 
								finalScore.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent e){
										try{
											whitneyWager = Integer.parseInt(wracWagerAmount.getText());
											if(whitneyWager > JeopardyPanel.WRACSCORE){
												throw new Exception("WRAC Wager Too High"); 
											} 
											multiWater = Integer.parseInt(hmgcWagerAmount.getText());
											if(multiWater > JeopardyPanel.HMGCSCORE){
												throw new Exception("HMGC Wager Too High"); 
											} 
											heritageWager = Integer.parseInt(hpacWagerAmount.getText()); 
											if(heritageWager > JeopardyPanel.HPACSCORE){
												throw new Exception("HPAC Wager Too High"); 
											}
										}catch(Exception w){
											JOptionPane.showMessageDialog(null,"Invalid Entry to Text Box\nReason: " + w.getMessage());
											return; 
										}
										JeopardyPanel.finalJeopardyPanel.removeAll();
										JeopardyPanel.finalJeopardyPanel.setLayout(new FlowLayout()); 
										JeopardyPanel.finalJeopardyPanel.setSize(100,200);  
										
										whitney = new JLabel("WRAC Result"); 
										whitney.setFont(new Font("Serif",Font.BOLD,35));
										whitneyCorrect = new JRadioButton("Correct");
										whitneyCorrect.setForeground(Color.GREEN);
										whitneyCorrect.setBackground(Color.ORANGE);  
										whitneyCorrect.setFont(new Font("Serif",Font.BOLD,35));
										whitneyCorrect.addActionListener(new ButtonListner());  
										whitneyIncorrect = new JRadioButton("Incorrect");
										whitneyIncorrect.setForeground(Color.RED); 
										whitneyIncorrect.setBackground(Color.ORANGE); 
										whitneyIncorrect.setFont(new Font("Serif",Font.BOLD,35));
										whitneyIncorrect.addActionListener(new ButtonListner());  
										whitneyButtonGroup = new ButtonGroup(); 
										whitneyButtonGroup.add(whitneyCorrect); 
										whitneyButtonGroup.add(whitneyIncorrect); 

										multigen = new JLabel("HMGC Result"); 
										multigen.setFont(new Font("Serif",Font.BOLD,35));
										multigenCorrect = new JRadioButton("Correct");
										multigenCorrect.setForeground(Color.GREEN); 
										multigenCorrect.setBackground(Color.MAGENTA); 
										multigenCorrect.setFont(new Font("Serif",Font.BOLD,35));
										multigenCorrect.addActionListener(new ButtonListner()); 
										multigenIncorrect = new JRadioButton("Incorrect");
										multigenIncorrect.setForeground(Color.RED); 
										multigenIncorrect.setBackground(Color.MAGENTA); 
										multigenIncorrect.setFont(new Font("Serif",Font.BOLD,35));
										multigenIncorrect.addActionListener(new ButtonListner()); 
										multigenButtonGrouup = new ButtonGroup(); 
										multigenButtonGrouup.add(multigenCorrect); 
										multigenButtonGrouup.add(multigenIncorrect);

										heritage = new JLabel("HPAC Result");
										heritage.setFont(new Font("Serif",Font.BOLD,35)); 
										heritageCorrect = new JRadioButton("Correct");
										heritageCorrect.setForeground(Color.GREEN); 
										heritageCorrect.setBackground(Color.lightGray); 
										heritageCorrect.setFont(new Font("Serif",Font.BOLD,35)); 
										heritageCorrect.addActionListener(new ButtonListner());
										heritageIncorrect = new JRadioButton("Incorrect");
										heritageIncorrect.setForeground(Color.RED); 
										heritageIncorrect.setBackground(Color.lightGray); 
										heritageIncorrect.setFont(new Font("Serif",Font.BOLD,35));
										heritageIncorrect.addActionListener(new ButtonListner()); 
										heritageButtonGroup = new ButtonGroup(); 
										heritageButtonGroup.add(heritageCorrect); 
										heritageButtonGroup.add(heritageIncorrect);

										JeopardyPanel.finalJeopardyPanel.add(whitney); 
										JeopardyPanel.finalJeopardyPanel.add(whitneyCorrect); 
										JeopardyPanel.finalJeopardyPanel.add(whitneyIncorrect); 
										JeopardyPanel.finalJeopardyPanel.add(multigen); 
										JeopardyPanel.finalJeopardyPanel.add(multigenCorrect); 
										JeopardyPanel.finalJeopardyPanel.add(multigenIncorrect);
										JeopardyPanel.finalJeopardyPanel.add(heritage); 
										JeopardyPanel.finalJeopardyPanel.add(heritageCorrect); 
										JeopardyPanel.finalJeopardyPanel.add(heritageIncorrect);  
										JeopardyPanel.finalJeopardyPanel.setPreferredSize(new Dimension(550,200));  
										JeopardyPanel.finalJeopardyFrame.setSize(550,200);
										JeopardyPanel.finalJeopardyFrame.pack();
									}
								});

								JeopardyPanel.finalJeopardyPanel.add(wagerPanel,BorderLayout.CENTER);  
								JeopardyPanel.finalJeopardyPanel.add(finalScore,BorderLayout.SOUTH); 
							}else if(i == 59 || i == 30){
								try {
				        			Sequence sequence = MidiSystem.getSequence(new File("jeopardy.mid"));
				        			Sequencer sequencer = MidiSystem.getSequencer();
				        			sequencer.open();
				       				sequencer.setSequence(sequence);
								    sequencer.start();
							    } catch (MalformedURLException w) {System.out.println("Here");
							    } catch (IOException w) {System.out.println("HEre");
							    } catch (MidiUnavailableException w) {System.out.println("HeRe");
							    } catch (InvalidMidiDataException w) {System.out.println("HerE");
							    } 
							}
      					}
  					};
  					timer = new javax.swing.Timer(delay, taskPerformer); 
  					timer.start();
			}
		});
	}

	private class ButtonListner implements ActionListener{

		public void actionPerformed(ActionEvent e){
			if((whitneyCorrect.isSelected() || whitneyIncorrect.isSelected()) && (multigenCorrect.isSelected() || multigenIncorrect.isSelected()) && (heritageCorrect.isSelected() || heritageIncorrect.isSelected())){
				if(whitneyCorrect.isSelected()){
					JeopardyPanel.WRACSCORE += whitneyWager; 
				}else{
					JeopardyPanel.WRACSCORE -= whitneyWager; 
				}
				if(multigenCorrect.isSelected()){
					JeopardyPanel.HMGCSCORE += multiWater; 
				}else{
					JeopardyPanel.HMGCSCORE -= multiWater; 
				}
				if(heritageCorrect.isSelected()){
					JeopardyPanel.HPACSCORE += heritageWager; 
				}else{
					JeopardyPanel.HPACSCORE -= heritageWager; 
				}
				JeopardyPanel.finalJeopardyPanel.removeAll();
				JLabel wracFinal = new JLabel("WRAC FINAL SCORE: " + JeopardyPanel.WRACSCORE); 
				wracFinal.setFont(new Font("Serif", Font.BOLD, 45));
				JLabel heritageFinal = new JLabel("HPAC FINAL SCORE: " + JeopardyPanel.HPACSCORE); 
				heritageFinal.setFont(new Font("Serif", Font.BOLD, 45)); 
				JLabel multigenFinal = new JLabel("HMGC FINAL SCORE: " + JeopardyPanel.HMGCSCORE); 
				multigenFinal.setFont(new Font("Serif",Font.BOLD,45)); 
				if(JeopardyPanel.WRACSCORE > JeopardyPanel.HPACSCORE && JeopardyPanel.WRACSCORE > JeopardyPanel.HMGCSCORE){
					wracFinal.setForeground(Color.GREEN);
				}else{
					wracFinal.setForeground(Color.RED); 
				}
				if(JeopardyPanel.HMGCSCORE > JeopardyPanel.WRACSCORE && JeopardyPanel.HMGCSCORE > JeopardyPanel.HPACSCORE){
					multigenFinal.setForeground(Color.GREEN);
				}else{
					multigenFinal.setForeground(Color.RED); 
				}
				if(JeopardyPanel.HPACSCORE > JeopardyPanel.WRACSCORE && JeopardyPanel.HPACSCORE > JeopardyPanel.HMGCSCORE){
					heritageFinal.setForeground(Color.GREEN);
				}else{
					heritageFinal.setForeground(Color.RED); 
				}
				JeopardyPanel.finalJeopardyPanel.add(wracFinal);
				JeopardyPanel.finalJeopardyPanel.add(heritageFinal);
				JeopardyPanel.finalJeopardyPanel.add(multigenFinal);
				JeopardyPanel.finalJeopardyPanel.revalidate(); 
				JeopardyPanel.finalJeopardyPanel.setPreferredSize(new Dimension(600,200));  
				JeopardyPanel.finalJeopardyFrame.setSize(600,200);
				JeopardyPanel.finalJeopardyFrame.pack(); 
			}
		}
	}
}