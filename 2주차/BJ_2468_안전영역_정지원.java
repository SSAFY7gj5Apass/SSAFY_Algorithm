package ws_04_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468_안전영역_정지원 {
	static int n, areaMax,cnt; // n : 배열의 크기 , areaMax : 안전영역 최대 값 , cnt : 
	static int arr[][], arrCopy[][]; //인풋담을 배열, 높이보다 더 높은 값만 카피할 배열
	static int visit[][]; //방문 처리 배열 카운트를 세기 위해 int형으로
	static int dr[] = {-1, 1, 0 ,0}; 
	static int dc[] = {0,0 ,-1, 1}; //상하좌우
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		StringTokenizer st = null;
		int arrMax = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] > arrMax) {
					arrMax = arr[i][j]; //배열에서 가장 높은 값 이 높이까지만 돌린다.
				}
			}
		}
		areaMax = Integer.MIN_VALUE;
		for (int i = 0; i <= arrMax; i++) {
			safeArea(i); //0부터 max 높이까지 돌리기
		}
		System.out.println(areaMax); //출력 
		

	}
	public static void safeArea(int h) { //높이에 따라 안전영역 구하기
		arrCopy = new int[n][n];
		visit = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				visit[i][j] = -1; //visit = -1로 초기화
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] > h) {
					arrCopy[i][j] = arr[i][j]; //높이 보다 큰 값만 copy
				}
			}
		}
		cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arrCopy[i][j] > h && visit[i][j] == -1) { //높이 보다 높은 값이 들어 있고 방문 안했으면 
					bfs(i,j, cnt, h); //bfs 돌리고 
					cnt++;//cnt++ 해주기
				}
			}
		}
		areaMax = Math.max(cnt, areaMax); //cnt와 areaMax중 최대 값을 넣어준다.
	}
	public static void bfs(int r, int c, int cnt, int h) { //bfs 돌리기
		Queue<Pos> q = new LinkedList<Pos>(); 
		q.add(new Pos(r,c)); 
		visit[r][c] = cnt; //초기위치에 cnt 넣어주기
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i]; 
				if(arrayArea(nr, nc) && visit[nr][nc] == -1 && arrCopy[nr][nc] > h) {
					visit[nr][nc] = cnt;
					q.add(new Pos(nr, nc));
				}
			} 
		}
	}
	public static boolean arrayArea(int r, int c) { //배열 안 영역인 지 검사하는 메소드
		return r>=0 && r<n && c>=0 && c<n;
	}
	public static class Pos { //큐에담을 r,c를 정의 한 Pos 클래스
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
}
