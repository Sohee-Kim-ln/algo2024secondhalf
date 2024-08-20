package algo240820;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek2447 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		char[][] board = new char[N][N];

		int originr = 0;
		int originc = 0;
		int copylength = 1;
		board[originr][originc] = '*';

		// 프렉탈 복제
		while (copylength < N) {
			// 7번 복사 시작
			// 9칸으로 쪼갠 상태에서
			for (int i = 1; i <= 3; i++) {
				for (int j = 1; j <= 3; j++) {
					// 첫칸은 원본이므로 스킵
					if (i == 0 && j == 0)
						continue;

					// 가운데 칸이 아니라면 복사
					if (i != 2 || j != 2) {
						int nextr = copylength * (i - 1);
						int nextc = copylength * (j - 1);

						// 현재 구역에 첫구역 복사
						for (int r = 0; r < copylength; r++) {
							for (int c = 0; c < copylength; c++) {
								int curr = nextr + r;
								int curc = nextc + c;
								board[curr][curc] = board[originr + r][originc + c];
							}
						}
					}
				}
			}

			copylength *= 3;
		}

		// 출력 시작
		StringBuilder sb;

		for (int i = 0; i < N; i++) {
			sb = new StringBuilder();
			for (int j = 0; j < N; j++) {
				if (board[i][j] != '*')
					sb.append(' ');
				else
					sb.append(board[i][j]);
			}
				System.out.println(sb);
		}

	}
}
