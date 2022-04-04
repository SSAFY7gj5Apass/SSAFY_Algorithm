package com.ssafy.wshw;

import java.io.*;
import java.util.*;

public class BJ_2178_미로탐색 {

	static int N, M, arr[][];
	static boolean[][] visited;

	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		bfs();
		System.out.println(arr[N - 1][M - 1]);
		sc.close();
	}

	public static void bfs() {
		Queue<Location> q = new LinkedList<Location>();
		q.add(new Location(0,0));

		visited[0][0] = true;

		while (!q.isEmpty()) {
			Location l = q.poll();
			int x = l.x;
			int y = l.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if (arr[nx][ny] == 1 && !visited[nx][ny]) {
						q.add(new Location(nx, ny));

						visited[nx][ny] = true;

						arr[nx][ny] = arr[x][y] + 1;
					}
				}
			}
		}

	}

	static class Location {
		int x, y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
