package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2573_빙산 {
	static int N;
	static int M;
	static int[][] map;
	static int[] dy = {-1,1,0,0};//상하좌우
	static int[] dx = {0,0,-1,1};
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;
		int count = -1;
		int year = 0; //빙산이 갈라지는 햇수를 측정하는 변수
		outer : while(count!=0) {
			//빙하가 존재할 때 그 빙하가 1년후에 줄어들 크기를 저장할 2차원배열
			int[][] newMap = new int[N][M];
			count = 0; //빙하가 존재하면 카운팅하는 변수
			year++;
			int cnt = 0; //빙산이 갈라졌을 때 빙산의 갯수를 측정하기 위한 변수
			
			//빙하가 존재하면 count를 증가시키고 newMap에 줄어들 빙하의 양을 저장하기 위한 반복문
			for(int i=1; i<N-1; i++) {
				for(int j=1; j<M-1; j++) {
					if(map[i][j]!=0) {
						count++;
						for(int d=0; d<4; d++) {
							int ny = i+dy[d];
							int nx = j+dx[d];
							if(check(ny,nx) && map[ny][nx]==0) {
								newMap[i][j]++;
							}
						}
					}
				}
			}
			
			for(int i=1; i<N-1; i++) {
				for(int j=1; j<M-1; j++) {
					if(map[i][j]-newMap[i][j]>=0) {
						map[i][j] -= newMap[i][j];
					}
					else {
						map[i][j] = 0;
					}
				}
			}
			
			//빙산이 두개로 갈라지는지 확인하기 위한 2차원 배열
			newMap = new int[N][M]; 
			boolean[][] visited = new boolean[N][M];
			//하나의 빙산이 두 덩어리 이상으로 갈라지는지 확인하기 위한 BFS탐색
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]!=0 && visited[i][j]==false) {
						cnt++;
						if(cnt>1) {
							answer = year;
							break outer;
						}
						bfs(i,j,cnt,newMap, visited);
					}
				}
			}

		}//while문 끝
		System.out.println(answer);
	}//main
	
	static void bfs(int y, int x, int cnt, int[][] newMap, boolean[][] visited) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(y,x));
		
		while(!queue.isEmpty()) {
			Point temp = queue.poll();
			for(int d =0; d<4; d++) {
				int ny = temp.y+dy[d];
				int nx = temp.x+dx[d];
				if(check(ny,nx) && map[ny][nx]!=0 && visited[ny][nx]==false) {
					newMap[ny][nx] = cnt;
					visited[ny][nx] = true; // 방문처리를 여기서 해주어야함! while문 아래에 할경우 시간초과 발생!(이유 : 빙산문제는 같은 빙산인지만 체크하면 되기때문에 어디에서 출발하는지는 상관없다.)
					queue.offer(new Point(ny, nx));
				}
			}
		}
	}
	static boolean check(int ny, int nx) {
		if(ny>=0 && ny<N && nx>=0 && nx<M)
			return true;
		return false;
	}
	
	static class Point{
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}
