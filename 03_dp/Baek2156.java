package algo241004;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek2156 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		// {i번 마셨을 때 최대, i번 안마셨을 때 최대}
		int[][] dp = new int[N + 2][2];
		int[] wine = new int[N + 2];

		// 점화식
		// i먹을 때 최대 = i-2 안먹고 i-1먹었을때, i-1안먹었을 때 중 최대 + i
		// dp[i][0]=max(dp[i-2][1]+wine[i-1], dp[i-1][1])+wine[i];
		// dp[i][1]=max(dp[i-1][0], dp[i-1][1]);

		// 포도주 정보 받기
		for (int i = 2; i < N + 2; i++) {
			wine[i] = Integer.parseInt(bfr.readLine());

			dp[i][0] = Math.max(dp[i - 2][1] + wine[i - 1], dp[i - 1][1]) + wine[i];
			dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);

		}

		// 출력
		System.out.println(Math.max(dp[N + 1][0], dp[N + 1][1]));

	}
}
