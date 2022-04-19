package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_13460_구슬탈출2 {
	static int N;
	static int M;
	static char[][] map;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로크기
		M = Integer.parseInt(st.nextToken()); // 가로크기
		map = new char[N][M];
		int ry = 0;
		int rx = 0;
		int by = 0;
		int bx = 0;
		for (int i = 0; i < N; i++) {
			String s = in.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					ry = i;
					rx = j;
				} else if (map[i][j] == 'B') {
					by = i;
					bx = j;
				}
			}
		}
		answer = -1;
		bfs(ry, rx, by, bx, 0);
		System.out.println(answer);
	}

	static void bfs(int ry, int rx, int by, int bx, int cnt) {
		boolean[][] visited = new boolean[N][M];
		visited[ry][rx] = true;
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		queue.offer(new Point(ry, rx, by, bx, cnt, visited));

		// 빨간 구슬과 파란 구슬이 동시에 구멍에 빠지면 실패
		outer: while (!queue.isEmpty()) {
			Point temp = queue.poll();
			int nCnt = temp.cnt;
			//상하좌우 순서로 기울이기
			inner: for (int d = 0; d < 4; d++) {
				int nry = temp.ry;
				int nrx = temp.rx;
				int nby = temp.by;
				int nbx = temp.bx;
				
				//기울였을 때 움직인 빨간구슬이 벽에 닿지 않는 경우 계속 이동
				while(check(nry+dy[d], nrx+dx[d]) && map[nry+dy[d]][nrx+dx[d]]!='#') {
					nry += dy[d];
					nrx += dx[d];
					//만약 빨간구슬이 O에 도착한다면 파란구슬을 이동시켜보고 파란구슬이 O에 도착하지 못한다면 nCnt를 1증가시킨 후 answer에 저장 
					if(map[nry][nrx] == 'O') {
						//파란구슬이 O에 도착하는지 판단하는 boolean 변수 flag
						boolean flag = true;
						int tempby = temp.by;
						int tempbx = temp.bx;
						while(check(tempby+dy[d],tempbx+dx[d]) && map[tempby+dy[d]][tempbx+dx[d]]!='#') {
							tempby+=dy[d];
							tempbx+=dx[d];
							if(map[tempby][tempbx]=='O') {
								flag = false;
							}
						}
						if(flag==true) {
							nCnt++;
							answer = nCnt;
							break outer;
						}
					}
					
					//이동하는 중간에 파란 구슬을 만나는 경우 이동한 빨간 구슬을 다시 원래자리로 돌리고 파란구슬을 먼저 이동하는 로직 수행
					if(nry== nby && nrx == nbx) {
						nry -= dy[d];
						nrx -= dx[d];
						while(check(nby+dy[d],nbx+dx[d]) && map[nby+dy[d]][nbx+dx[d]]!='#') {	
							nby+=dy[d];
							nbx+=dx[d];
							//만약 파란구슬이 O에 도착한다면 기울인 방향이 잘못된것이므로 다음방향으로 기울이기(for문 이동)
							if(map[nby][nbx]=='O') {
								continue inner;
							}
						}
						//파란구슬의 이동이 끝났다면 다시 빨간구슬을 파란구슬 위치 직전 위치까지 이동시킨 후  while문 종료
						nry = nby-dy[d];
						nrx = nbx-dx[d];
						break;
					}
				}

				//파란공을 만나지 않고 빨간공이 이동했을 경우 파란공의 이동은 그 이후에 진행
				while(check(nby+dy[d],nbx+dx[d]) && map[nby+dy[d]][nbx+dx[d]]!='#') {
					nby+=dy[d];
					nbx+=dx[d];
					//만약 파란구슬이 O에 도착한다면 기울인 방향이 잘못된 것이므로 다음방향으로 기울이기(for문 이동)
					if(map[nby][nbx]=='O') {
						continue inner;
					}
					//만약 파란구슬이 빨간구슬을 만난다면 한칸 줄인후 while문 탈출
					if(nby == nry && nbx == nrx) {
						nby -= dy[d];
						nbx -= dx[d];
						break;
					}
				}
				

				
				//queue에 현재 빨간구슬, 파란구슬의 위치정보 및 이동한 횟수 저장
				if(nCnt+1>10) {
					return;
				}
				temp.visited[nry][nrx] =true;
				queue.offer(new Point(nry, nrx, nby, nbx, nCnt+1, temp.visited));
				temp.visited[nry][nrx] =false;
			}
		}

	}

	static boolean check(int ny, int nx) {
		if (ny > 0 && ny < N - 1 && nx > 0 && nx < M - 1)
			return true;
		return false;
	}

	static class Point implements Comparable<Point>{
		int ry;
		int rx;
		int by;
		int bx;
		int cnt;
		boolean[][] visited;

		public Point(int ry, int rx, int by, int bx, int cnt, boolean[][] visited) {
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
			this.cnt = cnt;
			this.visited = visited;
		}


		@Override
		public int compareTo(Point o) {
			return this.cnt-o.cnt;
		}

	}

}
