package algo241006;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 스트링빌더 사용, 27240KB 212ms
public class Baek9935_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String origin = bfr.readLine();
		String bomb = bfr.readLine();

		int N = origin.length();
		int M = bomb.length();

		StringBuilder sb = new StringBuilder();

		// 문자열 전체 탐색
		for (int i = 0; i < N; i++) {
			char now = origin.charAt(i);
			sb.append(now);

			// 마지막 글자와 같고 길이가 M 이상이면
			if (sb.length() >= M && now == bomb.charAt(M - 1)) {
				boolean isBomb = true;

				// 폭탄인지 확인
				for (int j = 0; j < M; j++) {
					// 폭탄이 아니면 표시 후 break;
					if (sb.charAt(sb.length() + j - M) != bomb.charAt(j)) {
						isBomb = false;
						break;
					}
				}

				// 폭탄 제거
				if (isBomb)
					sb.setLength(sb.length() - M);

			}
		}

		// 출력
		System.out.println(sb.length() != 0 ? sb : "FRULA");
	}
}
