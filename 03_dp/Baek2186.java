package algo240715;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 설계 21분
// 구현 1시간 10분 (최초 잘못된 풀이에 40분, 이후 재구현 30분)

public class Baek2186 {

	// 델타변수 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String[] tempS = bfr.readLine().split(" ");

		int N = Integer.parseInt(tempS[0]); // 보드판 크기 r
		int M = Integer.parseInt(tempS[1]); // 보드판 크기 c
		int K = Integer.parseInt(tempS[2]); // 이동 가능 거리

		// 문자판
		char[][] board = new char[N][M];

		for (int i = 0; i < N; i++) {
			board[i] = bfr.readLine().toCharArray();
		}

		// 타겟문자 받기
		char[] target = bfr.readLine().toCharArray();

		// dp[r][c][n] = r,c가 n번째인 경우의 수 dp[r][c][n] = sum of dp[prer][prec][n-1]
		int[][][] dp = new int[N][M][target.length];

		// 첫글자 dp 채우기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] == target[0]) {
					dp[r][c][0] = 1;
				}
			}
		}

		int orderNow = 1;
		int sum = 0;

		while (orderNow != target.length) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					// 목표하는 글자와 같으면
					if (board[r][c] == target[orderNow]) {
						// 4방탐색 시작
						for (int dir = 0; dir < 4; dir++) {
							// 이동해 올 수 있는 거리 내에서
							for (int len = 1; len <= K; len++) {
								int prevr = r + dr[dir] * len;
								int prevc = c + dc[dir] * len;

								// 경계조건
								if (prevr < 0 || prevr >= N || prevc < 0 || prevc >= M)
									continue;

								// 보드판에서 이전 글자와 같은 글자 찾으면
								if (board[prevr][prevc] == target[orderNow - 1]) {
									dp[r][c][orderNow] += dp[prevr][prevc][orderNow - 1];
								}
							}
						}
						// 마지막 글자라면 합에 계산결과 더함
						if (orderNow == target.length - 1)
							sum += dp[r][c][orderNow];
					}
				}
			}
			orderNow++;
		}

		System.out.println(sum);

	}

}
