package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_5014_스타트링크_김주희 {
	
	static int F, S, G, U, D;
	static int[] check;

	public static void main(String[] args) throws IOException {
		// 총 F층으로 이루어진 건물, 스타트링크는 G층, 강호가 있는 위치는 S층
		// 엘리베이터에는 버튼이 2개 (U: 위로 U층을 가는 버튼, D: 아래로 D층을 가는 버튼) 
		// 강호가 G층에 도착하려면, 버튼을 적어도 몇번 눌러야하는지 구하기
		// 단, 엘리베이터를 이용해서 G층에 갈 수 없다면 "use the stairs" 출력
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/input_BJ_5014_스타트링크.txt"));

		String line;
		while((line = br.readLine()) != null) {
			String[] sp = line.split(" ");
			F = Integer.parseInt(sp[0]); //총 층수 1~F층
			S = Integer.parseInt(sp[1]); //강호가 있는 위치
			G = Integer.parseInt(sp[2]); //목적지 층
			U = Integer.parseInt(sp[3]); //위로 U층
			D = Integer.parseInt(sp[4]); //아래로 D층
			
			check = new int[F+1];
			
			int result = BFS();
			if(result == -1) {
				System.out.println("use the stairs");	
			}else {
				System.out.println(result);
			}
		}//while
	}
	
	private static int BFS() {
		
		Queue<Integer> locQ = new LinkedList<Integer>();
		locQ.add(S);
		check[S] = 1;
		
		while(!locQ.isEmpty()) {
			
			int current = locQ.poll();
			int next = 0;
			
			if(current == G) {
				return check[current] - 1;
			}
			
			for(int i = 0; i < 2; i++) {
				if(i == 0) { //U만큼 올라가거나
					next = current + U;					
				}else if(i == 1) { //D만큼 내려가기
					next = current - D;					
				}
				
				if(next < 1 || next > F || check[next] != 0) { //next<0.....
					continue;
				}
				
				locQ.add(next);
				check[next] = check[current] + 1;
			}
			
			
		}//while
		
		return -1; //위에서 return에 걸린것이 없으면 도착할 수 없으므로 -1
		
		
	}

}
