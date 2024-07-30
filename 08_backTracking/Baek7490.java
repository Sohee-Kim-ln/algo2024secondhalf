package algo240730;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek7490 {
	static int[] arr;
	static int N;
	static StringBuilder sbAll;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());
		sbAll = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {

			N = Integer.parseInt(bfr.readLine());

			// 연산자 저장 배열. 0, N+1에 + 표시
			arr = new int[N + 1];
			// 맨앞뒤에 + 처리
			arr[0] = 2;
			arr[N] = 2;

			choice(1);
			if(tc!=T-1)
				sbAll.append("\n");

		}
		sbAll.replace(sbAll.length() - 1, sbAll.length(), "");
		System.out.print(sbAll);
	}

	static void choice(int input) {
		// 끝까지 다 골랐으면
		if (input == N) {

			int result = 0;
			int prevNum = 1;
			int prevArr = 2;
			int curr = 1;

			while (curr <= N) {
				// 현재가 공백이 아니면 이전 연산 처리
				if (arr[curr] != 1) {
					// +
					if (prevArr == 2)
						result += prevNum;
					// -
					else if (prevArr == 3)
						result -= prevNum;

					prevNum = curr + 1;
					prevArr = arr[curr];
				}
				// 현재가 공백이면
				else if (arr[curr] == 1) {
					prevNum = prevNum * 10 + curr + 1;

				}

				curr++;
			}

			StringBuilder sb = new StringBuilder();

			// 결과가 0일 때 출력
			if (result == 0) {
				sb.append(1);
				for (int i = 1; i < N; i++) {
					if (arr[i] == 1)
						sb.append(" ");
					else if (arr[i] == 2)
						sb.append("+");
					else if (arr[i] == 3)
						sb.append("-");
					sb.append(i + 1);
				}

				sbAll.append(sb).append("\n");
			}
			return;
		}

		// 공백
		arr[input] = 1;
		choice(input + 1);

		// +
		arr[input] = 2;
		choice(input + 1);

		// -
		arr[input] = 3;
		choice(input + 1);

	}
}
