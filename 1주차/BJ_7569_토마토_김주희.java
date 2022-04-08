package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_7569_토마토_김주희 {
	
	static int M, N, H, cnt;
	static int[][][] tomatoes;
	static boolean[][][] visited;

	static int[] dirR = {-1, 1, 0, 0, 0, 0};
	static int[] dirC = {0, 0, -1, 1, 0, 0};
	static int[] dirZ = {0, 0, 0, 0, -1, 1};
	
	static class Location{
		private int row, col, verti, count;
		
		public Location(int row, int col, int verti, int count) {
			this.row = row;
			this.col = col;
			this.verti = verti;
			this.count = count;
		}
	}
	
	static Queue<Location> locQ;

	public static void main(String[] args) throws IOException {
		// 토마토는 격자모양의 상자들을 층층이 쌓아 보관한다.
		// 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게된다. 
		// 하나의 토마토에 인접한 곳은 위, 아래, 오른쪽, 왼쪽, 앞, 뒤 여섯방향
		// 단, 상자의 일부칸에는 토마토 안들어있을 수도 있음
		// 토마토가 전부 다 익는 최소 일수 출력
		// BFS		
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input/input_BJ_7569_토마토.txt"));
        
		String line;
		while((line = br.readLine()) != null) {
			String[] sp = line.split(" ");
			
			M = Integer.parseInt(sp[0]); //가로(col)
			N = Integer.parseInt(sp[1]); //세로(row)
			H = Integer.parseInt(sp[2]); //쌓아올려지는 상자의 수
			
			tomatoes = new int[H][N][M];
			visited = new boolean[H][N][M];
			locQ = new LinkedList<Location>();
			//int ripe = 0;
			int unripe = 0;
			cnt = 0;
			
			for(int k = 0; k < H; k++) {
				for(int i = 0; i < N; i++) {
					sp = br.readLine().split(" ");
					for(int j = 0; j < M; j++) {
						tomatoes[k][i][j] = Integer.parseInt(sp[j]);
					    //System.out.print(tomatoes[k][i][j] + " ");
						//현재 위치가 익은 토마토이면 위치 저장, 방문체크
						if(tomatoes[k][i][j] == 1) {
							locQ.add(new Location(i, j , k, 0));
							visited[k][i][j] = true;
						}
						//현재 위치가 안익은 토마토이면 방문체크만
						else if(tomatoes[k][i][j] == -1) {
							visited[k][i][j] = true;
							unripe++;
						}else if(tomatoes[k][i][j] == 0) {
							unripe++;
						}
					}
					//System.out.println();
				}
			}//for
			
			//처음부터 다 익어있는 상황이면 1출력
			if(unripe == 0) {
				System.out.println(1);
			}else {
				BFS();
			}
			
		}//while
	}//main
	
	private static void BFS() {
		while(!locQ.isEmpty()) {
			Location loc = locQ.poll();
			cnt = Integer.max(cnt, loc.count);
			
			for(int i = 0; i < 6; i++) {
				int r = loc.row + dirR[i];
				int c = loc.col + dirC[i];
				int v = loc.verti + dirZ[i];
				
				if(r >= 0 && r < N && c >= 0 && c < M && v >= 0 && v < H && !visited[v][r][c]) {
					locQ.add(new Location(r, c, v, loc.count + 1));
					visited[v][r][c] = true;
				}	
			}
		}
		
		//토마토가 모두 익지는 못하는 상황이면 -1을 출력
		for(int k = 0; k < H; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if (!visited[k][i][j]) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		System.out.println(cnt);
		
	}//BFS()

}
