package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_13458_시험감독_김주희 {
	
	static int[] people;

	public static void main(String[] args) throws IOException {
		// 총 N개의 시험장, 각각의 시험장마다 응시자
		// i번 시험장에 있는 응시자의 수는 Ai명
		// 감독관 - 총감독관, 부감독관
		// (1) 총감독관 : 한 시험장에서 감시할 수 있는 응시자의 수가 B명
		// (2) 부감독관: 한 시험장에서 감시할 수 있는 응시자의 수가 C명
		// 각각의 시험장에 총감독관은 오직 1명만 있어야하고, 부감독관은 여러명가능
		// 각 시험장마다 응시생들을 모두 감시해야할때, 필요한 감독관 수의 최솟값 구하기
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/input_BJ_13458_시험감독.txt"));

		String line;
		while((line = br.readLine()) != null){
		    int N = Integer.parseInt(line); //시험장 개수 N
		    people = new int[N];
		    
		    String[] sp = br.readLine().split(" ");
		    for(int i = 0; i < N; i++) {
		    	people[i] = Integer.parseInt(sp[i]); //각 시험장에 있는 응시자의 수
		    	//System.out.print(people[i] + " ");
		    }
		    
		    sp = br.readLine().split(" ");
		    int B = Integer.parseInt(sp[0]); //총감독관 감시 응시자수
		    int C = Integer.parseInt(sp[1]); //부감독관 감시 응시자수
		    //System.out.println("B: " + B + "C: " + C);
		    
		    
		    int result = 0;
		    
		    //각 시험장마다
		    for(int i = 0; i < N; i++) {
		    	int pNum = people[i];
		    	
		    	if(pNum == 0) {
		    		break;
		    	}
		    	
		    	//총감독관은 반드시 한명이 있어야함
		    	//총감독관 1명으로만 커버할 수 있으면 다음으로 넘어가기
		    	if(pNum <= B) {
		    		result++;
		    		continue;
		    	}
		    	//총감독관 1명 커버량보다 사람수가 많으면
		    	else {
		    		//총감독관 +1
		    		result++;
		    		//현재 시험장 인원 - 총감독관 커버인원
		    		pNum = pNum - B;		    		
		    		//System.out.println("pNum: " + pNum);
		    		
		    		//나머지 인원 부감독관 배치
		    		//잔여인원이 부감독관 커버인원보다 작으면 넘어가기
		    		if(pNum <= C) {
		    			result++;
		    			continue;		    			
		    		}else {
		    		
			    		int vice = pNum / C;
			    		float viceRest = pNum % C;
			    		//System.out.println("vice: " + vice);
			    		//System.out.println("viceRest: " + viceRest);
			    		if(viceRest != 0) {
			    			result = result + vice + 1;
			    		}else {
			    			result = result + vice;
			    		}
		    	    }
		    	}
		    	
		    }//for
		    
		    System.out.println(result);
		    //System.out.println("===========================");
		}//while
			
	}//main

}
