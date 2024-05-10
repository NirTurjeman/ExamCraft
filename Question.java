package nirTurjeman;

import java.io.Serializable;
//ArrayList
@SuppressWarnings("serial")
public abstract class Question implements Serializable,Comparable<Question>{
	protected String theQuestion;
	protected int id;
	protected static int counter;

	public enum levelOfQuestion {
		EASY, MEDIUM, HARD
	};

	protected levelOfQuestion eLevel;
	protected AnswersValue ans = new AnswersValue();

	public Question(String theQuestion) {
		this.theQuestion = theQuestion;
		id = counter++;
	}
	
	public boolean setLevel(levelOfQuestion eLevel) {
		this.eLevel = eLevel;
		return true;
	}
	public levelOfQuestion getLevel() {
		return this.eLevel;
	}

	public String getAnswerString(int ansNum) {
		return ans.getAnswer(ansNum);
	}

	public String getAllOfAnswers(int ansNum) {
		String str = "***-------ANSWERS-------***\n";
		for (int i = 0; i < ans.getNumberOfAnswers(); i++) {
			str += (i + 1) + ")" + getAnswerString(i) + "\n";
		}
		str += "***---------------------***";
		return str;
	}
	
	//public abstract boolean addAnswer(Answer ans, boolean value);

	public String getQuestion() {
		return theQuestion;
	}
	public int getId() {
		return id;
	}
	public boolean setQuestion(String theQuesion) {
		this.theQuestion = theQuesion;
		return true;
	}

	public int getNumberOfAnswers() {
		return ans.getNumberOfAnswers();
	}
	public abstract String toStringForQuiz();

	public  String toString(){
		return theQuestion;
	}
}
