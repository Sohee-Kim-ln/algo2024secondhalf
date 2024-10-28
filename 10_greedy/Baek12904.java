package algo241028;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek12904 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder origin = new StringBuilder(bfr.readLine());
		StringBuilder now = new StringBuilder(bfr.readLine());

		// 역으로 줄여가면서 확인하기
		while (now.length() > origin.length()) {
			boolean isB = false;

			// 현재 문자열 맨뒤가 B면 플래그 켜기
			if (now.charAt(now.length() - 1) == 'B')
				isB = true;

			// 현재 문자열 맨뒤 빼기
			now.setLength(now.length() - 1);

			// 뺀게 B였다면 뒤집기
			if (isB)
				now.reverse();
		}

		// 출력
		System.out.println(now.toString().equals(origin.toString()) ? 1 : 0);
	}
}
