package com.ssafy.wshw;

import java.io.*;
import java.util.*;

public class BJ_7569_토마토 {
	static int M,N,H,result;
	
	static int[][][] map;
	static Queue<Tomato> q;
	
	static int[] dx = {0,-1,0,1,0,0};// 좌상우하위아래
	static int[] dy = {-1,0,1,0,0,0};
	static int[] dz = {0,0,0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		map = new int[H][N][M];
		
		q = new LinkedList<Tomato>();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					map[i][j][k] = sc.nextInt();
					if(map[i][j][k] == 1 )
						q.add(new Tomato(i,j,k));
				}
			}
		}
		System.out.println(bfs());
			
	}
	public static int bfs() {
		while(!q.isEmpty()) {
			Tomato t = q.poll();
			int x = t.x;
			int y = t.y;
			int z = t.z;
			
			for (int i = 0; i < 6; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];
				
				if(nx>=0&&nx<N&&ny>=0&&ny<M&&nz>=0&&nz<H) {
					if(map[nz][nx][ny]==0) {
						q.add(new Tomato(nz,nx,ny));
						map[nz][nx][ny] = map[z][x][y] + 1;
					}
				}
			}
		}
		result = Integer.MIN_VALUE;
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if(map[i][j][k]==0) return -1; // 안익은게 있다면
					result = Math.max(result,map[i][j][k]);
				}
			}
		}
		
		if(result == 1) return 0; //모두익음
		else return result-1;
	}
	
	static class Tomato{
		int x,y,z;

		public Tomato(int z, int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		
	}
}
