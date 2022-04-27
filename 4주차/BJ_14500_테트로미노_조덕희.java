import java.io.*;
import java.util.*;

public class BJ_14500_테트로미노 {

	static int N,M,map[][];
	static boolean visited[][];
	static int max;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
	
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
			
		max = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i,j,0,0);
				fucku(i,j);
			}
		}
		System.out.println(max);
		
		
	}
	
	private static void dfs(int x, int y, int sum, int d) {
		if(d==4) {
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0||ny<0||nx>=N||ny>=M||visited[nx][ny])continue;
			
			visited[nx][ny] = true;
			dfs(nx,ny,sum+map[nx][ny],d+1);
			visited[nx][ny] = false;
		}
		
	}
	
	private static void fucku(int x, int y) {
		int min = Integer.MAX_VALUE;
		int sum =  map[x][y];
		int side = 4;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(side<=2) return;
			
			if(nx<0||ny<0||nx>=N||ny>=M) {
				side--;
				continue;
			}
			min = Math.min(min, map[nx][ny]);
			sum += map[nx][ny];
		}
		
		if(side == 4) {
			sum -= min;
		}
		
		max = Math.max(max, sum);
	}

}
