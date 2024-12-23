package algo241223;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek2749 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		long N = Long.parseLong(bfr.readLine());

		// 피사노 주기 반복
		int idx = (int) (N % 1500000);

		long[] fibo = new long[idx + 1];

		// 초기값
		fibo[0] = 0;
		fibo[1] = 1;

		// 피보나치 계산
		for (int i = 2; i <= idx; i++)
			fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1000000;

		// 출력
		System.out.println(fibo[idx]);
	}
}
