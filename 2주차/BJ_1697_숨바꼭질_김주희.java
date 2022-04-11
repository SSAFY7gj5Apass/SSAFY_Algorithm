package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_1697_숨바꼭질_김주희 {

	public static void main(String[] args) throws IOException {
		// 수빈이는 점 N, 동생은 점 K 위에 있음
		// 수빈이는 걷거나 순간이동 가능
		// 걷기 - 위치가 X일때 1초 후에 X-1나 X+1로 이동
		// 순간이동 - 1초 후에 2*X의 위치로 이동
		// 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇초 후인지 구하기
		// BFS
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("input/input_BJ_1697_숨바꼭질.txt")); 
		
        String[] sp = br.readLine().split(" ");
        int N = Integer.parseInt(sp[0]);
        int K = Integer.parseInt(sp[1]);
        
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] check = new int[100001];
        
        queue.add(N); //초기 위치 넣기
        check[N] = 1;
        int result = 0;
        
        outer:
        while(!queue.isEmpty()) {
        	//count++;
        	
        	if(N == K) {
        		System.out.println(0);
        		break outer;
        	}
        	
        	int loc = queue.poll();
        	int dir = 0;
        	
            for(int d = 0; d < 3; d++) {
            	
            	if(d == 0) {
            		//System.out.println("d=0");
            		dir = loc + 1;
            		
            	}else if(d == 1) {
            		//System.out.println("d=1");
            		dir = loc - 1;
            		
            	}else if(d == 2) {
            		//System.out.println("d=2");
            		dir = loc * 2;
            	}	
            	
            	if(dir < 0 || dir > 100000 || check[dir] != 0) {
            		continue;
            	}
            	
            	if(dir == K) {
            		System.out.println(check[loc]);
            		break outer;
            	}
            	
 
            	queue.add(dir);
            	check[dir] = check[loc] + 1;
            }
        
        }//while
      
        
		
	}

}
