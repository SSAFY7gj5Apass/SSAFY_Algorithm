package com.ssafy.wshw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2468_안전영역 {
	static int N,map[][];
	static boolean checked[][];
	
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int maxHeight = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>maxHeight)
					maxHeight = map[i][j];
			}
		}
		int max=0;
		
		for (int h = 0; h < maxHeight; h++) {
			checked = new boolean[N][N];
			int cnt=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!checked[i][j]&&map[i][j]>h) {
						cnt+=dfs(i,j,h);
					}
				}
			}
			max = Math.max(max, cnt);
		}
		System.out.println(max);
		
	}
	private static int dfs(int x, int y, int height) {
		checked[x][y]=true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0||nx>=N||ny<0||ny>=N) continue;
			if(checked[nx][ny]) continue;
			if(map[nx][ny]>height) {
				dfs(nx,ny,height);
			}
		}
		return 1;
	}

}
