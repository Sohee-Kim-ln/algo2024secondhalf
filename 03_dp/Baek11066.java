package algo240712;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 50분 27초 / dp 복습 필요. 점화식은 빠르게 만들었으나 구현 과정에서 시간이 오래걸림

public class Baek11066 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String[] tempS;

		// 출력용 sb
		StringBuilder sb = new StringBuilder();

		// 테케 갯수
		int T = Integer.parseInt(bfr.readLine());

		// 테케 1개에 대해서 수행
		for (int tc = 0; tc < T; tc++) {
			int K = Integer.parseInt(bfr.readLine()); // 장 갯수

			int[] pages = new int[K + 1]; // 페이지 수 배열. 1장부터 이므로 +1

			int[] sum = new int[K + 1];// 부분합 저장 배열

			// 페이지 정보 입력받기
			tempS = bfr.readLine().split(" ");

			for (int i = 1; i <= K; i++) {
				pages[i] = Integer.parseInt(tempS[i - 1]);
				sum[i] = sum[i - 1] + pages[i];

			}

			/*
			 * dp[i][i] = pages[i] dp[i][j] = i장~j장 합치기 비용 dp[i][i+1] = pages[i]+pages[i+1]
			 * dp[i][k] = dp[i][j]+dp[j][k]+sum(i~k) dp[start][end] = min(dp[start][end],
			 * dp[start][mid]+dp[mid+1][end]+누적합[end]-누적합[start-1]
			 */

			int[][] dp = new int[K + 1][K + 1];

			// 묶는 장의 갯수를 1부터 K까지 점차 증가시킬 때
			for (int i = 1; i <= K; i++) {
				// 묶기 시작 포인트를 1부터 K-i까지 옮기면서 계산하기
				for (int start = 1; start <= K - i; start++) {
					int end = start + i;

					// 계산되지 않은 dp 구간 값 초기화
					dp[start][end] = Integer.MAX_VALUE;

					for (int mid = start; mid < end; mid++) {
						dp[start][end] = Math.min(dp[start][end],
								dp[start][mid] + dp[mid + 1][end] + sum[end] - sum[start - 1]);
					}
				}
			}

			sb.append(dp[1][K]);

			if (tc != T - 1)
				sb.append("\n");
		}

		System.out.print(sb);

	}
}
