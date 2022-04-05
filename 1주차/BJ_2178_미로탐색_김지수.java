package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2178_미로탐색 {
	static int N;
	static int M;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };// 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		arr = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String sNum = in.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(sNum.charAt(j) + "");
			}
		}

		// Debug
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}

		//1. dfs
//		answer = Integer.MAX_VALUE;
//		visited[0][0] =true;
//		dfs(0, 0, 1, visited);
//		System.out.println(answer);

		//2. bfs
		answer = Integer.MAX_VALUE;
		visited[0][0] = true;
		bfs(0,0,1);
		System.out.println(answer);
	}

	//1.dfs : 시간초과 발생
	static void dfs(int x, int y, int cnt, boolean[][] visited) {
		if (y == N - 1 && x == M - 1) {
			answer = Math.min(answer, cnt);
			return;
		}
		if(cnt>answer) {
			return;
		}
		if (y + dy[0] >= 0 && arr[y + dy[0]][x] == 1 && visited[y + dy[0]][x] == false) {
			visited[y + dy[0]][x] = true;
			//System.out.println("상방향이동, cnt :" + cnt);
			dfs(x, y + dy[0], cnt + 1, visited);
			visited[y + dy[0]][x] = false;
		}
		if (y + dy[1] < N && arr[y + dy[1]][x] == 1 && visited[y + dy[1]][x] == false) {
			visited[y + dy[1]][x] = true;
			//System.out.println("하방향이동, cnt :" + cnt);
			dfs(x, y + dy[1], cnt + 1, visited);
			visited[y + dy[1]][x] = false;

		}
		if (x + dx[2] >= 0 && arr[y][x + dx[2]] == 1 && visited[y][x + dx[2]] == false) {
			visited[y][x + dx[2]] = true;
			//System.out.println("좌방향이동, cnt :" + cnt);
			dfs(x + dx[2], y, cnt + 1, visited);
			visited[y][x + dx[2]] = false;

		}
		if (x + dx[3] < M && arr[y][x + dx[3]] == 1 && visited[y][x + dx[3]] == false) {
			visited[y][x + dx[3]] = true;
			//System.out.println("우방향이동, cnt :" + cnt);
			dfs(x + dx[3], y, cnt + 1, visited);
			visited[y][x + dx[3]] = false;

		}
	}
	//2,bfs
	static void bfs(int x, int y, int cnt) {
		Queue<point> queue = new LinkedList<point>();
		queue.offer(new point(x, y,cnt));
		while(!queue.isEmpty()) {

			point temp = queue.poll();
			int tempX = temp.x; 
			int tempY = temp.y;
			int pCnt = temp.cnt;
			for(int i=0; i<4; i++) {
				int px = tempX+dx[i];
				int py = tempY+dy[i];
				if(px>=0 && px<M && py>=0 && py<N && visited[py][px]==false && arr[py][px]==1) {
					visited[py][px] = true;
					queue.offer(new point(px,py,pCnt+1));
				}

				if(px==M-1 && py ==N-1) {
					int count = temp.cnt;
					answer = Math.min(answer, count+1);
				}
			}

		}
	}

	
	public static class point {
		int x;
		int y;
		int cnt;
		public point(int x, int y,int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
	}
}
