package algo241114;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek18222 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 자연수 K
		long K = Long.parseLong(bfr.readLine());

		boolean reverse = false;

		long base = 1;
		long pos = K;

		// 현재 문자가 포함된 2^n구하기
		while (base < K)
			base <<= 1;

		// 1위치 갈때까지 뒤집기 표시
		while (pos != 1) {
			base >>= 1;

			if (pos > base) {
				pos -= base;
				reverse = !reverse;
			}
		}

		// 출력
		System.out.println(reverse ? '1' : '0');
	}
}
