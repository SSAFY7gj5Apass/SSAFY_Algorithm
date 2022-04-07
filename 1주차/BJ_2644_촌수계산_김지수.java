package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2644_촌수계산 {
	static int N;
	static int M;
	static int[][] map;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // 전체 사람 수
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		map = new int[N+1][N+1];
		M = Integer.parseInt(in.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine()," ");
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			map[parent][child] = 1;
			
		}

		answer = -1;
		bfs(a,b);
		System.out.println(answer);
	}
	
	static void bfs(int x, int y) {
		Queue<point> queue = new LinkedList<>();
		int cnt = 0;
		boolean[] visited = new boolean[N+1];
		visited[x] = true;
		queue.offer(new point(x,cnt));
		while(!queue.isEmpty()) {
			point temp = queue.poll();
			int nx = temp.x;
			int nCnt = temp.cnt;
			visited[nx] = true;
			//System.out.println("현재 x값 : "+nx+" 현재 count : "+nCnt);
			//탐색한 값이 결과 값이랑 같으면 cnt값 출력
			if(nx==y) {
				answer = nCnt;
				return;
			}
			//부모 방향 탐색 후 queue에 저장
			for(int i=1; i<=N; i++) {
				if(map[i][nx]==1 && !visited[i]) {
					//System.out.println("부모 "+i+"값 저장");
					queue.offer(new point(i,nCnt+1));
				}
			}
			//자식 방향 탐색 후 queue에 저장
			for(int i=1; i<=N; i++) {
				if(map[nx][i]==1 && !visited[i]) {
					//System.out.println("자식 "+i+"값 저장");
					queue.offer(new point(i,nCnt+1));
				}
			}
			
		}
		answer = -1;
	}
	
	static class point{
		int x;
		int cnt;
		public point(int x, int cnt) {

			this.x = x;
			this.cnt = cnt;
		}
		
	}
	

}
