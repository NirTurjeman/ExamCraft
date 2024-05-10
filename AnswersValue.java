package nirTurjeman;

import java.io.Serializable;
import java.util.ArrayList;
//V
public class AnswersValue implements Serializable{
	private static final long serialVersionUID = 1L;
	private final int MAX_OF_ANSWERS = 12;
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	private ArrayList<Boolean> value = new ArrayList<Boolean>();

	public boolean addAnswer(Answer ans, boolean value) {
		if (this.answers.size() == MAX_OF_ANSWERS)
			return  false;
		this.answers.add(ans);
		this.value.add(value);
		return true;
	}

	public boolean getValue(int ansNumber) {
		return value.get(ansNumber);
	}

	public boolean setValue(int ansNumber, boolean value) {
		return this.value.set(ansNumber, value);
	}

	public int getNumberOfAnswers() {
		return this.answers.size();
	}

	public String getAnswer(int ansNum) {
		return this.answers.get(ansNum).toString();
	}

	public boolean deleteAnswer(int ansNumber) {
		this.answers.remove(ansNumber);
		this.value.remove(ansNumber);
		return true;
	}

	public void updateHardCodedValue() {
		int numOfTrueAnswers = 0;
		for (int i = 2; i < value.size(); i++) {
			if (value.get(i))
				numOfTrueAnswers++;
		}
		if (numOfTrueAnswers == 0) {
			setValue(0, false);
			setValue(1, true);
		}
		if (numOfTrueAnswers == 1) {
			setValue(0, false);
			setValue(1, false);
		}
		if (numOfTrueAnswers >= 2) {
			setValue(0, true);
		}
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < answers.size(); i++) {
			str += "\n" + (i + 1) + ") " + answers.get(i).toString() + "(" + getValue(i) + ")";

		}
		return str;
	}

	public String toStringForQuizChoice() {
		String str = "";
		for (int i = 0; i < answers.size(); i++) {
			str += "\n" + (i + 1) + ") " + answers.get(i).toString();
		}
		return str;
	}
}
