import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BJ_2667_단지번호붙이기_이상훈 {
	
	static int N, aptCount, houseCount, map[][];
	static ArrayList<Integer> countList;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		countList = new ArrayList<>();
		aptCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					aptCount++;
					houseCount = 0;
					dfs(i, j);
					if (houseCount >= 1) countList.add(houseCount);
				}
			}
		}
		
		Collections.sort(countList);
		
		System.out.println(aptCount);
		for (int a: countList) {
			System.out.println(a);
		}
	}
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		houseCount++;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny] && map[nx][ny] == 1) {
				
				dfs(nx, ny);
			}
		}
	}

}
