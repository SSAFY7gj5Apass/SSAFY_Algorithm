package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5014_스타트링크 {
	static int F;
	static int S;
	static int G;
	static int U;
	static int D;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		F = Integer.parseInt(st.nextToken());//전체 층수
		S = Integer.parseInt(st.nextToken());//현재 층수
		G = Integer.parseInt(st.nextToken());//목표 층수
		U = Integer.parseInt(st.nextToken());//올라가는 버튼
		D = Integer.parseInt(st.nextToken());//내려가는 버튼
		visited = new int[F+1];
		if(S==G) {
			System.out.println(0);
		}else {
			bfs();
		}
	}
	
	static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(S);
		visited[S] = 1;
		int[] dx = {U,-D};
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			for(int d=0; d<2; d++) {
				int nx = temp+dx[d];
				if(nx== G) {
					System.out.println(visited[temp]);
					return;
				}
				if(check(nx) && visited[nx]==0) {
					queue.offer(nx);
					visited[nx] = visited[temp]+1;
				}
			}
		}
		System.out.println("use the stairs");
	}
	
	static boolean check(int nx) {
		if(nx>=1 && nx<=F)
			return true;
		return false;
	}
}
