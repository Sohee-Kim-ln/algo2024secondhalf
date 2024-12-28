package algo241228;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1106 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int C = Integer.parseInt(stz.nextToken());
		int N = Integer.parseInt(stz.nextToken());

		int[] dp = new int[C + 100];

		// 초기값 설정
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		// 각 도시 정보 받으면서 계산
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine());
			int cost = Integer.parseInt(stz.nextToken());
			int guest = Integer.parseInt(stz.nextToken());

			// guest 이후부터 최소C이후 100명까지
			for (int j = guest; j < C + 100; j++) {
				// 갱신된 값이라면
				if (dp[j - guest] != Integer.MAX_VALUE)
					// 최소값 계산
					dp[j] = Math.min(dp[j], dp[j - guest] + cost);
			}
		}

		// 최소값 도출
		int res = Integer.MAX_VALUE;
		
		for (int i = C; i < C + 100; i++)
			res = Math.min(res, dp[i]);

		System.out.println(res);

	}
}
