import java.util.*;
import java.io.*;

public class BJ_13406_구슬탈출2 {

	static int N,M,hx,hy;
	static char map[][];
	static boolean check[][][][];
	static RBposition blue,red;
	
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		check  = new boolean[N][M][N][M];
		
		
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'B') {
					blue = new RBposition(0, 0, i, j, 0);
				}
				else if(map[i][j]=='R') {
					red = new RBposition(i, j, 0, 0, 0);
				}else if(map[i][j] == 'O') {
					hx = i;
					hy = j;
				}
			}
		}
		
		System.out.println(bfs());
		
	
	
	}
	private static int bfs() {
		Queue<RBposition> q = new LinkedList<>();
		q.add(new RBposition(red.rx, red.ry, blue.bx, blue.by, 1));
		check[red.rx][red.ry][blue.bx][blue.by] = true;
		
		while(!q.isEmpty()) {
			RBposition rb = q.poll();
			
			
			int crx = rb.rx;
			int cry = rb.ry;
			int cbx = rb.bx;
			int cby = rb.by;
			int cCnt = rb.cnt;
			
			if(cCnt > 10) {
				return -1;
			}
			
			for (int i = 0; i < 4; i++) {
				int nrx = crx;
				int nry = cry;
				int nbx = cbx;
				int nby = cby;
				
				boolean redHoleCheck = false;
				boolean blueHoleCheck = false;
				
				while(map[nrx+dx[i]][nry+dy[i]] != '#') {
					nrx += dx[i];
					nry += dy[i];
					
					if(nrx == hx && nry == hy) {
						redHoleCheck = true;
						break;
					}
				}
				
				while(map[nbx+dx[i]][nby+dy[i]] != '#') {
					nbx += dx[i];
					nby += dy[i];
					
					if(nbx == hx && nby == hy) {
						blueHoleCheck =true;
						break;
					}
				}
				
				if(blueHoleCheck) {
					continue;
				}
				if(redHoleCheck && !blueHoleCheck) {
					return cCnt;
				}
				
				if(nrx == nbx && nry == nby) {
					if(i==0) {//좌
						if(cry>cby) nry -= dy[i];
						else nby -= dy[i];
					}
					else if(i==1) {//상
						if(crx>cbx) nrx -= dx[i];
						else nbx -= dx[i];
					}
					else if(i==2) {//우
						if(cry<cby) nry -= dy[i];
						else nby -= dy[i];
					}
					else {//하
						if(crx<cbx) nrx -= dx[i];
						else nbx -= dx[i];
					}
				}
				
				if(!check[nrx][nry][nbx][nby]) {
					check[nrx][nry][nbx][nby] = true;
					q.add(new RBposition(nrx, nry, nbx, nby, cCnt+1));
				}
			}
			
			
			
		}
		
		return -1;
		
	}
	
	static class RBposition{
		int rx,ry,bx,by,cnt;

		public RBposition(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
		
		
	}

}
