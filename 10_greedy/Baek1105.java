package algo240918;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1105 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		String L = stz.nextToken();
		String R = stz.nextToken();

		// L R 자릿수 다른 경우 무조건 0
		int res = 0;

		// L R 자릿수 같은 경우
		if (L.length() == R.length()) {
			// 앞자리부터 8인지 검사
			for (int i = 0; i < L.length(); i++) {
				// 둘다 같은 수면
				if (L.charAt(i) == R.charAt(i)) {
					// 8로 같으면 ++, 다른수면 다음자리
					if (L.charAt(i) == '8')
						res++;
					else
						continue;
				}
				// 둘이 다르면 그 사이 8없는 수 존재하므로 종료
				else
					break;
			}
		}

		// 출력
		System.out.println(res);
	}
}
