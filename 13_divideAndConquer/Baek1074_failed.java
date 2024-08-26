package algo240826;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 초과
public class Baek1074_failed {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		int N = Integer.parseInt(stz.nextToken());
		int R = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());

		int length = 1 << N;

		int[][] board = new int[length][length];

		int cnt = 0;
		int z = 0;

		// 첫칸 입력
		board[0][0] = cnt++;

		// z모양 탐색용 델타변수
		int[] dr = { 0, 0, 1, 1 };
		int[] dc = { 0, 1, 0, 1 };

		while (z < N) {
			// 원본 위치인 0,0 제외 탐색
			for (int i = 1; i < 4; i++) {
				int r = dr[i] * (1 << z);
				int c = dc[i] * (1 << z);
//				r,c+(2<<z);
//				r+(2<<z),c;
//				r+(2<<z),c+(2<<z);

				for (int j = 0; j < (1 << z); j++) {
					for (int k = 0; k < (1 << z); k++) {
						board[r + j][c + k] = board[j][k] + i * ((1 << z) << z);
					}

				}

				if (board[R][C] != 0) {
					z = N;
					break;
				}

			}
			z++;

		}

		System.out.println(board[R][C]);

	}
}
