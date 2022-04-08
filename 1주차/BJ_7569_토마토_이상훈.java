import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7569_토마토_이상훈 {
	
	static class Tomato{
		int z, x, y;

		public Tomato(int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
		}
	}
	
	static int M, N, H, box[][][], result;
	static Queue<Tomato> q;
	// 3차원 이동 상 하 좌 우 위 아래
	static int[] dz = {0, 0, 0, 0, -1, 1};
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로
		H = Integer.parseInt(st.nextToken()); // 상자 수
		
		q = new LinkedList<Tomato>();
		
		boolean flag = false;
		box = new int[H][N][M]; // 3차원
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					
					if (box[i][j][k] == 1) q.offer(new Tomato(i, j, k)); // 익은 토마토 큐에 저장
					
					if (box[i][j][k] == 0) flag = true;
				}
			}
		}
		
		result = 0;
		if (!flag) result = 0; // 저장될 때부터 모두 익어있는 경우
		else {
			bfs();
		}
		
		System.out.println(result);
	}
	
	public static void bfs() {
		while(!q.isEmpty()) {
			int size = q.size();
			for (int k = 0; k < size; k++) {
				Tomato curr = q.poll();
				int z = curr.z;
				int x = curr.x;
				int y = curr.y;
				
				for (int i = 0; i < 6; i++) {
					int nz = z + dz[i];
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if (0 <= nz && nz < H && 0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (box[nz][nx][ny] == 0) { // 안익운 토마토일 경우
							box[nz][nx][ny] = 1;
							q.offer(new Tomato(nz, nx, ny));
						}
					}
				}	
			}
			result++;
		}
		
		if (!check()) {
			result = -1;
		} else {
			result--; // 마지막에 방문 시에 증가하기에 1 감소
		}
	}

	// 토마토가 모두 익었는 지 체크
	public static boolean check() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (box[i][j][k] == 0) return false; // 안익은 토마토가 존재하는 경우
				}
			}
		}
		return true;
	}
}
