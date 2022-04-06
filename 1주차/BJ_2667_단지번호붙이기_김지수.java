package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class BJ_2667_단지번호붙이기 {
	static int N;
	static int[][] map;
	static int[] dy = {-1,1,0,0}; // 상하좌우
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String s = in.readLine();
			for(int j=0; j<N ;j++) {
				map[i][j] = Integer.parseInt(s.charAt(j)+"");
			}
		}
		boolean[][] visited = new boolean[N][N];
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1) {
					cnt++;
					dfs(i,j,cnt,visited);
				}
			}
		}
		System.out.println(cnt);
		int[] sum = new int[cnt];
		for(int count=0; count<cnt; count++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N;j++) {
					if(map[i][j]==count+2) {
						sum[count]++;
					}
				}
			}
		}
		Arrays.sort(sum);
		for(int i=0; i<sum.length; i++) {
			System.out.println(sum[i]);
		}
	}
	//dfs
	static void dfs(int y, int x, int cnt, boolean[][] visited) {
		visited[y][x] = true;
		map[y][x] = cnt+1;
		for(int d=0; d<4; d++) {
			int ny = y+dy[d];
			int nx = x+dx[d];
			if(ny>=0 && ny<N && nx>=0 && nx<N) {
				if(visited[ny][nx]==false && map[ny][nx]==1) {
					dfs(ny, nx, cnt, visited);
				}
			}
		}
	}
}
