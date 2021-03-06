package com.project.wf.employee.memberfarm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 직원, 관리자가 전체회원의 텃밭 현황을 조회하는 클래스
 */
public class Employee_2Member_1Gardencheck {
	//직원] 2. 회원 농장 조회 - 2.1 회원 텃밭 현황
	
			private static Scanner scan;
			private static String DATA;
			private static String DATA1;
			private static String membernum;

			static {
				membernum = ""; //두 파일간 공유할 회원번호들
				scan = new Scanner(System.in);
				DATA = "dat\\1. MemberList.dat";
				DATA1 = "dat\\3. PlanListDummy.dat";
			}
			
			/**
			 * 직원, 관리자가 전체회원의 텃밭 현황을 확인한 후, 원하는 회원의 텃밭 현황만 조회할 수 있게 하는 메소드
			 */
			public void Memberplantcheck() {
				
				try {

					BufferedReader reader = new BufferedReader(new FileReader(DATA)); //회원번호파일
					
					String line = "", line1 = "";
					String result = "";

					System.out.println("[회원 텃밭 현황 목록]");
					System.out.println("[회원 번호]\t[이름]\t\t[전화번호]\t[텃밭 면적]\t[텃밭 대여 가격]\t\t[텃밭 대여 기간]     \t\t[재배중인 농작물]\t\t[재배 농작물 종류 수량]");
					
						while ((line = reader.readLine()) != null) {//회원번호

							String[] temp = line.split("★");
							membernum = temp[0]; 					//temp[0]은 회원번호, membernum은 매개
								
								int plantcount = 0; //재배종류 누적변수
								String str = "";//누적변수				//농작물명들 받을 누적변수
							
								
								BufferedReader reader1 = new BufferedReader(new FileReader(DATA1)); //재배중인 농작물 파일(PlanListDummy)
								while ((line1 = reader1.readLine()) != null) {  
									
									
									String[] temp1 = line1.split("★"); 
									
									if (membernum.equals(temp1[4])) { //같은 회원번호 찾기
											
											plantcount++;				//농작물갯수 누적변수에 쌓기
											str += temp1[2] + ",";		//농작물이름 누적변수에 쌓기
									}
									
								} 
								reader1.close();
								result = String.format("    %s\t%s\t      %s\t  %s㎡  \t\t%,9d원      \t%s\t\t%s\t\t%10d개\n"
												, temp[0]
												, temp[1]
												, temp[3]
												, temp[9]
												, Integer.parseInt(temp[10])
												, temp[6] + " ~ " + temp[7]
												, str
														//, str.substring(0, str.length()-1) + ".." //농작물 종류 	 		
												, plantcount //재배 종류 수량
												);
								
								System.out.println(result);
					}
					reader.close();
					

				} catch (Exception e) {
					System.out.println(e);
				}

				
				// 뒤로가기 버튼
				System.out.println("1. 회원선택\t\t\t\t0.뒤로가기");
				System.out.print("번호 입력: ");
				String num = scan.nextLine();
				
				boolean loop = true;
				//조회할 회원 번호
				while(loop) {
					
					if(num.equals("1")) {
						
						System.out.print("조회할 회원 번호: ");
						String member = scan.nextLine();
						
						list(member.toUpperCase()); 
						
						System.out.println("1. 회원선택\t\t\t\t0.뒤로가기");
						System.out.print("번호 입력: ");
						num = scan.nextLine();
						
						
					} else if (num.equals("0")) {
						loop = false;
					}
					
				}
				
			}//메소드
					
				/**
				 * 직원, 관리자가 원하는 회원의 번호를 입력하여 해당 회원의 텃밭 현황만을 확인할 수 있는 메소드
				 * @param member 조회할 회원 번호
				 */
				private static void list(String member) {
				
					try {

						BufferedReader reader = new BufferedReader(new FileReader(DATA));
						
						String line = "", line1 = "";
						
						System.out.println("[회원 텃밭 현황 목록]");
						System.out.println("[회원 번호]  [이름]\t\t[전화번호]\t\t[텃밭 면적]\t[텃밭 대여 가격]\t\t[텃밭 대여 기간]\t\t[재배중인 농작물 이름]\t[각 재배 농작물 수량]");
						
							while ((line = reader.readLine()) != null) {//회원목록

								String[] temp = line.split("★");
								membernum = temp[0];
								
								if(member.equals(membernum)) {
								
									BufferedReader reader1 = new BufferedReader(new FileReader(DATA1));
									while ((line1 = reader1.readLine()) != null) { //PlanListDummy
										
										String[] temp1 = line1.split("★"); 
										
										if (membernum.equals(temp1[4])) {
											
												System.out.printf("  %s\t   %s\t      %s\t  %4s㎡\t\t%,9d원\t      %s\t\t%1s\t\t\t%d개\n"
													, temp[0]
													, temp[1]
													, temp[3]
													, temp[9]
													, Integer.parseInt(temp[10])
													, temp[6] + " ~ " + temp[7]
													, temp1[2] //재배중 농작물 	 		
													, Integer.parseInt(temp1[5]) //재배 수량
													); 		
											
										}
										
									} 
									reader1.close();
							}
								
						}
						reader.close();
						

					} catch (Exception e) {
						System.out.println(e);
					}
	
				}
}
