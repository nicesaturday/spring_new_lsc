package com.kh.practice.student.view;

import com.kh.practice.student.controller.StudentController;
import com.kh.practice.student.model.vo.Student;

public class StudentMenu {
	
	
	StudentController ssm = new StudentController();
	
	
	public StudentMenu() {
		System.out.println("==========  학생  정보  출력  ==========");
		for(Student item : ssm.printStudent()) {
			System.out.printf("이름 : %s / 과목 : %s / 점수 : %d\n",item.getName(),item.getSubject(),item.getScore());
		}
		System.out.println("==========  학생  성적  출력  ==========");
		System.out.printf("학생 점수 합계 : %d\n" , (int)ssm.avgScore()[0]);
		System.out.printf("학생 점수 합계 : %.1f\n" , ssm.avgScore()[1]);
		System.out.println("==========  성적  결과  출력  ==========");
		
		for(Student item : ssm.printStudent()) {
			if(item.getScore() < ssm.CUT_LINE) {
				System.out.println(item.getName()+"학생은 재시험 대상입니다.");
			}
			else {
				System.out.println(item.getName()+"학생은 통과입니다.");
			}
		}
	}
	
}
