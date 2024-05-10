package nirTurjeman;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ManualQuiz extends Quiz implements Serializable {

//	public boolean addAnswer(int questionNum, int ansNum, boolean value) {
//		if (questionNum > q.size() || questionNum < 0 || ansNum > ans.size() || ansNum < 0)
//			return false;
//		((ChoiceQuestion) q.get(questionNum)).addAnswer(ans.get(ansNum), value);
//		((ChoiceQuestion) q.get(questionNum)).updateHardCodedValue();
//		return true;
//	}

//	public boolean deleteAnswer(int questionNum, int ansNum) {
//		if (questionNum > q.size() || questionNum < 0 || ansNum > ans.size() || ansNum < 2)
//			return false;
//
//		((ChoiceQuestion) q.get(questionNum)).deleteAnswer(ansNum);
//		((ChoiceQuestion) q.get(questionNum)).updateHardCodedValue();
//		return true;
//	}
	@Override
	public void createExam(ArrayList<Question> q) throws ExamException {
		ArrayList<Question> quizQuestions = new ArrayList<Question>();
		this.q = quizQuestions;
	}
}
