import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5014_스타트링크_정지원 {
	static int visitCnt[];
	static int f,s,g,u,d;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		f = Integer.parseInt(st.nextToken()); //총 건물 층
		s = Integer.parseInt(st.nextToken()); //강호가 있는 층
		g = Integer.parseInt(st.nextToken()); //스타트링크 있는 층
		u = Integer.parseInt(st.nextToken()); //올라가는 층수
		d = Integer.parseInt(st.nextToken()); //내려가는 층수
		visitCnt = new int[f+1];
		for (int i = 0; i <= f; i++) {
			visitCnt[i] = Integer.MAX_VALUE;
		}
		bfs(s);
		if(visitCnt[g] == Integer.MAX_VALUE) {
			System.out.println("use the stairs");
		} else {
			System.out.println(visitCnt[g]);
		}
		
	}
	public static void bfs(int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		visitCnt[s] = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			int up = cur + u;
			int dowm = cur - d;
			if(up>=1 && f>=up && visitCnt[up] > visitCnt[cur] + 1) {
				visitCnt[up] = visitCnt[cur] + 1;
				q.add(up);
			}
			if(dowm>=1 && f>=dowm && visitCnt[dowm] > visitCnt[cur] + 1) {
				visitCnt[dowm] = visitCnt[cur] + 1;
				q.add(dowm);
			}
		}
	}

}
