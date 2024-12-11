package algo241211;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2616 {

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 객차 수
		int N = Integer.parseInt(bfr.readLine());
		int[] train = new int[N + 1];
		int[] sum = new int[N + 1];

		// 손님 수 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		for (int i = 1; i <= N; i++) {
			train[i] = Integer.parseInt(stz.nextToken());
			sum[i] = sum[i - 1] + train[i];
		}

		// 최대 객차 수
		int M = Integer.parseInt(bfr.readLine());

		// [i번째 기차][j칸] 까지 고려했을 때 최대 승객 수
		// 점화식 dp[i][j] = 현재칸 포함 안함, 현재칸 포함함 중 큰 값
		int[][] dp = new int[4][N + 1];

		// 기차 별
		for (int i = 1; i <= 3; i++) {
			// 객차 별
			for (int j = i * M; j <= N; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + sum[j] - sum[j - M]);
			}
		}

		System.out.println(dp[3][N]);
	}

}
