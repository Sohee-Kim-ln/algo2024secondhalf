package algo241103;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek7579 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		// {메모리, 비용}
		int[] memory = new int[N + 1];
		int[] cost = new int[N + 1];
		int sum = 0;

		// 메모리, 비용 정보 받기
		stz = new StringTokenizer(bfr.readLine());
		StringTokenizer stzC = new StringTokenizer(bfr.readLine());

		for (int i = 1; i <= N; i++) {
			memory[i] = Integer.parseInt(stz.nextToken());
			cost[i] = Integer.parseInt(stzC.nextToken());
			sum += cost[i];
		}

		// dp[i][j] = i번 앱까지 고려했을 때 j코스트로 만들 수 있는 최대 메모리
		int[][] dp = new int[N + 1][sum + 1];

		// N개 앱에 대해 탐색
		for (int i = 1; i <= N; i++) {
			// 최대 비용까지 1씩 올려가며 계산
			for (int j = 0; j <= sum; j++) {
				// j보다 코스트가 크면 i번째 앱 포함 못하므로 이전앱 상태 유지
				if (j < cost[i])
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);

				// 마지막 앱 고려하면서 제일 먼저 M 이상이 된 경우의 코스트 출력 후 종료
				if (i == N && dp[i][j] >= M) {
					System.out.println(j);
					return;
				}
			}
		}

	}
}
