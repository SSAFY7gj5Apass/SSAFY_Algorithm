import java.util.Scanner;

public class BJ_14888_연산자끼워넣기 {

	static int N, input[], arr[];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		arr = new int[4];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}

		dfs(input[0], 1);
		System.out.println(max);
		System.out.println(min);

	}

	private static void dfs(int n, int d) {

		if (d == N) {
			max = Math.max(max, n);
			min = Math.min(min, n);
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				
				arr[i]-=1;
				
				switch (i) {
				case 0:
					dfs(n+input[d], d+1);
					break;
				case 1:
					dfs(n-input[d], d+1);
					break;
				case 2:
					dfs(n*input[d], d+1);
					break;
				case 3:
					dfs(n/input[d], d+1);
					break;
				}
				arr[i]+=1;

			}
		}
	}

}
