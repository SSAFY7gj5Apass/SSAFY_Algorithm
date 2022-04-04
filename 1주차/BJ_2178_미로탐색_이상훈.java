import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2178_미로탐색_이상훈 {
	
	static class Node{
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		visited = new boolean[N][M];
		
		bfs(0, 0);
		
		System.out.println(map[N - 1][M - 1]);
	}

	public static void bfs(int x, int y) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(x, y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			int currX = current.x;
			int currY = current.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = currX + dx[i];
				int ny = currY + dy[i];
				
				// 1. 맵 범위 안이고 2. 방문 한적 없고 3. 이동 가능한 칸이면
				if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny] && map[nx][ny] == 1) {
					q.offer(new Node(nx, ny));
					map[nx][ny] = map[currX][currY] + 1;
					visited[nx][ny] = true;
				}
			}
		}
	}
	
}
