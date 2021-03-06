package com.project.wf.member.myfarm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.project.wf.login.mainclass;

/**
 * 회원이 자신의 텃밭 현황을 조회하는 클래스
 */
public class Member_1Myfarm_2Gardencheck {
	//회원] 1. 내 농장 조회 - 2. 텃밭 현황
	
	private static Scanner scan;
	private static String DATA;
	private static String mynum; //메인에서 가져오는 회원번호/직원번호
	
	static {

		mynum = mainclass.inputwho; // 입력받은 회원번호
		scan = new Scanner(System.in);
		DATA = "dat\\1. MemberList.dat";
	}
	
	/**
	 * 회원이 자신의 텃밭 현황을 확인 후, 전 페이지로 이동할 수 있는 메소드
	 */
	public void Myfarmfarmcheck() {
		
		boolean loop = true;
		while(loop) {
			
			list(mynum);
			System.out.println();
			
			// 뒤로가기 버튼
			System.out.println();
			System.out.println("0.뒤로가기");
			System.out.print("번호 입력: ");
			String back = scan.nextLine();
			
			if(back.equals("0")) {
				loop = false;
			}
			
		}
		
	}//메인메서드

	
	/**
	 * 회원이 자신의 텃밭 현황을 확인할 수 있는 메소드
	 * @param mynum 로그인시 입력된 회원번호
	 */
	private void list(String mynum) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			String line = "";
			
			System.out.println("[텃밭 현황 목록]");
			System.out.println("[텃밭 총 면적(㎡)]\t[텃밭 대여 시작일]\t[텃밭 대여 종료일]\t[텃밭 가격]");
				while((line = reader.readLine()) != null) {
				String[] temp = line.split("★");
				
				if(mynum.equals(temp[0])) {
					
					System.out.printf("      %s㎡\t\t%11s\t\t%11s\t\t%,d원"
							, temp[9]
							, temp[6]
							, temp[7]
							, Integer.parseInt(temp[10])
							);
					
					}
				}
				reader.close();	
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
