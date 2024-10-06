package algo241006;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

//스택 사용, 88052KB 336ms
public class Baek9935 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String origin = bfr.readLine();
		String bomb = bfr.readLine();

		int N = origin.length();
		int M = bomb.length();

		Stack<Character> st = new Stack<>();

		// 문자열 전체 탐색
		for (int i = 0; i < N; i++) {
			char now = origin.charAt(i);
			st.add(now);

			// 마지막 글자와 같고 길이가 M 이상이면
			if (now == bomb.charAt(M - 1) && st.size() >= M) {
				boolean isBomb = true;

				// 폭탄인지 확인
				for (int j = 0; j < M; j++) {
					// 폭탄이 아니면 표시 후 break;
					if (st.get(st.size() + j - M) != bomb.charAt(j)) {
						isBomb = false;
						break;
					}
				}

				// 폭탄 제거
				if (isBomb)
					st.setSize(st.size() - M);

			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();

		for (Character ch : st)
			sb.append(ch);

		System.out.println(sb.length() != 0 ? sb : "FRULA");
	}
}
