package algo241227;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2225 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());

		// 점화식
		// dp[a][b] = 0부터 b까지의 정수 a개 더해서 b가 되는 경우의 수
		// dp[a][b] = dp[a-1][0] + dp[a-1][1]+ ... + dp[a-1][b]
		// dp[a][b] = dp[a][b-1] + dp[a-1][b]
		int[][] dp = new int[K + 1][N + 1];

		// 초기값 채우기
		// 1개 써서 N만드는 경우는 각 1개씩
		Arrays.fill(dp[1], 1);

		// 0으로만 이루어진 경우는 각 1개씩
		for (int i = 0; i <= K; i++)
			dp[i][0] = 1;

		// dp 계산
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000000;
			}
		}

		System.out.println(dp[K][N]);
	}
}
