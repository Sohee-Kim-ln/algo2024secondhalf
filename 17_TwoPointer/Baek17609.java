package algo241123;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek17609 {
	
	static String str;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());

		StringBuilder sb = new StringBuilder();

		// 각 테케에 대해 수행
		for (int tc = 0; tc < T; tc++) {
			str = bfr.readLine();

			int start = 0;
			int end = str.length() - 1;

			// 검사 후 결과 저장
			sb.append(isPalin(start, end, false)).append('\n');
		}

		// 출력
		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}

	static int isPalin(int start, int end, boolean isDeleted) {
		// 회문 검사
		while (start < end) {

			// 양쪽 글자 같으면 다음 글자 검사
			if (str.charAt(start) == str.charAt(end)) {
				start++;
				end--;
				continue;
			}

			// 이미 뺀 상태인데 다르면 2 반환 종료
			if (isDeleted)
				return 2;

			// 한글자 빼봤을 때 회문이면
			if (isPalin(start + 1, end, true) == 0 || isPalin(start, end - 1, true) == 0)
				return 1;
			else
				return 2;

		}

		// 전부 검사 완료시 회문
		return 0;
	}

}
