package com.ssafy.hw;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2644_촌수계산_정지원 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		int arr[][] = new int[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st= new StringTokenizer(br.readLine());
			int pr = Integer.parseInt(st.nextToken()); //부모
			int ch = Integer.parseInt(st.nextToken()); //자식
			arr[pr][ch] = arr[ch][pr] = 1;
		}
		boolean[] visit = new boolean[N+1];
		
		Queue<Fa> q = new LinkedList<>();
		int cnt = 0;
		q.add(new Fa(a, cnt));
		visit[a] = true;
		while(!q.isEmpty()) {
			Fa cur = q.poll();
			int w = cur.w;
			int num = cur.num;
			for (int i = 1; i <= N; i++) {
				if(arr[num][i] == 1 && !visit[i]) {
					arr[i][i] = w+1;
					visit[i] = true;
					q.add(new Fa(i, w+1));
				}
			}
		}
		if(arr[b][b] == 0) {
			System.out.println(-1);
		}else {
			System.out.println(arr[b][b]);
		}
		
		
	}
	public static class Fa {
		int num, w;

		public Fa(int num, int w) {
			super();
			this.num = num;
			this.w = w;
		}
		
	}

}
