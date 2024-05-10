package nirTurjeman;
public class OpenQuestion extends Question {
	private static final long serialVersionUID = 1L;
	public OpenQuestion(String theQuestion) {
		super(theQuestion);
	}

//	public boolean addAnswer(Answer ans, boolean Value) {
//		if (this.ans.getNumberOfAnswers() == 1) {
//			return false;
//		}
//		this.ans.addAnswer(ans, true);
//		return true;
//	}
	@Override
	public String toStringForQuiz() {
		return "\n//-------------//\n" + "#" + theQuestion + "\nANSWER: _______________________.";
	}

	public String toString() {
		return "#" + super.toString() + "(Level: " + eLevel.toString() + ")\nANSWER: " + ans.getAnswer(0);
	}
	public int compareTo(Question o) {
		if(o.getQuestion().equalsIgnoreCase(theQuestion)&& o.getAnswerString(0) == this.getAnswerString(0))
			return 1;
	return 0;	
	}
}
