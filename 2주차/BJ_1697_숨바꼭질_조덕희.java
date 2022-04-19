package com.ssafy.wshw;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_1697_숨바꼭질 {
	static int N,K,arr[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		arr = new int[100001];
		
		if(N==K) {
			System.out.println(0);
		}else {
			bfs(N);
		}
	
	}
	private static void bfs(int n) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		arr[n] = 1;
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			
			for (int i = 0; i < 3; i++) {
				int move;
				if(i==0) {
					move = temp + 1;
				}else if(i==1) {
					move = temp - 1; 
				}else {
					move = temp * 2;
				}
			
			
				if(move == K) {
					System.out.println(arr[temp]);
					return;
				}
				
				if(move>=0&&move<arr.length&&arr[move]==0) {
					q.offer(move);
					arr[move] = arr[temp] + 1;
				}
			}
		}
		
	}

}
