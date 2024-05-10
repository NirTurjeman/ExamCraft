package nirTurjeman;
//ArrayList
@SuppressWarnings("serial")
public class ChoiceQuestion extends Question {

	public ChoiceQuestion(String theQuestion) {
		super(theQuestion);
	}

	public boolean setValue(int ansNum, boolean value) {
		ans.setValue(ansNum, value);
		return true;
	}

//	public boolean addAnswer(Answer ans, boolean value) {
//		if (this.ans.getNumberOfAnswers() < 12) {
//			this.ans.addAnswer(ans, value);
//			return true;
//		}
//		return true;
//	}

	public boolean deleteAnswer(int ansNum) {
		ans.deleteAnswer(ansNum);
		return true;
	}

	public boolean getValue(int ansNum) {
		return ans.getValue(ansNum);
	}

	public String getAllOfAnswers() {
		String str = "";
		for (int i = 0; i < getNumberOfAnswers(); i++) {
			str += "\n" + (i + 1) + ")" + ans.getAnswer(i);
		}
		return str;
	}
	public void updateHardCodedValue() {
		ans.updateHardCodedValue();
	}
	public int compareTo(Question o) {
		if(o.getQuestion().equalsIgnoreCase(theQuestion))
			return 1;
		return 0;
	}
	public String toStringForQuiz() {
		if (ans.getNumberOfAnswers() > 0) {
			return "\n//-------------//\n" + "#" + theQuestion + ans.toStringForQuizChoice();
		} else
			return " \n!--with no answers--!";

	}

	public String toString() {
		if (ans.getNumberOfAnswers() > 0)
			return "# " + super.toString() + "(Level:" + eLevel.toString() + ")" + ans.toString();
		else
			return " \n!--with no answers--!";
	}
}
