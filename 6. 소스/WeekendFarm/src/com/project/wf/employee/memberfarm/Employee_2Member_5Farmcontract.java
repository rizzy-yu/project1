package com.project.wf.employee.memberfarm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 직원, 관리자가 전체회원의 농장 계약서를 확인할 수 있는 클래스
 */
public class Employee_2Member_5Farmcontract {
	//직원] 2. 회원 농장 조회 - 2.5 회원 농장 계약서 조회
	
	private static Scanner scan;
	private static String DATA;
	private static String membernum;
	
	
	static {
		membernum = ""; //두 파일간 공유할 회원번호들
		scan = new Scanner(System.in);
		DATA = "dat\\1. MemberList.dat";
	}
	
	/**
	 * 직원, 관리자가 전체회원의 농장계약서를 확인 후, 원하는 회원의 계약서만 조회할 수 있게 하는 메소드
	 */
	public void Farmcontract() {
		
		boolean loop = true;
		while(loop) {
			
			
			list();
			System.out.println();
			
			// 뒤로가기 버튼
			System.out.println("1.회원 선택\t\t\t\t0.뒤로가기");
			System.out.print("번호 입력: ");
			String back = scan.nextLine();
			
			boolean loop2 = true;
			//조회할 회원 번호
			while(loop2) {
			
				if(back.equals("1")) {
					System.out.print("조회할 회원 번호: ");
					String membernum = scan.nextLine();
					contractcheck(membernum.toUpperCase());
					System.out.println("\"실행된 파일을 확인하세요.\"");
					
					System.out.println("1.회원 선택\t\t\t\t0.뒤로가기");
					System.out.print("번호 입력: ");
					back = scan.nextLine();
					
				} else if (back.equals("0")) {
					System.out.println("[종료]");
					loop2 = false;
				}
			}//while
			
			if(back.equals("0")) {
				loop = false;
			}
			
		}//while

	
	}
	
	/**
	 * 직원, 관리자가 전체회원의 농장 계약서 목록을 확인할 수 있는 메소드
	 */
	private void list() {
		//고객들 계약서 리스트 목록
			
			try {

				BufferedReader reader = new BufferedReader(new FileReader(DATA));
				
				String line = "";

				System.out.println("[회원 농장 계약서 목록]");
				System.out.println("[회원 번호]  [회원 이름]\t[회원 전화번호]\t   [회원 텃밭번호]\t[텃밭 대여 기간]\t\t[금액]");
				
				
					while ((line = reader.readLine()) != null) {//MemberList

						String[] temp = line.split("★");
						
										System.out.printf("  %s\t     %s\t%4s\t       %s\t   %s\t       %,d원\n"
											, temp[0]
											, temp[1]
											, temp[3]
											, temp[8]
											, temp[6] + " ~ " + temp[7]
											, Integer.parseInt(temp[10])
											); 		
									
								
								
								}
								reader.close();
								

			} catch (Exception e) {
				System.out.println(e);
			}

	}
	
	/**
	 * 직원, 관리자가 원하는 회원 번호를 입력하여 해당 회원의 농장계약서를 파일로 실행시키는 메소드
	 * @param membernum 조회할 회원 번호
	 */
	private void contractcheck(String membernum) {
		//해당 메개변수(membernum)에 해당하는 계약서가 뜨도록하기
		
					try {	//해당 파일 실행시키기
							Process p1 = new ProcessBuilder("notepad.exe"
									, "dat\\회원 농장 계약서\\"+membernum+".txt").start();
						} catch (IOException e) {
								System.out.println(e);
						}
					
	}//메소드
	

}
	
	
