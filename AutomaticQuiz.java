package nirTurjeman;

import java.io.Serializable;
import java.util.ArrayList;

//V
@SuppressWarnings("serial")
public class AutomaticQuiz extends Quiz implements Serializable {
	private int numberOfQuestions;
	public AutomaticQuiz(int numberOfQuestions,ArrayList<Question> questionDataBase) throws Exception {
		if(numberOfQuestions > getNumOfQuestionForExam(questionDataBase))
			throw new ExamException();
		this.numberOfQuestions = numberOfQuestions;
	}

	public void createExam(ArrayList<Question> q) throws ExamException {
		int checker, count = 0;
		boolean duplicuteQuestion = false;
		if (q.size() > getNumOfQuestionForExam(q))
			throw new ExamException(
					q.size()-1 + " This is a larger number of quetstions than the corresponding question");

		for (int i = 0; i < numberOfQuestions; i++) {
			duplicuteQuestion = true;
			while (duplicuteQuestion) {
				duplicuteQuestion = false;
				checker = (int) (Math.random() * q.size());
				if (q.get(checker).getNumberOfAnswers() >= 6 && !(q.get(checker).ans.getValue(0))
						|| q.get(checker) instanceof OpenQuestion) {
					if (this.q.size() == 0) {
						this.q.add(q.get(checker));
						count++;
					} else {
						for (int j = 0; j < count; j++) {
							if (this.q.get(j).equals(q.get(checker))) {
								duplicuteQuestion = true;
								break;
							}
						}

						if (!(duplicuteQuestion)) {
							this.q.add(q.get(checker));
							count++;
						}
					}
					if (q.get(checker) instanceof ChoiceQuestion) {
						((ChoiceQuestion) this.q.get(i)).deleteAnswer(0);
						while (this.q.get(i).getNumberOfAnswers() != 4) {
							((ChoiceQuestion) this.q.get(i))
									.deleteAnswer((int) (1 + Math.random() * this.q.get(i).getNumberOfAnswers()));
						}
					}
				}
			}
		}
	}

	public int getNumOfQuestionForExam(ArrayList<Question> q) {
		int counter = 0;
		for (int i = 0; i < q.size(); i++) {
			if((q.get(i).getNumberOfAnswers() >= 6 && !(q.get(i).ans.getValue(0)) || q.get(i) instanceof OpenQuestion)){
				counter++;
			}
		}
		return counter;
	}
}
