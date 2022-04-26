import java.util.*;
import java.io.*;

public class BJ_14501_퇴사 {
	static int N,map[][],result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		result = 0;
		dfs(0,0);
		System.out.println(result);
	}
	
	private static void dfs(int index, int cost) {
		if(index>=N) {
			result = Math.max(result, cost);
			return;
		}
		
		if(index+map[index][0]<=N) {
			dfs(index+map[index][0],cost+map[index][1]);
		}else {
			dfs(index+map[index][0],cost);
		}
		
		dfs(index+1,cost);
	}

}
