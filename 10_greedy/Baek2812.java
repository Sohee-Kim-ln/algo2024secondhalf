package algo240808;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek2812 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String[] tempS = bfr.readLine().split(" ");

		int N = Integer.parseInt(tempS[0]);
		int K = Integer.parseInt(tempS[1]);

		Stack<Character> st = new Stack<>();

		// 숫자배열
		String nums = bfr.readLine();
		int poped = 0;

		// 아랫자리부터 탐색
		for (int i = 0; i < nums.length(); i++) {
			while (!st.isEmpty()) {
				if (poped < K && st.peek() < nums.charAt(i)) {
					st.pop();
					poped++;
				}
			}

			if (st.size() < N - K) {
				st.add(nums.charAt(i));
			}
//			System.out.println(st.toString());
		}

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < st.size(); i++) {
			sb.append(st.get(i));
		}

		System.out.println(sb);

	}
}
