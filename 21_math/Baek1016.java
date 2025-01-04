package algo250104;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1016 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		long min = Long.parseLong(stz.nextToken());
		long max = Long.parseLong(stz.nextToken());

		long cnt = max - min + 1;
		boolean[] isSqrt = new boolean[(int) cnt];

		// 제곱이 범위내 일 동안 탐색
		for (long i = 2; i * i <= max; i++) {
			long pow = i * i;

			long now = min / pow;

			// 제곱수 아니면 다음 수부터 체 시작
			if (min % pow != 0)
				now += 1;

			// 에라토스테네스의 체
			for (long j = now; j * pow <= max; j++) {
				int temp = (int) (j * pow - min);
				if (!isSqrt[temp]) {
					isSqrt[temp] = true;
					cnt--;
				}
			}
		}

		// 출력
		System.out.println(cnt);
	}
}