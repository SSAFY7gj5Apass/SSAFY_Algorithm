package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2667_단지번호붙이기_정지원 {
	static int[][] dir = {{-1, 0},{1,0}, {0,-1}, {0,1}};
	static boolean visit[][];
	static int[][] arr;
	static int n;
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String s= br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		bfs();
		System.out.println(cnt);
		int[] result = new int[cnt+1];
		for (int i = 1; i <=cnt ; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if(arr[j][k] == i) {
						result[i]++;
					}
				}
			}
		}
		Arrays.sort(result);
		for (int i = 1; i <= cnt; i++) {
			System.out.println(result[i]);
		}
		
	}
	public static void bfs() {
		cnt = 0;
		Queue<Pos> q = new LinkedList<Pos>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visit[i][j] && arr[i][j] > 0) {
					cnt++;
					q.add(new Pos(i,j));
					arr[i][j] = cnt;
					visit[i][j] = true;
					while(!q.isEmpty()) {
						Pos cur = q.poll();
						int r = cur.r;
						int c = cur.c;
						for (int d = 0; d < 4; d++) {
							int nr = r + dir[d][0];
							int nc = c + dir[d][1];
							if(arrayArea(nr, nc) && !visit[nr][nc] && arr[nr][nc] == 1) {
								q.add(new Pos(nr, nc));
								arr[nr][nc] = cnt;
								visit[nr][nc] = true;
							}
						}
					}
				}
			}
		}
	}
	public static boolean arrayArea(int r, int c) {
		return r >= 0 && r < n && c>=0 && c<n;
	}
	public static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
}
