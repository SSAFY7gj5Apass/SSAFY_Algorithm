import java.io.*;
import java.util.*;

public class BJ_14503_로봇청소기 {

	static int N, M, map[][], cnt;
	static boolean[][] visited;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cnt = 0;
		dfs(r, c, d);
		
		System.out.println(cnt);
	}

	private static void dfs(int r, int c, int d) {
		boolean check = false;

		if (!visited[r][c]) {
			visited[r][c] = true;
			cnt++;
		}

		for (int i = 0; i < 4; i++) {
			d = turn(d);
			int nr = r + dx[d];
			int nc = c + dy[d];

			if (map[nr][nc] == 0 && !visited[nr][nc]) {
				dfs(nr, nc, d);
				check = true;
				break;
			}
		}

		if (check == false) {
			int nr = r - dx[d];
			int nc = c - dy[d];

			if (map[nr][nc] == 0) {
				dfs(nr, nc, d);
			}
		}
	}

	private static int turn(int d) {
		if (d == 0) {
			return 3;
		} else if (d == 1) {
			return 0;
		} else if (d == 2) {
			return 1;
		} else {
			return 2;
		}

	}

}
