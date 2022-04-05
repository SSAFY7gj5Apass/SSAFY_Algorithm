package com.ssafy.wshw;

import java.util.*;

public class BJ_2606_바이러스 {
	static int computer, connect, cnt;
	static int[][] arr;
	static int[] check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int computer = sc.nextInt();
		int connect = sc.nextInt();
		arr = new int[computer + 1][computer + 1];
		check = new int[computer + 1];
		for (int i = 0; i < connect; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			arr[from][to] = arr[to][from] = 1;

		}

		bfs(1);
		System.out.println(cnt);
	}

	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		check[start] = 1;
		q.offer(start);

		cnt = 0;

		while (!q.isEmpty()) {
			int x = q.poll();

			for (int i = 0; i < arr.length; i++) {
				if (arr[x][i] == 1 && check[i] != 1) {
					q.offer(i);
					check[i] = 1;
					cnt++;
				}
			}
		}

	}

}
