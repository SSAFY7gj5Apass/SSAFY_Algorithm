package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BJ_2178_미로탐색_정지원 {
	static int[][] dir = {{-1, 0},{1,0}, {0,-1},{0,1}}; //상하좌우
	static int[][] arr;
	static boolean[][] visit;
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i <N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j) -'0';
			}
		}
		bfs();
		System.out.println(arr[N-1][M-1]);
	}
	
	public static void bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		q.offer(new Pos(0,0));
		visit[0][0] = true;
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			for (int i = 0; i < 4; i++) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];
				if(arrayArea(nr, nc)) {
					if(arr[nr][nc] ==1 && !visit[nr][nc]) {
						q.offer(new Pos(nr,nc));
						visit[nr][nc] = true;
						arr[nr][nc] = arr[r][c] + 1;
					}
				}
			}
		}
	}
	public static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}	
	}
	public static boolean arrayArea(int r, int c) {
		return r >=0 && r < N && c>=0 && c<M;
	}
}
