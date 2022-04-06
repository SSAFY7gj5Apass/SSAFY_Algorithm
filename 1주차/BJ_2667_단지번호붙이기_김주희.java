package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_2667_단지번호붙이기_김주희 {
	
//	static class Location{
//		private int row;
//	    private int col;
//	    
//	    public Location (int row, int col){
//	    	this.row = row;
//	    	this.col = col;
//	    }
//	} //for BFS
	
	static int[][] map;
	static int[] dirR = {-1, 1, 0, 0};
	static int[] dirC = {0, 0, -1, 1};
	static int N, count;
	//static int complex; //단지 개수
	static ArrayList<Integer> houses; //단지 내 집 개수

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 정사각형 지도에 1은 집, 0은 집이 없는곳
		// 단지는 연결된 집들의 모임이고, 번호가 부여된다.
		// 대각선이 아닌 상하좌우로만 연결된다.
		// 단지수 출력, 각 단지에 포함된 집의 수를 오름차순으로 정렬해서 출력
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input_BJ_2667_단지번호붙이기.txt"));
        
		N = Integer.parseInt(br.readLine()); //지도의 크기 N
		map = new int[N][N];
		houses = new ArrayList<Integer>();
		
		for(int i = 0; i < N; i++) {
			String[] sp = br.readLine().split("");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(sp[j]);
				//System.out.print(map[i][j] + " ");
			}
			//System.out.println();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				//집 발견 시 이 집을 기준으로 탐색 시작
				if(map[i][j] == 1) {				
					count = 0;
					DFS(i, j);
					houses.add(count);
				}
			}
		}
			
		System.out.println(houses.size());
		for(int i = 0; i < houses.size(); i++) {
			System.out.println(houses.get(i));
		}
		
	}//main
	
	public static void DFS(int row, int col) {
		//탐색 위치 0으로 만들기
		map[row][col] = 0;
		count++;
		
		for(int k = 0; k < 4; k++) {
			//범위를 벗어나면 지나치기
			if(row + dirR[k] < 0 || row + dirR[k] >= N || col + dirC[k] < 0 || col + dirC[k] >= N) {
				continue;
			}
			//탐색 지점이 1이면, 탐색 지점으로 이동하여 DFS
			if(map[row + dirR[k]][col + dirC[k]] == 1) {
				DFS(row + dirR[k], col + dirC[k]);
			}
		}

	}
}
