package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7569_토마토 {
	static int M;
	static int N;
	static int H;
	static int[][][] map;
	static int[] dh = {-1,1,0,0,0,0}; //앞뒤상하좌우
	static int[] dy = {0,0,-1,1,0,0};
	static int[] dx = {0,0,0,0,-1,1};
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		M = Integer.parseInt(st.nextToken()); //가로
		N = Integer.parseInt(st.nextToken()); //세로
		H = Integer.parseInt(st.nextToken()); //높이
		map = new int[H][N][M];
		for(int h = 0; h<H; h++) {
			for(int n=0; n<N; n++) {
				st = new StringTokenizer(in.readLine()," ");
				for(int m=0; m<M; m++) {
					map[h][n][m] = Integer.parseInt(st.nextToken());
				}
			}
		}
		answer = Integer.MAX_VALUE;
		bfs();
		System.out.println(answer);
//		//debug
//		for(int h = 0; h<H; h++) {
//			for(int n=0; n<N; n++) {
//				for(int m=0; m<M; m++) {
//					System.out.print(map[h][n][m]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
//		//end
	
	}
	static void bfs() {
		Queue<Point> queue = new LinkedList<Point>();
		int cnt = 0;
		for(int h=0; h<H; h++) {
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
					if(map[h][n][m]==1)
						queue.offer(new Point(h,n,m,cnt));
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Point temp = queue.poll();
			cnt = temp.cnt;
			for(int d=0; d<6; d++) {
				int nh = temp.h+dh[d];
				int ny = temp.y+dy[d];
				int nx = temp.x+dx[d];

				if(check(nh,ny,nx) && map[nh][ny][nx]==0) {
					map[nh][ny][nx] = 1;
					queue.offer(new Point(nh,ny,nx,cnt+1));
				}
			}
		}
		
		int count = 0;
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if(map[h][n][m] == 0)
						count++;
				}

			}
		}
		
		if(count==0) {
			answer = Math.min(answer, cnt);
		}
		else {
			answer = -1;
		}
	}
	
	static boolean check(int nh, int ny, int nx) {
		if(nh>=0 && nh<H && ny>=0 && ny<N && nx>=0 && nx<M)
			return true;
		return false;
	}
	
	static class Point{
		int h;
		int y;
		int x;
		int cnt;
		public Point(int h, int y, int x, int cnt) {
			this.h = h;
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	
		
	}

}
