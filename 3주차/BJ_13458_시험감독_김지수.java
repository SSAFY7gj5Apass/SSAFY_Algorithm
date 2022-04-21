package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_13458_시험감독 {
	static int N;
	static int[] A; // 각시험장의 응시자
	static int B; // 총감독관이 감독할수 있는 최대 응시자 수
	static int C; // 부감독관이 감독할 수 있는 최대 응시자 수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine(), " ");
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		long count = 0; // 감독관의 최소 수

		for (int i = 0; i < N; i++) {
			count++;
			if (A[i] - B >= 0) {
				A[i] = A[i]-B;
			}
			else if(A[i]-B < 0){
				A[i] = 0;
			}
		}
		
		for(int i=0; i<N; i++) {
			if(A[i]<=0)
				continue;
			if(A[i]%C ==0) {
				long temp = A[i]/C;
				count+=temp;
				continue;
			}
			else {
				long temp = A[i]/C;
				count += temp+1;
				continue;
			}
		}
		
		System.out.println(count);
	}

}
