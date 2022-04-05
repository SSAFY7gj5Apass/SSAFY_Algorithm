package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2606_바이러스_김주희 {

	static int cpt, pair, count;
	static boolean[][] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 한 컴퓨터가 바이러스에 걸리면 그 컴퓨터와 연결되어있는 모든 컴퓨터도 걸리게됨
		// 컴퓨터 수와 네트워크 연결 정보가 주어질 때,
		// 1번 컴퓨터를 통해 바이러스에 걸리게 되는 컴퓨터의 수 출력
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input_BJ_2606_바이러스.txt"));
        
		cpt = Integer.parseInt(br.readLine());
		pair = Integer.parseInt(br.readLine());
		
		graph = new boolean[cpt+1][cpt+1];
		visited = new boolean[cpt+1];
		
		for(int i = 0; i < pair; i++) {
			
			String[] sp = br.readLine().split(" ");
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			
			graph[a][b] = true;
			graph[b][a] = true;

		}
		
		//BFS();
		
		DFS(1);
		System.out.println(count-1);
		
	}//main
	
	static private void BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		visited[1] = true;
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			
			for(int i = 1; i <= cpt; i++) {
				//연결되어있고 아직 방문하지 않았으면
				if(graph[num][i] && !visited[i]) {
					queue.add(i);
					visited[i] = true;
				}
			}
		}
		
		int result = 0;
		for(int i = 2; i <= cpt; i++) { //1번 제외
			if(visited[i]) {
				result++;
			}
		}
		System.out.println(result);
	
	}
	
	static private void DFS(int num) {
		visited[num] = true;
		
		count++;
		
		for(int i = 1; i <= cpt; i++) {
			//연결되어있고 아직 방문하지 않았으면
			if(graph[num][i] && !visited[i]) {
				DFS(i);
			}
		}
		
		
	}
	

}
