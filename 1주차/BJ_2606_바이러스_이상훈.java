import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2606_바이러스_이상훈 {
	
	static int V, E, result;
	static ArrayList<Integer>[] nodeList;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		V = Integer.parseInt(br.readLine()); // 노드
		E = Integer.parseInt(br.readLine()); // 간선
		
		nodeList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			nodeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nodeList[from].add(to);
			nodeList[to].add(from); // 추가해야 오류 안뜸
		}
		
		visited = new boolean[V + 1];
		result = 0;
		bfs(1);
		
		System.out.println(result);
	}
	
	public static void bfs(int x) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(x);
		visited[x] = true;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			for (int n : nodeList[curr]) {
				if (!visited[n]) {
					q.offer(n);
					result++;
					visited[n] = true;
				}
			}
		}
	}
}
