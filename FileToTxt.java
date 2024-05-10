package nirTurjeman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileToTxt implements Fileable {
	ArrayList<Question> questionToTxt;
	public FileToTxt(ArrayList<Question> q) {
		questionToTxt = q;
	}
	@Override
	public ArrayList<?> toImport() throws FileNotFoundException, IOException, ClassNotFoundException{
		throw new IOException("in this program there are no import of Txt");
	}
	@Override
	public void toExport() throws FileNotFoundException, IOException {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("YYYY_MM_yy_hh_mm");
		String formattedDate = myDateObj.format(myFormatObj);
		File exam = new File("exam_" + formattedDate + ".txt/");
		File solution = new File("solution_" + formattedDate + ".txt/");
		PrintWriter printExam = new PrintWriter(exam);
		PrintWriter printSolution = new PrintWriter(solution);
		printExam.printf("%20s", "Quiz");
		printSolution.printf("%20s", "Solution");
		for (int i = 0; i < questionToTxt.size(); i++) {
			printExam.print("\n" + questionToTxt.get(i).toStringForQuiz());
			printSolution.print("\n" + questionToTxt.get(i).toString());
		}
		printExam.close();
		printSolution.close();
	}

}
