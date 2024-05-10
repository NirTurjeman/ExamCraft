package nirTurjeman;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Quiz implements ExamAble{
	protected ArrayList<Question> q = new ArrayList<Question>();
	protected Fileable toFile;
//	public boolean addQuestion(Question q) throws ExamException {
//		if (q.ans.getNumberOfAnswers() <= 5 && q instanceof ChoiceQuestion) {
//			throw new ExamException("Error: This question has less than 6 answers! It can't be in the quiz question");
//		}
//		this.q.add(q);
//		counter++;
//		for (int i = 0; i < this.q.size(); i++) {
//			if (q.getQuestion().equals(this.q.get(i).getQuestion()))
//				return false;
//		}
//		return true;
//	}

	public abstract void createExam(ArrayList<Question> q) throws ExamException;

	public String instanceofDitails(int qNum) {
		return q.get(qNum).getClass().getSimpleName();
	}
	public String printQuestions() {
		String str = "";
		for (int i = 0; i < q.size(); i++) {
			str += "\n" + (i + 1) + ")" + q.get(i).getQuestion();
		}
		return str;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < q.size(); i++) {
			if (q.get(i) instanceof ChoiceQuestion)
				str += "\n" + ((ChoiceQuestion) q.get(i)).toStringForQuiz();
			else {
				str += ((OpenQuestion) q.get(i)).toStringForQuiz();
			}
		}
		return str;
	}

	public boolean toTextFile() throws FileNotFoundException, IOException {
		if (q.size() == 0)
			return false;
		toFile = new FileToTxt(q);
		toFile.toExport();
//		LocalDateTime myDateObj = LocalDateTime.now();
//		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("YYYY_MM_yy_hh_mm");
//		String formattedDate = myDateObj.format(myFormatObj);
//		File exam = new File("exam_" + formattedDate + ".txt/");
//		File solution = new File("solution_" + formattedDate + ".txt/");
//		PrintWriter printExam = new PrintWriter(exam);
//		PrintWriter printSolution = new PrintWriter(solution);
//		printExam.printf("%20s", "Quiz");
//		printSolution.printf("%20s", "Solution");
//		for (int i = 0; i < q.size(); i++) {
//			printExam.print("\n" + q.get(i).toStringForQuiz());
//			printSolution.print("\n" + q.get(i).toString());
//		}
//		printExam.close();
//		printSolution.close();
		return true;
	}
}
