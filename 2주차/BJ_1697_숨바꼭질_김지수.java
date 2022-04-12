package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1697_숨바꼭질 {
	static int N;
	static int K;
	static int[] visited = new int[100000+1];
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if(N==K) {
			System.out.println(0);
		}
		else {
			bfs();
		}

	}
	
	static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(N);
		visited[N] = 1;
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			
			for(int d=0; d<3; d++) {
				int nx;
				if(d==0) {
					nx = temp+1;
				}
				else if(d==1) {
					nx = temp-1;
				}
				else {
					nx = temp*2;
				}

				if(nx == K) {
					System.out.println(visited[temp]);
					return;
				}
				
				if(check(nx) && visited[nx] == 0) {
					queue.offer(nx);
					System.out.println(visited[temp]+" "+nx);
					visited[nx] = visited[temp]+1;
				}
			}
			
		}

	}
	static boolean check(int nx) {
		if(nx>=0 && nx<=100000)
			return true;
		return false;
	}
	

}
