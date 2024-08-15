package algo240815;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek10997 {
	// 좌하우상
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		int originc = 4 * (N - 1) + 1;
		int originr = originc == 1 ? 1 : originc + 2;

		char[][] board = new char[originr][originc];
		for (int i = 0; i < originr; i++) {
			Arrays.fill(board[i], ' ');

		}

		// 멈출 지점 계산
		int stopr = originr == 1 ? 0 : originr / 2 + 1;
		int stopc = originc / 2;

		// 행 열 진행 한계
		int c = originc + 1;
		int r = originr - 1;

		// 초기위치 및 방향
		int curr = 0;
		int curc = c;
		int dir = 0;

		int cnt = 0;

		while (curr != stopr || curc != stopc) {
			dir = dir % 4;
			// 좌우
			if (dir == 0 || dir == 2) {
				cnt = 0;

				while (cnt < c) {
					curc = curc + dc[dir];
					cnt++;
					if (curc >= originc)
						continue;

					board[curr][curc] = '*';

				}
				c -= 2;
				dir++;
			}
			// 하상
			else {
				cnt = 0;

				while (cnt < r) {
					curr = curr + dr[dir];

					board[curr][curc] = '*';
					cnt++;
				}
				r -= 2;
				dir++;
			}

		}

		// 출력

		for (int i = 0; i < originr; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = originc - 1; j >= 0; j--) {
				if (board[i][j] == ' ' && sb.length() == 0)
					continue;
				sb.append(board[i][j]);
			}
			System.out.println(sb.reverse());
		}

	}
}
