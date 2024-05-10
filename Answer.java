package nirTurjeman;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Answer implements Serializable,Comparable<Answer>{
	private String theAnswer;

	public Answer(String theAnswer) {
		this.theAnswer = theAnswer;
	}

	public String getAnswer() {
		return theAnswer;
	}

	public boolean setAnswer(String newAnswer) {
		this.theAnswer = newAnswer;
		return true;
	}

	public boolean deleteAnswer() {
		this.theAnswer = null;
		return true;
	}

	public String toString() {
		return theAnswer;
	}
	public int compareTo(Answer o){
		if(this.theAnswer.equalsIgnoreCase(o.getAnswer()))
			return 1;
		return 0;
	}

}
