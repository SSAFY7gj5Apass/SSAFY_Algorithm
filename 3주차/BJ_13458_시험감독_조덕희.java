import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_13458_시험감독 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]<=B) {
				cnt++;
				continue;
			}else {
				arr[i]-=B;
				cnt++;
				if(arr[i]%C==0) cnt+=arr[i]/C;
				else cnt+=arr[i]/C+1;																
			}
			
		}
		System.out.println(cnt);
	}

}
