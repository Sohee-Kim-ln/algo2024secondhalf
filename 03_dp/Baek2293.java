package algo241012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2293 {

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		int[] coin = new int[N];
		long[] dp = new long[K + 1];

		// 동전 정보 받기
		for (int i = 0; i < N; i++)
			coin[i] = Integer.parseInt(bfr.readLine());

		// 점화식
		// dp[n] = dp[n-coin[1]]+dp[n-coin[2]]+...

		// 아무것도 고르지 않을 때
		dp[0] = 1;

		// 모든 동전에 대해 반복
		for (int i = 0; i < N; i++) {
			// 현재 동전 더하는 경우를 현재동전 이상의 dp에 더하기
			for (int j = coin[i]; j <= K; j++) {
				dp[j] += dp[j - coin[i]];
			}
		}

		System.out.println(dp[K]);
	}
}
