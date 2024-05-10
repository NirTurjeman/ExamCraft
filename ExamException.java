package nirTurjeman;
//ArrayList,
@SuppressWarnings("serial")
public class ExamException extends Exception {
	public ExamException(String str) {
		super(str);
	}

	public ExamException() {
		super("Error! ,General Exception");
	}
}
