package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2644_촌수계산_김주희 {
	
	static int N, a, b, rel, result;
	static int[][] relation;
	static int[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 부모-자식 관계는 1촌  => 나-할아버지는 2촌  / 나-아버지형제 3촌
		// 여러 사람들에 대한 부모 자식들간의 관계가 주어졌을 때, 
		// 주어진 두 사람의 촌수를 계산
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/input_BJ_2644_촌수계산.txt"));

		String line;
		while((line = br.readLine()) != null) {
			
			N = Integer.parseInt(line); //사람의 수 N
			String[] sp = br.readLine().split(" ");
			a = Integer.parseInt(sp[0]); //비교할 사람1 번호
			b = Integer.parseInt(sp[1]); //비교할 사람2 번호
			
			rel = Integer.parseInt(br.readLine()); //부모 자식들 간의 관계 개수
			
			relation = new int[N+1][N+1];
			check = new int[N+1];

			for(int i = 0; i < rel; i++) {
				sp = br.readLine().split(" ");
				//(p c) p = c의 부모번호
				int p = Integer.parseInt(sp[0]); //parent
				int c = Integer.parseInt(sp[1]); //child
				
				relation[p][c] = 1;
				relation[c][p] = 1;
			}
			
			BFS(a);
			
			// 출력: 입력에서 요구한 두 사람의 촌수를 나타내는 정수
			// 두 사람의 친척 관계가 없어 촌수 계산 불가할 때는 -1 출력
			if(check[b] == 0) {
				System.out.println("-1");
			}else {
				System.out.println(check[b]);
			}
		}//while
	}
	
	private static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);  
		
		while(!queue.isEmpty()) {
			int person = queue.poll();
			
			for(int i = 1; i < relation.length; i++) {
				//i번째 사람과의 관계가 존재하고, 촌수 정보가 없으면
				if(check[i] == 0 && relation[person][i] == 1) {
					queue.add(i); //다음 탐색할 사람으로 i번째 사람 지정
					check[i] = check[person] + 1; //check배열에 촌수 정보 업데이트
				}
			}		
		}

	}
}
