package com.ssafy.wshw;

import java.util.*;

public class BJ_2644_촌수계산 {
	static int n,m, map[][],result,ans[];
	static boolean visited[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int p1 = sc.nextInt();
		int p2 = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n+1][n+1];
		visited = new boolean[n+1];
		ans = new int[n+1];
		for (int i = 1; i <= m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		bfs(p1,p2);
		if(ans[p2]==0) {
			System.out.println(-1);
		}else {
			System.out.println(ans[p2]);
		}
	}
	private static void bfs(int p1, int p2) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(p1);
		visited[p1] = true;
		result = 0;
		while(!q.isEmpty()) {
			int temp = q.poll();
			if(temp == p2) return;
			
			for (int i = 1; i <= n; i++) {
				if(map[temp][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
					ans[i] = ans[temp] + 1;
				}
			}
		}
	}

}
