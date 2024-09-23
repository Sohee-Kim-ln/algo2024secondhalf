package algo240923;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baej9663TimeOver {
	static int cntQ = 0;
	static int res = 0;
	static boolean[] rcan, ccan, rdcan, ldcan;
	static int N, prevr, prevc, square;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());

		// 행, 열 가능여부 저장
		rcan = new boolean[N];
		ccan = new boolean[N];

		// 우하 대각선, 좌하 대각선 가능여부 저장
		rdcan = new boolean[2 * N - 1];
		ldcan = new boolean[2 * N - 1];

		// 0번부터 고려 시작
		queen(0);

		System.out.println(res);

	}

	// num번을 놓은 상태와 안놓은 상태 나눠서 계산하는 함수
	static void queen(int num) {
		// N개 퀸 다 놨으면 카운트 후 종료
		if (cntQ == N) {
			res++;
			return;
		}

		// 끝까지 다 고려한 경우 종료
		if (num == N * N) {
			return;
		}

		// 순번으로 행열 계산
		int r = num / N;
		int c = num % N;

		// 퀸을 놓을 수 있다면
		if (!rcan[r] && !ccan[c] && !rdcan[c - r + N - 1] && !ldcan[r + c]) {
			// 현재칸 표시
			rcan[r] = true;
			ccan[c] = true;
			rdcan[c - r + N - 1] = true;
			ldcan[r + c] = true;
			cntQ++;

			// 현재칸 놓은 상태로 다음칸 고려

			queen(num + 1);

			// 백트래킹
			rcan[r] = false;
			ccan[c] = false;
			rdcan[c - r + N - 1] = false;
			ldcan[r + c] = false;
			cntQ--;

		}

		// 현재칸 놓지 않고 다음칸으로 넘어가기
		queen(num + 1);

	}

}
