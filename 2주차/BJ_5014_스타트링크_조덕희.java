package com.ssafy.wshw;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_5014_스타트링크 {
	static int F,S,G,U,D,map[];
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		F = sc.nextInt();
		S = sc.nextInt();
		G = sc.nextInt();
		U = sc.nextInt();
		D = sc.nextInt();
		
		map = new int[F+1];
		visited= new boolean[F+1];
		Arrays.fill(map, -1);
		
		bfs(S);
		if(map[G] == -1 && S!=G)
			System.out.println("use the stairs");
		else
			System.out.println(map[G]+1);
	}
	private static void bfs(int s) {
		Queue<Integer> q = new LinkedList<Integer>(); 
		q.add(s);
		visited[s] = true;
		
		while(!q.isEmpty()) {
			int temp = q.poll();

			
			if(temp == G) return;
			
			if(temp+U <= F && !visited[temp+U]) {
				q.add(temp+U);
				map[temp+U] = map[temp] + 1;
				visited[temp+U] = true;
			}
			if(temp-D > 0 && !visited[temp-D]) {
				q.add(temp-D);
				map[temp-D] = map[temp] + 1;
				visited[temp-D] = true;
			}
			
		}
	}

}
