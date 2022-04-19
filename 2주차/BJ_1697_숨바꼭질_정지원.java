import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

public class BJ_1697_숨바꼭질_정지원 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int arr[] = new int[200001];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.MAX_VALUE;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);
		arr[n] = 0;
		while(!q.isEmpty()){
			int cur = q.poll();
			int a = cur + 1;
			int b = cur - 1;
			int c = cur*2;
			if(a>=0 && a<arr.length && arr[a] > arr[cur] + 1) {
				arr[a] = arr[cur] + 1;
				q.add(a);
			}
			if(b>=0 && b<arr.length && arr[b] > arr[cur] + 1) {
				arr[b] = arr[cur] + 1;
				q.add(b);
			}
			if(c>=0 && c<arr.length && arr[c] > arr[cur] + 1) {
				arr[c] = arr[cur] + 1;
				q.add(c);
			}
		}
		System.out.println(arr[k]);
		

	}

}
