package com.ssafy.wshw;

import java.io.*;
import java.util.*;

public class BJ_2667_단지번호붙이기 {
	static int N,map[][];
	static boolean[][] visited;
	static ArrayList<Integer> al;
	
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		al = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j)-'0';
				if(map[i][j]==0) {
					visited[i][j]=true;
				}
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j])
					bfs(i,j);
			}
		}
		
		Collections.sort(al);//오름차수 정렬
		System.out.println(al.size());// 총 단지수 출력
		for(int i: al) {
			System.out.println(i); // 단지 내 집수 출력
		}
		
	}
	
	public static void bfs(int x, int y) {
		Queue<Position> q = new LinkedList<>();
		q.add(new Position(x,y));
		visited[x][y] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			Position p = q.poll();
			cnt++;
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Position(nx,ny));
				}
			}
		}
		al.add(cnt);
	}
	
	static class Position{
		int x,y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}

}
