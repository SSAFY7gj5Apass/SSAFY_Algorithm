package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7569_토마토_정지원 {
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0,-1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	static Queue<Tomato> q;
	static int m,n,h;
	static int arr[][][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		arr= new int [h][n][m];
		q = new LinkedList<Tomato>();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < m; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
					if(arr[i][j][k] == 1) {
						q.add(new Tomato(i, j, k));
					}
				}
			}
		}
		System.out.println(bfs());
	}
	public static int bfs() {
		while(!q.isEmpty()) {
			Tomato cur = q.poll();
			int z = cur.z;
			int x = cur.x;
			int y = cur.y;
			for (int i = 0; i < 6; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];
				if(arrayArea(nx, ny, nz)) {
					if(arr[nz][nx][ny] == 0) {
						q.add(new Tomato(nz, nx, ny));
						arr[nz][nx][ny] = arr[z][x][y] + 1;
					}
				}
			}
		}
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if(arr[i][j][k] == 0) return -1;
					result = Math.max(result, arr[i][j][k]);
				}
			}
		}
		if(result == 1) {
			return 0;
		} else {
			return result-1;
		}
	}
	public static boolean arrayArea(int x, int y, int z) {
		return x>=0 && x<n && y>=0 && y<m && z>=0 && z<h;
	}
	public static class Tomato{
		int z,x,y;

		public Tomato(int z, int x, int y) {
			super();
			this.z = z;
			this.x = x;
			this.y = y;
			
		}
		
	}
}
