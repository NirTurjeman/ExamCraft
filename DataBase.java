package nirTurjeman;

import java.io.Serializable;
import java.util.ArrayList;
//ArrayList
@SuppressWarnings("serial")
public class DataBase implements Serializable {

	public ArrayList<Question> q = new ArrayList<Question>();
	public ArrayList<Answer> ans = new ArrayList<Answer>();
	public Quiz quiz;
	private String mainTopic;

	public DataBase(String mainTopic) {
		this.mainTopic = mainTopic;
		ans.add(new Answer("There is more than one correct answer"));
		ans.add(new Answer("None of the answers are correct"));
	}

	public String printAll() {
		String str = "";
		if (q.size() > 0) {
			str += "//-------------//\n";
			for (int i = 0; i < q.size(); i++) {
				str += q.get(i).toString() + "\n//-------------//\n";
			}
			return str;
		} else
			str += "!--There are no Questions in the system yet--!";
		return str;
	}

	public String printAnswerList() {
		String str = "";
		if (ans.size() > 0) {
			str += "*-----Answers list:-----*\n";
			for (int i = 0; i < ans.size(); i++) {
				str += (i + 1) + ")" + ans.get(i).toString() + "\n";
			}
		} else
			str += "!--There are no Answers in the system yet--!";
		return str;
	}

	public String getMainTopic() {
		return mainTopic;
	}

}
