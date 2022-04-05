package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2606_바이러스 {
	static int num;
	static int N;
	static ArrayList<point> computer;
	static boolean[] visited;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(in.readLine()); //컴퓨터 수
		N = Integer.parseInt(in.readLine()); // 연결된 쌍
		computer = new ArrayList<point>();
		visited = new boolean[num+1];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			computer.add(new point(y,x));
			computer.add(new point(x,y)); // bfs 로직이 end에서 start로만 연결하게끔 만들어서 (start end)가 반대인 상황도 확인하게끔 리스트에 넣어줌
		}
		Collections.sort(computer);
		
		//debug
//		for(point i : computer) {
//			System.out.println(i.start+" "+i.end);
//		}
		bfs();
		answer = 0;
		for(int i=1; i<num+1; i++) {
			if(visited[i]==true) {
				answer++;
			}
		}
		System.out.println(answer-1);
	}//main 끝
	static void bfs() {
		
		Queue<point> queue = new LinkedList<point>();
		//1번 컴퓨터의 감염정보 담기
		visited[1] = true;
		for(point i : computer) {
			if(i.start == 1) {
				queue.offer(i);
			}
		}

		while(!queue.isEmpty()) {
			point temp = queue.poll();
			int end = temp.end;
			if(visited[end]==false) {
				visited[end] = true;
				for (point i : computer) {
					if (i.start == end) {
						queue.offer(i);
					}
				}
			}

		}//while문 끝
	}
	static class point implements Comparable<point>{
		int start;
		int end;
		
		public point(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(point o) {
			if(this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}
	}

}
