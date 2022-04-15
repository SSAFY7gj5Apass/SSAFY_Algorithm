package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_9205_맥주마시면서걸어가기 {
	static int N;
	static String answer;
	static ArrayList<Point> store;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(in.readLine()); // 편의점의 갯수
			store = new ArrayList<Point>();
			for(int i=0; i<N+2; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				store.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),i));
			}
			
			Point start = new Point(store.get(0).x, store.get(0).y,0);
			Point goal = new Point(store.get(N+1).x, store.get(N+1).y,N+1);
			bfs(start, goal);
			System.out.println(answer);
		}

	}
	
	static void bfs(Point start, Point goal) {
		if(check(start,goal)) {
			answer = "happy";
			return;
		}
		
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(start);
		boolean[] visited = new boolean[N+2];
		visited[0] = true;
		while(!queue.isEmpty()) {
			Point temp = queue.poll();
			visited[temp.count] = true;
			for(int i=1; i<N+2; i++) {
				if(check(temp, store.get(i)) && visited[i]==false) {
					queue.offer(new Point(store.get(i).x,store.get(i).y,i));
				}
				if(i==N+1 && check(temp,store.get(i)) && visited[i]==false) {
					answer ="happy";
					return;
				}
			}
		}
		answer = "sad";
	}
	
	static boolean check(Point a, Point b) {
		if(Math.abs(a.x-b.x)+Math.abs(a.y-b.y)<=1000) {
			return true;
		}
		return false;
	}
	static class Point{
		int x;
		int y;
		int count;
		public Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;

		}
		
	}

}
