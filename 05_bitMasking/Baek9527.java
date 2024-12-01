package algo241201;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Baek9527 {
	static long[] dp = new long[55];

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		long A = Long.parseLong(stz.nextToken());
		long B = Long.parseLong(stz.nextToken());

		// dp[idx] = 1<<idx -1 까지의 1 갯수 누적합
		// dp[idx] = dp[idx-1]+ dp[idx-1]+2^idx;
		dp[0] = 1;

		for (int i = 1; i < 55; i++)
			dp[i] = (dp[i - 1] << 1) + (1L << i);

		// 출력
		System.out.println(count(B) - count(A - 1));

	}

	// num까지 1갯수 누적합 반환
	static long count(long num) {
		long res = num & 1;

		// 높은 자리부터 1 체크
		for (int i = 54; i > 0; i--) {
			// 1있으면
			if ((num & (1L << i)) != 0L) {
				// 한자리 아래 누적합 + 한자리 아래부터 해당숫자까지 수의 갯수
				res += dp[i - 1] + num - (1L << i) + 1;

				// 현재 자리 제거
				num ^= (1L << i);
			}
		}

		return res;
	}
}
