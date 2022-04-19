package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2573_빙산_김주희 {
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	
	static class Location{
		private int row;
		private int col;
		
		public Location(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int[] dirR = {-1, 1, 0, 0};
	static int[] dirC = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		// 2차원 배열에 빙산의 양의 정수로 높이 정보 주어짐. 그 외 바다는 0
		// 바닷물에 더 많이 접해있을 수록 빨리 줄어듦(동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼)
		// 한 덩어리의 빙산이 주어질때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하기
		// 끝까지 분리되지 않으면 0 출력
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/input_BJ_2573_빙산.txt"));

		String[] sp = br.readLine().split(" ");
		
		N = Integer.parseInt(sp[0]);
		M = Integer.parseInt(sp[1]);
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			sp = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(sp[j]);
			}
		}
		
		int result = 0;
		int iceberg = 0;
		
		while(true) {
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] != 0 && visited[i][j] == false) {
						BFS(i, j);
						iceberg++;
					}
				}
			}
			if(iceberg >= 2) {
				break;
			}
			if(iceberg == 0) {
				result = 0;
				break;
			}
			
			visited = new boolean[N][M];
			result++;
			iceberg = 0;
			
		}
		
		System.out.println(result);

	}//main
    
	private static void BFS(int row, int col) {
		
		Queue<Location> locQ = new LinkedList<Location>();
        locQ.add(new Location(row, col));
        visited[row][col] = true;
        
        while(!locQ.isEmpty()) {
        	Location next = locQ.poll();
        	int R = next.row;
        	int C = next.col;
        	
        	for(int i = 0; i < 4; i++) {
        		int nextR = R + dirR[i];
        		int nextC = C + dirC[i];
        
	        	if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || visited[nextR][nextC] == true) {
	        		continue; 
	        	}
	        	
	        	//바닷물이면
	        	if(map[nextR][nextC] == 0) {
	        		if(map[R][C] == 0) {
	        			
	        		}else {
	        			map[R][C] = map[R][C] - 1;
	        		}
	        	//빙산이면
	        	}else {
	        		visited[nextR][nextC] = true;
	        		locQ.add(new Location(nextR, nextC));
	        	}
        	}
        }//while
	}// BFS
}
