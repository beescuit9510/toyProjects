package kh.java.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import kh.java.vo.Student;

public class StudentView {
	private Scanner sc;

	public StudentView() {
		super();
		sc = new Scanner(System.in);
	}

	public int showMainMenu() {
		System.out.println("\n============학생관리 프로그램v5=============\n");
		System.out.println("1. 학생 정보 등록");
		System.out.println("2. 학생 정보 출력(등록된 전체학생)");
		System.out.println("3. 학생 정보 출력(1명 이름으로 검색)");
		System.out.println("4. 학생 정보 수정");
		System.out.println("5. 학생 정보 삭제");
		System.out.println("0. 프로그램종료");
		System.out.printf("선택 > ");
		return sc.nextInt();

	}

	public StringTokenizer insertStudnet() {
		System.out.println("=========== register student info ============");
		return add();
	}

	public void printStudent(HashMap<String, Student> students) {
		System.out.println("=========== show all student info ============");
		System.out.println("name\tage\taddress");
		for(Student s : students.values()){
			System.out.println(s);
		}
	}
	
	public String gettStudentName() {
		System.out.println("=========== show student info ============");
	}
	
	public StringTokenizer updateStudent() {
		System.out.println("=========== update student info ============");
		return add();
	
		
	}
	
	public void printOnetStudent(Student s ) {
		System.out.println(s);

	}
	
	public StringTokenizer add() {
		sc.nextLine();
		System.out.println("enter student name/age/addr : ");
		return new StringTokenizer(sc.nextLine(), "/");
	}
	
	public getStudentName() {
		sc.nextLine();
		System.out.println("enter student name/age/addr : ");
		return new StringTokenizer(sc.nextLine(), "/");
	}

}