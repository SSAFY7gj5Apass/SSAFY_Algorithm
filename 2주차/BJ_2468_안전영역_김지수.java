package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468_안전영역 {
	static int N;
	static int[][] map;
	static int[][] newMap;
	static int[] dy = {-1,1,0,0	};//상하좌우
	static int[] dx = {0,0,-1,1};
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = Integer.MIN_VALUE;
		int water = -1; //물에 잠긴 영역 수 체크하는 변수
		int count = 0; //물의 높이
		while(water != 0) {
			newMap = new int[N][N];
			water = 0;
			int cnt = 0; // 영역의 갯수 체크 변수
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					//잠긴지 않은 경우(안전한 영역)
					if(map[i][j]>count) {
						newMap[i][j] = -1;
						water++;
					}
				}
			}

			//잠기지 않았을경우 영역을 체크하는 bfs
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(newMap[i][j]== -1) {
						cnt++;
						bfs(i,j,cnt);
					}
				}
			}
			answer = Math.max(answer, cnt);
			count++;
		}
		System.out.println(answer);
	}
	//
	//구역이 이어져있는지 확인하는 BFS
	static void bfs(int y ,int x, int cnt) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(y, x));
		newMap[y][x] = cnt;

		while(!queue.isEmpty()) {
			Point temp = queue.poll();
			for(int d=0; d<4; d++) {
				int ny = temp.y+dy[d];
				int nx = temp.x+dx[d];
				if(check(ny,nx)  && newMap[ny][nx] == -1) {
					queue.offer(new Point(ny,nx));
					newMap[ny][nx] = cnt;
				}
			}
		}
		
		
	}
	
	static boolean check(int ny, int nx) {
		if(ny>=0 && ny<N && nx>=0 && nx<N)
			return true;
		return false;
	}
	static class Point{
		int y;
		int x;
		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

}
