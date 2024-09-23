package algo240923;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baej9663 {
	static int res = 0;
	static boolean[] ccan, rdcan, ldcan;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());

		// 행, 열 가능여부 저장
		ccan = new boolean[N];

		// 우하 대각선, 좌하 대각선 가능여부 저장
		rdcan = new boolean[2 * N - 1];
		ldcan = new boolean[2 * N - 1];

		// r=0부터 고려시작
		queen(0);

		System.out.println(res);

	}

	// r행에 퀸 놓는 함수
	static void queen(int r) {
		// 끝까지 다 고려한 경우 종료
		if (r >= N) {
			res++;
			return;
		}

		for (int c = 0; c < N; c++) {
			if (isPossible(r, c)) {
				ccan[c] = true;
				rdcan[c - r + N - 1] = true;
				ldcan[r + c] = true;

				queen(r + 1);

				// 백트래킹
				ccan[c] = false;
				rdcan[c - r + N - 1] = false;
				ldcan[r + c] = false;
			}

		}

	}

	// r,c가 가능한지 판별함수
	static boolean isPossible(int r, int c) {
		if (ccan[c] || rdcan[c - r + N - 1] || ldcan[r + c])
			return false;
		else
			return true;
	}

}
