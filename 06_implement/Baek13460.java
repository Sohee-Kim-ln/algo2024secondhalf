package algo240909;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek13460 {
	// 4방탐색용 델타변수 하상우좌
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int[] dirR = { 1, 0, 3, 2 };
	static int min = Integer.MAX_VALUE;
	static char[][] board;
	static int[] red, blue, hole;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		board = new char[N][M];
		red = new int[2];
		blue = new int[2];
		hole=new int[2];

		// 보드 정보 받기
		for (int i = 0; i < N; i++) {
			board[i] = bfr.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 'R') {
					red[0] = i;
					red[1] = j;
				}
				
				if (board[i][j] == 'B') {
					blue[0] = i;
					blue[1] = j;
				}
				
				if (board[i][j] == 'O') {
					hole[0] = i;
					hole[1] = j;
				}
			}
		}

		for (int dir = 0; dir < 4; dir++)
			tilt(dir, 1);

		if (min == Integer.MAX_VALUE)
			min = -1;

		System.out.println(min);

	}

	// 기울이기
	static void tilt(int dir, int cnt) {
		if (cnt > 10)
			return;

		boolean redFirst = false;
		boolean redGoaled = false;
		boolean blueGoaled = false;
		int[] redOrigin = { red[0], red[1] };
		int[] blueOrigin = { blue[0], blue[1] };

		// 방향에 따라 먼저 움직일 구슬 선택 하상우좌
		if (dir == 0 && red[0] > blue[0])
			redFirst = true;
		if (dir == 1 && red[0] < blue[0])
			redFirst = true;
		if (dir == 2 && red[1] > blue[1])
			redFirst = true;
		if (dir == 3 && red[1] < blue[1])
			redFirst = true;

		// 구슬 2개 움직이기
		if (redFirst) {
			redGoaled = move(true, dir);
			blueGoaled = move(false, dir);
		} else {
			blueGoaled = move(false, dir);
			redGoaled = move(true, dir);
		}

		// 둘다 안들어갔으면 다음 움직임 시작
		if (!redGoaled && !blueGoaled) {
			// 4방에 대해 기울이기 탐색 시작
			for (int dirNext = 0; dirNext < 4; dirNext++) {
				// 이미 기울인 방향은 스킵
				if (dir == dirNext)
					continue;

				// 방금 움직인 걸 되돌리는 방향도 스킵
				if (dirR[dir] == dirNext)
					continue;

				tilt(dirNext, cnt + 1);
			}
		}

		// 빨간 구슬만 들어가면 min 최소로 갱신
		if (redGoaled && !blueGoaled) {
			min = Math.min(min, cnt);
		}

		// 파란구슬 들어갔다면 아무 행동 안함

		// 백트래킹용 원상복구 후 종료
		board[red[0]][red[1]] = '.';
		board[blue[0]][blue[1]] = '.';
		red[0] = redOrigin[0];
		red[1] = redOrigin[1];
		blue[0] = blueOrigin[0];
		blue[1] = blueOrigin[1];
		board[red[0]][red[1]] = 'R';
		board[blue[0]][blue[1]] = 'B';
		board[hole[0]][hole[1]] = 'O';
		return;
	}

	// 구슬 움직이기
	static boolean move(boolean isRed, int dir) {
		boolean isGoaled = false;
		int r = isRed ? red[0] : blue[0];
		int c = isRed ? red[1] : blue[1];

		int nextr, nextc;

		while (true) {
			nextr = r + dr[dir];
			nextc = c + dc[dir];

			// 경계조건
			if (nextr < 1 || nextr > N - 2 || nextc < 1 || nextc > M - 2)
				break;

			// 장애물 만났을 때
			if (board[nextr][nextc] == '#')
				break;
			if (board[nextr][nextc] == 'R')
				break;
			if (board[nextr][nextc] == 'B')
				break;

			r = nextr;
			c = nextc;

			// 구멍에 들어갔을 때
			if (board[nextr][nextc] == 'O') {
				isGoaled = true;
				break;
			}

		}

		// 구슬 움직임 저장
		if (isRed) {
			board[red[0]][red[1]] = '.';
			red[0] = r;
			red[1] = c;

			if (!isGoaled)
				board[red[0]][red[1]] = 'R';
		}

		if (!isRed) {
			board[blue[0]][blue[1]] = '.';
			blue[0] = r;
			blue[1] = c;
			if (!isGoaled)
				board[blue[0]][blue[1]] = 'B';
		}

		return isGoaled;
	}
}
