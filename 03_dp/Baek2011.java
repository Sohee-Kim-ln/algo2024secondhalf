package algo241216;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek2011 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String code = bfr.readLine();
		int len = code.length();

		int[] arr = new int[len + 1];
		int[] dp = new int[len + 1];

		// 점화식
		// dp[i] = dp[i-1]+ 2자리로 글자 만들수 있으면 dp[i-2]\

		// 초기값
		arr[1] = code.charAt(0) - '0';
		dp[0] = 1;

		if (arr[1] != 0)
			dp[1] = 1;

		// 값 변환과 동시에 계산
		for (int i = 2; i <= len; i++) {
			arr[i] = code.charAt(i - 1) - '0';

			if (arr[i - 1] == 0) {
				// 00
				if (arr[i] == 0)
					break;
				// 0x
				else
					dp[i] = dp[i - 1];
			} else {

				// xx 라서 한글자만 가능할 때
				if (arr[i] != 0)
					dp[i] = dp[i - 1];

				// xx가 글자일 때 2글자 전 경우의수 더하기
				if (arr[i - 1] * 10 + arr[i] < 27)
					dp[i] += dp[i - 2];

			}

			dp[i] %= 1000000;
		}

		System.out.println(dp[len]);
	}

}
