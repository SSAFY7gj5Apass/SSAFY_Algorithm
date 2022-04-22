package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_3190_뱀_김주희 {
	
	static int[][] map;
	static int N;
	static int[] time; 
	static char[] dir;
	
	static class Location{
		private int row;
		private int col;
		
		public Location(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int[] dirR = {0, 1, 0, -1}; //오, 하, 좌, 상
	static int[] dirC = {1, 0, -1, 0};
	static Deque<Location> snakeDQ;

	public static void main(String[] args) throws IOException {
		// 사과를 먹으면 뱀 길이가 늘어나는 도스게임
		// 기어다니다가 벽 또는 자기자신의 몸에 부딪히면 게임 종료
		// NxN 정사각형보드, 몇몇칸에는 사과 존재
		// 뱀은 맨위 맨 좌측에서 시작, 초기 방향 오른쪽
		// 매 초마다 이동 시 동작
		//(1) 몸길이를 늘려 머리를 다음칸에 위치시키기
		//(2) 이동한 칸에 사과가 있으면, 그 칸에 있던 사과가 없어지고, 꼬리는 움직이지 않는다.
		//(3) 이동한 칸에 사과가 없으면, 몸길이를 줄여서 꼬리가 위치한 칸을 비운다. 몸길이 변화X
		// 사과의 위치와 뱀의 이동경로가 주어질 떄, 몇초만에 게임이 끝나는지 계산
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/input_BJ_3190_뱀.txt"));
		
		String line;
		while((line = br.readLine()) != null) {
			
			N = Integer.parseInt(line); //보드 크기 N
			int K = Integer.parseInt(br.readLine()); //사과 개수 K
			
			map = new int[N][N];
			
			//0으로 초기화
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = 0;
				}
			}
			
			//사과 위치 받아서 map에 표시
			for(int i = 0; i < K; i++) {
				String[] sp = br.readLine().split(" ");
				int appRow = Integer.parseInt(sp[0]);
				int appCol = Integer.parseInt(sp[1]);
				map[appRow-1][appCol-1] = 1; //사과는 1
			}
			
			int L = Integer.parseInt(br.readLine()); //뱀의 방향변환 횟수
			time = new int[L];
			dir = new char[L];
			
		    for(int i = 0; i < L; i++) {
		    	String[] sp = br.readLine().split(" ");
		    	int X = Integer.parseInt(sp[0]); //시작시간으로부터 X초가 끝난 뒤에
		    	char C = sp[1].charAt(0); //왼쪽 또는 오른쪽으로 90도 방향 회전	
		    	time[i] = X;
		    	dir[i] = C;
		    }
			
		    //map[0][0] = 2; //뱀은 2
		    snakeDQ = new ArrayDeque<>();
		    int result = Move(0, 0);
		    
		    System.out.println(result);
			
		}//while
	}//main
	
	private static int Move(int row, int col) {
		int direction = 0; //초기엔 오른쪽부터 시작 
		int r, c = 0;
		int count = 0;
		int index = 0;
		
		snakeDQ.addLast(new Location(row, col));
		while(true)
		{
			r = snakeDQ.getFirst().row;
			c = snakeDQ.getFirst().col;
			map[r][c] = 2; //뱀
			count++;
			
			int nextR = r + dirR[direction];
			int nextC = c + dirC[direction];
			//System.out.println("nextR: " + nextR);
			//System.out.println("nextC: " + nextC);
			//System.out.println("map[nextR][nextC]: " + map[nextR][nextC]);
			 
			//벽이나 자기자신의 몸에 부딪히면 종료 
			if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || map[nextR][nextC] == 2) {
				return count;
			}
			else {
				snakeDQ.addFirst(new Location(nextR, nextC));
				
				//사과가 없으면 deque 마지막에서 pop
				if(map[nextR][nextC] != 1) {
					int lastR = snakeDQ.getLast().row;
					int lastC = snakeDQ.getLast().col;
					map[lastR][lastC] = 0;
					snakeDQ.pollLast();
				}
				
				//주어진 시간에 방향 변경
				//System.out.println("count: "+ count);
				//System.out.println("time[index]: "+ time[index]);
				if(count == time[index]) {
					if(dir[index] == 'L') { //ex) 오른쪽(0) -> 상(3)
						//System.out.println("L");
						direction += 3;
						direction %= 4;
					}
					else if(dir[index] == 'D'){ //ex) 오른쪽(0) -> 하(1)
						//System.out.println("D");
						direction += 1;
						direction %= 4;
					}
					
					if(index < time.length - 1) {
						index++;
					}
				}
			}//else
		}//while

	}//Move

}
