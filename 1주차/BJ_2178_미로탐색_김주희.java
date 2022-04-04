package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2178_미로탐색_김주희 {
	
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
	static int[][] maze;
	static boolean[][] visited;
    static int N, M;
	
	public static void main(String[] args) throws IOException {
		// 04-04
        // NxM 크기 미로
		// 1은 이동가능하지만 0은 이동불가
		// (1,1)에서 출발하여 (N,M)의 위치로 이동할 때 지나야하는 최소 칸수 구하기 
		// 시작위치, 도착위치 포함
		// BFS
		
		//BufferedReader br = new BufferedReader(new FileReader("input_BJ_2178_미로탐색.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		while((line = br.readLine()) != null) {
			String[] sp = line.split(" ");
			N = Integer.parseInt(sp[0]);
			M = Integer.parseInt(sp[1]);
			maze = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i = 0; i < N; i++) {
				sp = br.readLine().split("");
				for(int j = 0; j < M; j++) {
					maze[i][j] = Integer.parseInt(sp[j]);
					//System.out.print(maze[i][j] + " ");
					
				}
				//System.out.println();				
			}
			
			BFS(0,0); //시작위치
			
            System.out.println(maze[N-1][M-1]);
		}//while
		
	}//main
	
	public static void BFS(int row, int col) {
		
		Queue<Location> queue = new LinkedList<>();
		queue.add(new Location(row, col));
		
		while(!queue.isEmpty()) {
			Location curLoc = queue.poll();
			int curR = curLoc.row;
			int curC = curLoc.col;
			
			//4방탐색
			for(int i = 0; i < 4; i++) {
				int nextR = curR + dirR[i];
				int nextC = curC + dirC[i];
				
				//범위를 벗어나면 지나치기
				if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
					continue;
				}else {
					//방문한 적 있거나 갈 수 없는길(0)이면 지나치기
					if(visited[nextR][nextC] || maze[nextR][nextC] == 0) {
						continue;
					}
				}
				
				//위를 모두 통과했으면 이동 가능
				queue.add(new Location(nextR, nextC));
				//이동할 좌표의 값에 이동횟수 +1 더하기, 방문체크
				maze[nextR][nextC] = maze[curR][curC] + 1;
				visited[nextR][nextC] = true;
				

			}//for

		}//while

	}

}
