package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2468_안전영역_김주희 {
	
	static class Location{
		private int row;
		private int col;
		
		public Location(int row, int col) {
			this.row = row; 
			this.col = col;
		}
	}
	
	static int[][] region;
	static int[][] temp;
	static boolean[][] visited;
	static int N, max, top;
	static int[] dirR = {-1, 1, 0, 0};
	static int[] dirC = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		// 장마철 대비 
		// 지역의 높이 정보 파악 -> 그 지역에 비가 내렸을 때 물에 잠기지 않는 안전한 영역이 최대 몇개인지 조사
		// ** 비의 양에 따라 일정 높이 이하의 모든 지점은 물에 잠김
		// 안전한 영역은 물에 잠기지 않는 지점들이 상하좌우로 인접해있고, 그 크기가 최대인 영역
		// 높이 정보가 주어졌을 떄, 물에 잠기지 않는 안전한 영역의 최대 개수 계산
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/input_BJ_2468_안전영역.txt"));
		
		String line;
		while((line = br.readLine()) != null) {
			N = Integer.parseInt(line); //NxN
			region = new int[N][N];
			temp = new int[N][N];
			visited = new boolean[N][N];
			max = 1;
			top = 100;
			
			for(int i = 0; i < N; i++) {
				String[] sp = br.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					region[i][j] = Integer.parseInt(sp[j]);
					//top = Integer.max(top, region[i][j]);
					//System.out.print(region[i][j] + " ");
				}
				//System.out.println();
			}
			
			for(int t = 1; t <= top; t++) {
				//temp, visited 초기화
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						temp[i][j] = 0;
					}
				}
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						visited[i][j] = false;
					}
				}
				
				copyArray(t);
				
//				System.out.println("after copy");
//				for(int i = 0; i < N; i++) {
//					for(int j = 0; j < N; j++) {
//						System.out.print(temp[i][j] + " ");
//					}
//					System.out.println();
//				}	
				
				int count = 0;
				//1~top까지 돌면서 각 강수량별 맵 정보 받기
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(!visited[i][j] && temp[i][j] != 0) {
							 DFS(i, j);
							 count++;
						}
						//System.out.print(temp[i][j] + " ");
					}
				   // System.out.println();
				}
				max = Integer.max(max, count);
				
			}//for
			
			System.out.println(max);
		}//while

	}
	
	private static void DFS(int row, int col) {
		
		visited[row][col] = true;
		
		//4방 탐색
		for(int i = 0; i < 4; i++) {
			int nextR = row + dirR[i];
			int nextC = col + dirC[i];
			
			if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) {
				continue;
			}
			
			if(!visited[nextR][nextC] && temp[nextR][nextC] != 0) {
				DFS(nextR, nextC);
			}
		}
		
	}
	
	private static void copyArray(int height) {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(region[i][j] > height) { //침수되지 않는 높이
					//System.out.println("passed");
					temp[i][j] = region[i][j];
				}
				//System.out.print(temp[i][j] + " ");
			}
			//System.out.println();
		}
	}

}
