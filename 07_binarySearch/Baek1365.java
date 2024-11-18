package algo241118;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1365 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		int[] from = new int[N];

		// dp[i] = i길이를 가지는 최대 전봇대 인덱스
		int[] dp = new int[N];
		dp[0] = Integer.MIN_VALUE;

		// 전선 연결 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		for (int i = 0; i < N; i++) {
			from[i] = Integer.parseInt(stz.nextToken());
		}

		int length = 0;

		// dp로 최장 부분수열 계산. LIS
		for (int i = 0; i < N; i++) {
			if (dp[length] < from[i])
				dp[++length] = from[i];
			else {
				// 이분탐색으로 갱신할 dp[길이]찾기
				int start = 0;
				int end = length;
				int mid;

				while (start < end) {
					mid = (start + end) / 2;
					if (dp[mid] <= from[i])
						start = mid + 1;
					else
						end = mid;
				}

				dp[end] = from[i];
			}
		}

		// 출력
		System.out.println(N - length);

	}
}
