package nirTurjeman;

import java.io.*;
import java.util.*;

public class Program {
	public static final int EXIT = -1;
	public static final int PRINT_ALL = 1;
	public static final int ADD_ANSWER = 2;
	public static final int ADD_QUESTION = 3;
	public static final int DELETE_ANSWER_FROM_QUESTION = 4;
	public static final int DELETE_ALL = 5;
	public static final int CREATE_EXAM = 6;
	public static Scanner in = new Scanner(System.in);
	public static boolean compleateTest = false;
	public static int subjectNum;
	public static Fileable toFile;
	public static ArrayList<DataBase> data = new ArrayList<DataBase>();
	public static void main(String[] args) throws Exception {
		int choice = 0;
		boolean errorInput = true;
		try {
			importData(data);
		}catch(Exception e) {
			System.out.println("Data import failed!");
		}

		while (errorInput) {
			System.out.println("\n***------------------MENU------------------***");
			System.out.println("1) Continue creating a test on an existing topic");
			System.out.println("2) Create a test on a new topic");
			System.out.println("***------------------------------------------***");
			choice = in.nextInt();
			if (choice != 1 && choice != 2) {
				System.out.println("The input must be between 1-2!");
				continue;
			}
			if (choice == 1) {
				System.out.println("***---------Subjects---------***");
				for (int i = 0; i < data.size(); i++) {
					System.out.println((i + 1) + ") " + data.get(i).getMainTopic());
				}
				subjectNum = in.nextInt() - 1;
				if (subjectNum > data.size() - 1 || subjectNum < 0) {
					System.out.println("ERROR INPUT!");
					continue;
				}

			} else {
				System.out.println("What is the main Topic to create?");
				data.add(new DataBase(in.next()));
				data.get(data.size()-1);
				subjectNum = data.size() - 1;
			}
			errorInput = false;
		}
		do {
			System.out.println("\n***------------------ADMIN MENU------------------***");
			System.out.printf("Subject is: %s\n", data.get(subjectNum).getMainTopic());
			System.out.println("-1) Exit and save");
			System.out.println("1) Show all of the questions and answers");
			System.out.println("2) Add an answer to an existing question/to Answers list");
			System.out.println("3) Add new question");
			System.out.println("4) Delete answer from question");
			System.out.println("5) Delete question and all the answers");
			System.out.println("6) Exam menu");
			System.out.println("***----------------------------------------------***");
			try {
				choice = in.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Error input must be numbers!");
				in.next();
			}
			switch (choice) {
			case PRINT_ALL:
				printAll(data.get(subjectNum).q, data.get(subjectNum).ans);
				break;
			case ADD_ANSWER:
				data.get(subjectNum).ans = addAnswer(data.get(subjectNum).q, data.get(subjectNum).ans);
				break;
			case ADD_QUESTION:
				data.get(subjectNum).q = addQuestion(data.get(subjectNum).q, data.get(subjectNum).ans);
				break;
			case DELETE_ANSWER_FROM_QUESTION:
				deleteAnswer(data.get(subjectNum).q);
				break;
			case DELETE_ALL:
				data.get(subjectNum).q = deleteAll(data.get(subjectNum).q);
				break;
			case CREATE_EXAM:
				data.get(subjectNum).quiz = createExam(data.get(subjectNum).q,data.get(subjectNum).ans, data.get(subjectNum).quiz);
				break;
			case EXIT:
				export(data);
				System.out.println("Thank you");
				break;
			default:
				System.out.printf("Please write a option between %d to %d", CREATE_EXAM, PRINT_ALL);
			}
		} while (choice != EXIT);
	}

	public static void printAll(ArrayList<Question> q, ArrayList<Answer> ans) {
		int choice;
		System.out.println("1)To print all the questions with their answers");
		System.out.println("2)To print the answers list");
		choice = in.nextInt();
		if (choice > 2 || choice < 1)
			System.out.println("Please write a option between 1-2");
		else {
			switch (choice) {
			case 1:
				System.out.print(data.get(subjectNum).printAll());
				break;
			case 2:
				System.out.print(data.get(subjectNum).printAnswerList());
				break;
			}
		}

	}

	public static ArrayList<Question> addQuestion(ArrayList<Question> q, ArrayList<Answer> ans) {
		int res, levelRes;
		String theQuestion,theAnswer;
		System.out.println("Which Question do you want to add?:");
		System.out.println("1) Open Question");
		System.out.println("2) Multiple Choice Question");
		res = in.nextInt();
		if (res != 1 && res != 2) {
			System.out.println("Error you are need to write number between 1-2!");
			return q;
		}
		System.out.printf("write your question:");
		in.nextLine();
		theQuestion = in.nextLine();
		if (!(theQuestion.contains("?")))
			theQuestion += "?";
		if (res == 1) {
			q.add(new OpenQuestion(theQuestion));
			System.out.println("Write a Answer for this question:");
			theAnswer = in.nextLine();
			ans.add(new Answer(theAnswer));
			//q.get(q.size()-1).addAnswer(ans.get(ans.size()-1),true);
			q.get(q.size()-1).ans.addAnswer(ans.get(ans.size() - 1),true);
		}
		if (res == 2) {
			q.add(new ChoiceQuestion(theQuestion));
			q.get(q.size()-1).ans.addAnswer(ans.get(0),false);
			q.get(q.size()-1).ans.addAnswer(ans.get(1),true);
//			q.get(q.size()-1).addAnswer(ans.get(0),false);
//			q.get(q.size()-1).addAnswer(ans.get(1),true);
		}
		for(int i =0 ;i<q.size()-2;i++) {
			if(q.get(i).compareTo(q.get(q.size()-1)) == 1) {
				q.remove(q.size()-1);
				System.out.println("Question is already exists!");
				return q;
			}
		}
		System.out.println("What is the level of this question? (1,2,3)");
		System.out.println("1.Easy");
		System.out.println("2.Medium");
		System.out.println("3.Hard");
		levelRes = in.nextInt();
		if (levelRes == 1)
			q.get(q.size()-1).setLevel(Question.levelOfQuestion.EASY);
		if (levelRes == 2)
			q.get(q.size()-1).setLevel(Question.levelOfQuestion.MEDIUM);
		if (levelRes == 3)
			q.get(q.size()-1).setLevel(Question.levelOfQuestion.HARD);
		if (levelRes != 1 && levelRes != 2 && levelRes != 3) {
			System.out.println("Error you are need to write number between 1-3!");
			return q;
		}
		return q;
	}
	public static ArrayList<Answer> addAnswer(ArrayList<Question> q, ArrayList<Answer> ans) {
		Answer theAns = new Answer("");
		int qNum, ansNum = -1, choice;
		boolean answerValue;
		ListIterator<Question> questionListIterator = q.listIterator();
		System.out.println("1)Add answer to existing question");
		System.out.println("2)Add answer to Answer array");
		choice = in.nextInt();
		switch (choice) {
		case 1:
			if (q.size() == 0) {
				System.out.println("!--There are no Questions in the system yet--!");
				return ans;
			}
			System.out.println("Select a Question:");
			while(questionListIterator.hasNext()){
				if (questionListIterator.next() instanceof ChoiceQuestion)
					System.out.println((questionListIterator.nextIndex() + 1) + ")" + questionListIterator.next().getQuestion());
				else
					System.out.println((questionListIterator.nextIndex() + 1) + ")" + questionListIterator.next().getQuestion() + " (This question don't required more answers)");
			}
			qNum = in.nextInt() - 1;
			if (qNum > q.size() - 1 || qNum < 0 || q.get(qNum) instanceof OpenQuestion) {
				System.out.printf("Please write a option between 1 to %d and does not contain open questions",
						q.size());
				return ans;
			}
			System.out.println("Write your Answer with capital letter:");
			in.nextLine();
			theAns.setAnswer(in.nextLine());
			if (ans.contains(theAns)) {
				System.out.println("what is the value of this Answer(true/false):");
				answerValue = in.nextBoolean();
				q.get(qNum).ans.addAnswer(ans.get(ansNum), answerValue);
//				q.get(qNum).addAnswer(ans.get(ansNum), answerValue);
				((ChoiceQuestion) q.get(qNum)).updateHardCodedValue();
				return ans;
			} else {
				ans.add(theAns);
				System.out.println("what is the value of this Answer(true/false):");
				answerValue = in.nextBoolean();
				q.get(qNum).ans.addAnswer(ans.get(ans.size()-1), answerValue);
				//q.get(qNum).addAnswer(ans.get(ans.size()-1), answerValue);
				((ChoiceQuestion) q.get(qNum)).updateHardCodedValue();
			}
			break;
		case 2:
			System.out.println("Write your Answer with capital letter:");
			in.nextLine();
			theAns.setAnswer(in.nextLine());
			if (!ans.contains(theAns)) {
				ans.add(theAns);
				return ans;
			} else {
				System.out.println("your Answer already exists!");
				return ans;
			}
		default:
			System.out.println("Please write a option between 1 to 2");
			break;

		}
		return ans;
	}
	public static void deleteAnswer(ArrayList<Question> q) {
		int qNum = 0, ansNum = 0;
		ListIterator<Question> questionListIterator = q.listIterator();
		System.out.println("Select a Question:");
		while(questionListIterator.hasNext()){
			if (questionListIterator.next() instanceof ChoiceQuestion)
				System.out.println((questionListIterator.nextIndex() + 1) + ")" + questionListIterator.next().getQuestion());
			else
				System.out.println(
						(questionListIterator.nextIndex() + 1) + ")" + questionListIterator.next() + " (The answer to this question cannot be deleted)");
		}
		qNum = in.nextInt() - 1;
		if (qNum > q.size() - 1 || qNum < 0 || q.get(qNum) instanceof OpenQuestion) {
			System.out.printf("Please write a option between 1 to %d and does not contain open questions", q.size());
			return;
		}
		System.out.printf("Select a Answer to delete between 3 to %d", ((ChoiceQuestion) q.get(qNum)).getNumberOfAnswers());
		System.out.println(((ChoiceQuestion) q.get(qNum)).getAllOfAnswers());
		ansNum = in.nextInt() - 1;
		if (ansNum > ((ChoiceQuestion) q.get(qNum)).getNumberOfAnswers() || ansNum < 2) {
			System.out.printf("Please try again");
			return;
		}
		((ChoiceQuestion) q.get(qNum)).deleteAnswer(ansNum);
		((ChoiceQuestion) q.get(qNum)).updateHardCodedValue();
	}

	public static ArrayList<Question> deleteAll(ArrayList<Question> q) {
		int qNum;
		System.out.println("Which question do you want to delete");
		System.out.println("Select a Question:");

		printList(q);
		qNum = in.nextInt() - 1;
		if (qNum > q.size() && qNum > q.size() - 1 || qNum < 0) {
			System.out.printf("Please write a option between 1 to %d", q.size());
			return q;
		}
		q.remove(qNum);
		return q;
	}

	public static Quiz createExam(ArrayList<Question> questionDataBase,ArrayList<Answer> ans,Quiz quiz) throws ExamException, IOException {
		int choose = 0, numOfQuestions, counter = 0, selectedQuestion, selectedAnswer, examType;
		boolean value, manualExam = false;
		Quiz newQuiz = null;
		if (compleateTest == false) {
			System.out.println("How would you like to create the test?:(please choose 1 or 2) ");
			System.out.println("1) Manually \n2) Automatically");
			examType = in.nextInt();
			if (examType == 1)
				manualExam = true;
			if (examType == 2)
				manualExam = false;
			if (examType != 1 && examType != 2) {
				System.out.println("please try again and choose 1 or 2!");
				return quiz;
			}
			if (manualExam) {
				newQuiz = new ManualQuiz();
				try {
					newQuiz.createExam(questionDataBase);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					return quiz;
				}
				while (choose != -1 && choose != 4) {
					System.out.println("***-------QUIZ MENU-------***");
					System.out.println("-1) Back to main menu");
					System.out.println("1) Add a question ");
					System.out.println("2) Add an answer from answer list");
					System.out.println("3) Delete an Answer from Question");
					System.out.println("4) To the next step");
					System.out.println("***-----------------------***");
					choose = in.nextInt();
					if (questionDataBase.size() == 0 && Math.abs(choose) != 1) {
						System.out.println("!--There are no Questions in the quiz system yet--!");
						return quiz;
					}
					switch (choose) {
					case -1:
						return quiz;
					case 1:
						if (newQuiz.q.size() == 0 || newQuiz.q.size() == 10) {
							System.out.println("Try again!");
							break;
						}
						while (counter != newQuiz.q.size()) {
							System.out.println("Which question do you want to add to your quiz:");
							printList(questionDataBase);
							selectedQuestion = in.nextInt() - 1;
							if (selectedQuestion < 0 || selectedQuestion > questionDataBase.size() - 1) {
								System.out.printf("Please write a option between 1 to %d\n", questionDataBase.size());
								break;
							}
							if (questionDataBase.get(selectedQuestion).getNumberOfAnswers() <= 5 && questionDataBase.get(selectedQuestion) instanceof ChoiceQuestion) {
								System.out.println("Error: This question has less than 6 answers! It can't be in the quiz question");
								continue;
							}
							try {
//								((ManualQuiz) newQuiz).addQuestion(q.get(selectedQuestion));
								((ManualQuiz) newQuiz).q.add(questionDataBase.get(selectedQuestion));
								for (int i = 0; i < newQuiz.q.size(); i++) {
//								if (q.getQuestion().equals(this.q.get(i).getQuestion()))
//									return false;
									if(newQuiz.q.get(i).compareTo(questionDataBase.get(selectedQuestion)) == 1)
											throw new Exception("This question already exists");
								}
							} catch (Exception e) {
								System.err.println(e.getMessage());
								counter--;
							}
							counter++;
						}
						break;
					case 2:
						System.out.println("Which question do you want to add answer?");
						System.out.println(newQuiz.printQuestions());
						selectedQuestion = in.nextInt() - 1;
						if (selectedQuestion < 0 || selectedQuestion > newQuiz.q.size()) {
							System.out.println("Try again!");
							break;
						}
						if (newQuiz.instanceofDitails(selectedQuestion).equals("OpenQuestion")) {
							System.out.println("This question don't required more answers,Try again!");
							break;
						}
						System.out.println("Which answer do you want to add?");
						printList(ans);
						selectedAnswer = in.nextInt() - 1;
						System.out.println("what is the value of this Answer(true/false):");
						value = in.nextBoolean();
//						((ManualQuiz) newQuiz).addAnswer(selectedQuestion, selectedAnswer, value);
						((ManualQuiz) newQuiz).q.get(selectedQuestion).ans.addAnswer(ans.get(selectedAnswer), value);
						((ManualQuiz) newQuiz).q.get(selectedQuestion).ans.updateHardCodedValue();
						break;
					case 3:
						System.out.print("Which question would you like to delete an answer from:");
						System.out.println(newQuiz.printQuestions());
						selectedQuestion = in.nextInt() - 1;
						if (selectedQuestion < 0 || selectedQuestion > newQuiz.q.size()) {
							System.out.println("Try again!");
							break;
						}
						if (newQuiz.instanceofDitails(selectedQuestion).equals("OpenQuestion")) {
							System.out.println("This question cannot be deleted");
							break;
						}
						System.out.println(questionDataBase.get(selectedQuestion).getAllOfAnswers(selectedQuestion));
						System.out.println("Which answer do you want to delete?");
						selectedAnswer = in.nextInt() - 1;
						//((ManualQuiz) newQuiz).deleteAnswer(selectedQuestion, selectedAnswer);
						((ManualQuiz) newQuiz).q.get(selectedQuestion).ans.deleteAnswer(selectedAnswer);
						((ManualQuiz) newQuiz).q.get(selectedQuestion).ans.updateHardCodedValue();
						break;
					case 4:
						if (((ManualQuiz) newQuiz).q.size() == 0) {
							System.out.println("The next step is possible only after creating a test!");
							return quiz;
						}
						quiz = newQuiz;
						break;
					}
				}
			} else {
				try {
					do {
						System.out.println("How many Question do you want in your Quiz:");
						numOfQuestions = in.nextInt();
						if(numOfQuestions <= 0 || numOfQuestions > 10) {
							System.out.println("Error input");
							continue;
						}
						break;
					}while(true);
					newQuiz = new AutomaticQuiz(numOfQuestions,questionDataBase);
					newQuiz.createExam(questionDataBase);
					quiz = newQuiz;
				} catch (Exception e) {
					System.err.println(e.getMessage());
					return newQuiz;
				}
			}
			compleateTest = true;
		}
		if (choose == -1)
			return newQuiz;
		System.out.println("***-------QUIZ EXPORT MENU-------***");
		System.out.println("-1) Back to main menu");
		System.out.println("1) Print all the questions to console");
		System.out.println("2) Print all the questions to txt file");
		System.out.println("***------------------------------***");
		switch (in.nextInt()) {
		case 1:
			System.out.println(quiz.toString());
			break;
		case 2:
			quiz.toTextFile();
			break;
		}
		return quiz;
	}
	public static void printList(ArrayList<?> list){
		ListIterator<?> listIterator = list.listIterator();
		while (listIterator.hasNext()){
			System.out.println((listIterator.nextIndex() + 1) + ")" + listIterator.next());
		}
	}
	public static void export(ArrayList<DataBase> data) throws FileNotFoundException, IOException {
		toFile = new FileToBinary(data);
		toFile.toExport();
//		ObjectOutputStream outQuestions = new ObjectOutputStream(new FileOutputStream("dataBase.dat"));
//		outQuestions.writeObject(data);
//		outQuestions.close();
	}

	public static void importData(ArrayList<DataBase> data) throws FileNotFoundException, ClassNotFoundException, IOException {
	toFile = new FileToBinary(data);
	toFile.toImport();
//		ObjectInputStream inData = new ObjectInputStream(new FileInputStream("dataBase.dat"));
//		data = (ArrayList<DataBase>) inData.readObject();
//		inData.close();
	}
}
