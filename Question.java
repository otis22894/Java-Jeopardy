public class Question{
	
	private int points;
	private String question; 
	private String answer;  
	private boolean answered; 

	public Question(int pointValue, String question, String answer){
		this.points = pointValue; 
		this.question = question; 
		this.answer = answer;
		answered = false;  
	}

	public int getPointValue(){
		return points; 
	}

	public String getQuestion(){
		return question; 
	}

	public String getAnswer(){
		return answer; 
	}

	public void questionAnswered(){
		answered = true; 
	}

	public boolean isAnswered(){
		return answered; 
	}

	public void setPointValue(int pointValue){
		points = pointValue; 
	}
}
