import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2644_촌수계산_이상훈 {
	
	static int N, result = -1;
	static int targetX, targetY;
	static int[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		targetX = Integer.parseInt(st.nextToken()); // 촌수 계산해야할 사람
		targetY = Integer.parseInt(st.nextToken()); // 촌수 계산해야할 사람
		
		arr = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[r][c] = arr[c][r] = 1;
		}
		

		dfs(targetX, 0);
		System.out.println(result);
	}
	
	public static void dfs(int start, int cnt) {
		
		visited[start] = true;
		
		if (start == targetY) {
			result = cnt;
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (arr[start][i] != 0 && !visited[i]) {
				dfs(i, cnt + 1);
			}
 		}

	}

}
