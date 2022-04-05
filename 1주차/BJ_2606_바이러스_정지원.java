package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2606_바이러스_정지원 {
	static boolean[] visit;
	static int[][] arr;
	static int n, edge, cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		edge = Integer.parseInt(br.readLine());  
		visit = new boolean[n+1];
		arr = new int[n+1][n+1];
		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from][to] = arr[to][from] = 1;
		}
		cnt = 0;
		dfs(1);
		System.out.println(cnt);
	}
	
	public static void dfs(int start) {
		visit[start] = true;
		for (int i = 1; i <= n; i++) {
			if(arr[start][i] == 1 && visit[i] == false) {
				cnt++;
				dfs(i);
			}
		}
	}

}
