package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14503_로봇청소기 {
	static int N; // 세로크기
	static int M; // 가로크기
	static int r; // 로봇청소기의 y좌표
	static int c; // 로봇청소기의 x좌표
	static int d; // 로봇청소기가 바라보는 방향(0 : 북쪽, 1 : 동쪽, 2 : 남쪽, 3 : 서쪽)
	static int[][] map;
	static int dy[] = { -1, 0, 1, 0 }; // 상우하좌
	static int dx[] = { 0, 1, 0, -1 };
	static int answer; //로봇청소기가 청소하는 칸의 갯수
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;
		bfs(r,c,d);
		System.out.println(answer);
	}

	static void bfs(int y, int x, int dir) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(y, x, dir));
		boolean[][] visited = new boolean[N][M];
		visited[y][x] = true;
		answer++; 
		outer : while (!queue.isEmpty()) {
			Point temp = queue.poll();
			int nDir = temp.dir;
			for (int d = 3; d>=0 ; d--) {
				int ny = temp.y + dy[(d + nDir) % 4];
				int nx = temp.x + dx[(d + nDir) % 4];
				if(check(ny, nx)&& map[ny][nx]!=1 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					answer++;
					queue.offer(new Point(ny, nx, (d+nDir)%4));
					continue outer;
				}
			}
			int ny = temp.y+dy[(nDir+2)%4];
			int nx = temp.x+dx[(nDir+2)%4];
			if(check(ny, nx) && map[ny][nx]!=1) {
				queue.offer(new Point(ny, nx, nDir));
				continue outer;
			}
		}
	}

	static boolean check(int ny, int nx) {
		if (ny > 0 && ny < N-1 && nx > 0 && nx < M-1)
			return true;
		return false;
	}

	static class Point {
		int y;
		int x;
		int dir;

		public Point(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}

}
