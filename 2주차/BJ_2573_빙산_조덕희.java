package com.ssafy.study;

import java.util.*;
import java.io.*;

import java.util.StringTokenizer;

public class BJ_2573_빙산{
	static int N,M,map[][],year=0;
	static boolean visited[][];
	
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int partition = 1;
		while(true) {
			partition = melt();
			if(partition>1) break;
			else if(partition == -1) {
				year = 0;
				break;
			}
			check();
			year++;
		}
		System.out.println(year);
		
	}
	private static void bfs(int x, int y) {
		Queue<Position> q = new LinkedList<Position>();
		q.offer(new Position(x,y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Position p = q.poll();
			int xx = p.x;
			int yy = p.y;
			
			int cnt=0;
			
			for (int i = 0; i < 4; i++) {
				int nx = xx + dx[i];
				int ny = yy + dy[i];
				
				if(nx<0||ny<0||nx>=N||ny>=M||visited[nx][ny]) continue;
				
				if(map[nx][ny]==0) cnt++;
				if(map[nx][ny]>0) {
					q.add(new Position(nx,ny));
					visited[nx][ny] = true;
				}
				
			}
			map[xx][yy] = map[xx][yy] - cnt > 0 ? map[xx][yy] - cnt : -1; 
			
		
		}
		
	}
	private static int melt() {
		visited = new boolean[N][M];
		int part = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] > 0) {
					part++;
					if(part>1) return part;
					bfs(i,j);
				}
			}
		}
		if(part == 0) return -1;
		else return part;
	}
	
	private static void check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==-1) map[i][j] = 0;
			}
		}
	}
	
	static class Position{
		int x,y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
}
