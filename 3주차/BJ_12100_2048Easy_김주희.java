package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_12100_2048Easy_김주희 {
	
	//static long[][] board;
	static int N;
	static long result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// NxN 보드 위에서 게임 진행
		// 한번의 이동은 전체 블록을 상하좌우 네 방향 중 하나로 이동시키는 것
		// 같은 값을 가지는 두 블록이 충돌하면 하나로 합쳐지게됨
        // 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다. 
	    // 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하는 프로그램을 작성하시오.
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input/input_BJ_12100_2048Easy.txt"));
		
		N = Integer.parseInt(br.readLine());
		long[][] board = new long[N][N];
		result = 0;
		
		//0은 빈칸, 이외는 모두 블록(x>=2)
		for(int i = 0; i < N; i++) {
			String[] sp = br.readLine().split(" ");
			for(int j = 0; j <N; j++) {
				board[i][j] = Integer.parseInt(sp[j]);
			}
		}
		
		DFS(0, board);
		
		System.out.println(result);
	
	}//main
	
	private static void DFS(int count, long[][] board) {
		
		result = Long.max(result, arrayMax(board));
		
		if(count == 5) { //최대 이동횟수 
			return;
		}
		
		DFS(count + 1, right(board));
		DFS(count + 1, left(board));
		DFS(count + 1, up(board));
		DFS(count + 1, down(board));
		
	}
	
	private static long arrayMax(long[][] arry) {
		
		long maxAns = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				maxAns = Long.max(maxAns, arry[i][j]);
			}
		}
		
		return maxAns;
	}
	
	private static long[][] up(long[][] board){
		
		boolean[][] visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[j][i] == 0) {
					continue;
				}
				
				for(int k = j-1; k >= 0; k--) {
					if(board[k][i] == board[k+1][i] && !visited[k][i]) {
						board[k][i] *= 2;
						board[k+1][i] = 0;
						visited[k][i] = true;
						break;
					}
					else if(board[k][i] == 0) {
						board[k][i] = board[k+1][i];
						board[k+1][i] = 0;
					}else {
						break;
					}

				}
			}//for
		}//for

		return board;
	}
	
	private static long[][] down(long[][] board){
	
		boolean[][] visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = N-2; j >= 0; j--) {
				if(board[j][i] == 0) {
					continue;
				}
				for(int k = j+1; k < N; k++) {
					if(board[k][i] == board[k-1][i] && !visited[k][i]) {
						board[k][i] *= 2;
						board[k-1][i] = 0;
						visited[k][i] = true;
						break;
					}else if(board[k][i] == 0) {
						board[k][i] = board[k-1][i];
						board[k-1][i] = 0;
					}else {
						break;
					}

				}
			}//for
		}//for	
		
		return board;
	}
	
	private static long[][] left(long[][] board){
		boolean[][] visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 1; j < N; j++) {
				
				if(board[i][j] == 0) {
					continue;
				}
				for(int k = j-1; k >= 0; k--) {
					if(board[i][k] == board[i][k+1] && !visited[i][k]) {
						board[i][k] *= 2;
						board[i][k+1] = 0;
						visited[i][k] = true;
						break;
					}else if(board[i][k] == 0) {
						board[i][k] = board[i][k+1];
						board[i][k+1] = 0;
					}else {
						break;
					}
				}
			}//for
		}//for
		
		return board;
	}
	
	private static long[][] right(long[][] board){
		boolean[][] visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = N-2; j >= 0; j--) {
				if(board[i][j] == 0) {
					continue;
				}
				for(int k = j+1; k < N; k++) {
					if(board[i][k] == board[i][k-1] && !visited[i][k]) {
						board[i][k] *= 2;
						board[i][k-1] = 0;
						visited[i][k] = true;
						break;
					}else if(board[i][k] == 0) {
						board[i][k] = board[i][k-1];
						board[i][k-1] = 0;
					}else {
						break;
					}
				}
			}//for
		}//for
		
		return board;
	}

}
