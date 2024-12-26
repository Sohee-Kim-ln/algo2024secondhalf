package algo241226;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2580 {
	static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new int[9][9];

		StringTokenizer stz;

		// 숫자판 정보 받기
		for (int i = 0; i < 9; i++) {
			stz = new StringTokenizer(br.readLine());

			for (int j = 0; j < 9; j++)
				board[i][j] = Integer.parseInt(stz.nextToken());
		}

		sudoku(0, 0);
	}

	static void sudoku(int r, int c) {
		// 해당 행 끝까지 탐색했으면
		if (c == 9) {
			sudoku(r + 1, 0);
			return;
		}

		// 마지막 행까지 탐색했으면
		if (r == 9) {
			// 결과 저장
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++)
					sb.append(board[i][j]).append(' ');
				sb.append('\n');
			}

			// 출력 및 종료
			System.out.print(sb);
			System.exit(0);
		}

		// 빈칸이면 가능한 수 탐색해서 넣기
		if (board[r][c] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (isValid(r, c, i)) {
					board[r][c] = i;
					sudoku(r, c + 1);
				}
			}

			// 백트래킹용 초기화
			board[r][c] = 0;
			return;
		}

		sudoku(r, c + 1);
	}

	static boolean isValid(int r, int c, int input) {
		// 행 및 열 탐색
		for (int i = 0; i < 9; i++) {
			if (board[r][i] == input)
				return false;

			if (board[i][c] == input)
				return false;
		}

		// 박스 탐색
		int rStart = (r / 3) * 3;
		int cStart = (c / 3) * 3;

		for (int i = rStart; i < rStart + 3; i++) {
			for (int j = cStart; j < cStart + 3; j++)
				if (board[i][j] == input)
					return false;
		}

		return true;
	}
}
