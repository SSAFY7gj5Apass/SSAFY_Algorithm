package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_13460_구슬탈출2_김주희 {
	
	static int N,M;
	static char[][] map;
	static boolean[][][][] visited;
	static int[] dirR = {-1, 1, 0, 0};
	static int[] dirC = {0, 0, -1, 1};
	
	static class Location{
		private int redR;
		private int redC;
		private int blueR;
		private int blueC;
		private int count;
		
		public Location() {}
		
		public Location(int redR, int redC, int blueR, int blueC, int count) {
			this.redR = redR;
			this.redC = redC;
			this.blueR = blueR;
			this.blueC = blueC;
			this.count = count;
		}		
	}
	

	public static void main(String[] args) throws IOException {
		// 구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임
		// 보드 NxM, 가장 바깥 행과 열은 모두 막혀져 있고, 구멍이 하나 있음
		// 빨강 구슬이 구멍에 들어가면 성공
		// 파란 구슬은 구멍에 들어가면 안됨 (실패)
		// 빨강 + 파랑 동시에 구멍에 들어가면 안됨(실패)
		// 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다.
		// 왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기
		// 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하기 (빼낼 수 없으면 -1 출력)
		// BFS
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/input_BJ_13460_구슬탈출2.txt"));
        
		// '.', '#', 'O', 'R', 'B'
		// . 빈칸
		// # 공이 이동할 수 없는 장소
		// o 구멍의 위치
		// R 빨간구슬 위치
		// B 파란구슬 위치
		
		String line;
		while((line = br.readLine()) != null) {
			String[] sp = line.split(" ");
			N = Integer.parseInt(sp[0]);
			M = Integer.parseInt(sp[1]);
			
			map = new char[N][M];
			visited = new boolean[N][M][N][M];
			
			for(int i = 0; i < N; i++) {
				sp = br.readLine().split("");
				for(int j = 0; j < M; j++) {
					map[i][j] = sp[j].charAt(0);
					//System.out.print(map[i][j] + " ");
				}
				//System.out.println();
			}
			
			Location loc = new Location();
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 'R') {
						loc.redR = i;
						loc.redC = j;
					}
					
					if(map[i][j] == 'B') {
						loc.blueR = i;
						loc.blueC = j;
					}
				}
			}
			
			loc.count = 0;
			int result = BFS(loc);
	        
			System.out.println(result);
						
		}//while
				
	}//main
	
	private static int BFS(Location loc) {
	    Queue<Location> locQ = new LinkedList<Location>();
		locQ.add(loc);
		visited[loc.redR][loc.redC][loc.blueR][loc.blueC] = true;
		
	    int temp = -1;
	    
	    while(!locQ.isEmpty()) {
	    	Location current = locQ.poll();
	    	
	    	if(current.count > 10) {
	    		break;
	    	}
	    	//파란색은 남아있고 빨간색만 빠져나갔으면 성공
	    	if(map[current.redR][current.redC] == 'O' && map[current.blueR][current.blueC] != 'O') {
	    		temp = current.count;
	    		break;
	    	}
	    	
	    	for(int i = 0; i < 4; i++) {
	    		int nextRedR = current.redR;
	    		int nextRedC = current.redC;
	    		int nextBlueR = current.blueR;
	    		int nextBlueC = current.blueC;
	    		
	    		//빨간공이 벽에 닿을 때까지 진행
	    		while(true) {
	    			if(map[nextRedR][nextRedC] != '#' && map[nextRedR][nextRedC] != 'O') {
	    				nextRedR += dirR[i];
	    				nextRedC += dirC[i];
	    			}else {
	    				if(map[nextRedR][nextRedC] == '#') {
	    					nextRedR -= dirR[i];
	    					nextRedC -= dirC[i];	    					
	    				}
	    				break;    				
	    			}    			
	    		}
	    		
	    		//파란공이 벽에 닿을 때까지 진행
	    		while(true) {
	    			if(map[nextBlueR][nextBlueC] != '#' && map[nextBlueR][nextBlueC] != 'O') {
	    				nextBlueR += dirR[i];
	    				nextBlueC += dirC[i];
	    			}else {
	    				if(map[nextBlueR][nextBlueC] == '#') {
	    					nextBlueR -= dirR[i];
	    					nextBlueC -= dirC[i];	    					
	    				}
	    				break;    				
	    			}    			
	    		}
	    		
	    		if(nextRedR == nextBlueR && nextRedC == nextBlueC) {
	    			if(map[nextRedR][nextRedC] != 'O') {
	    				int redDist = Math.abs(nextRedR - current.redR) + 
	    						      Math.abs(nextRedC - current.redC);
	    				int blueDist = Math.abs(nextBlueR - current.blueR) + 
	    						      Math.abs(nextBlueC - current.blueC);
	    				
	    				if(redDist > blueDist) {
	    					nextRedR -= dirR[i];
	    					nextRedC -= dirC[i];
	    				}else {
	    					nextBlueR -= dirR[i];
	    					nextBlueC -= dirC[i];
	    				}		
	    			}
	    		}
	    		
	    		if(visited[nextRedR][nextRedC][nextBlueR][nextBlueC] == false){
	    			visited[nextRedR][nextRedC][nextBlueR][nextBlueC] = true;
	    			Location next = new Location();
	    			next.redR = nextRedR;
	    			next.redC = nextRedC;
	    			next.blueR = nextBlueR;
	    			next.blueC = nextBlueC;
	    			next.count = current.count + 1;
	    			locQ.add(next);
	    		}	    		
	    		
	    	}//for

	    }//while
	    
		return temp;
	}//BFS
	
	

}
