package kh.java.contoller;

import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;
import kh.java.view.StudentView;
import kh.java.vo.Student;

public class StudentController {
	private HashMap<String, Student> students;
	private StudentView view;

	public StudentController() {
		super();
		students = new HashMap<String, Student>();
		view = new StudentView();
	}

	public void main() {
		while (true) {
			switch (view.showMainMenu()) {
			case 1:
				insertStudent();
				break;
			case 2:
				printAllStudents();
				break;
			case 3:
				printStudent();
				break;
			case 4:
				updateStudent();
				break;
			case 5:
//				deleteStudent();
				break;
			case 0:
				System.out.println("");
				return;
			default:
				break;
			}
		}
	}

	private void insertStudent() {
		StringTokenizer token = view.insertStudnet();
		Student s = new Student(token.nextToken(), Integer.parseInt(token.nextToken()), token.nextToken());
		students.put(s.getName(), s);
	}

	private void printAllStudents() {
		view.printStudent(students);
	}

	private void printStudent() {
		String name = view.gettStudentName();
		if (students.containsKey(name)) {
			view.printOnetStudent(students.get(name));
		}else {
			System.out.println("No such a student.");
		}

	}
	private void updateStudent() {
		insertStudent();

	}
	
	

}
