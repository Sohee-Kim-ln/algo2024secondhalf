package algo240910;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek9328 {
	// 4방 탐색용 델타변수 없우좌상하
	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };
	static int[] reverse = { 0, 2, 1, 4, 3 };
	static boolean isOver = false;

	static class Node {
		int r, c;
		int[] state;
		int color; // 0 흰, 1빨강, 2파랑
		boolean isReversed = false;
		int last = -1;

		Node(int r, int c, int color) {
			this.r = r;
			this.c = c;
			this.color = color;
			this.state = new int[5];

		}

		@Override
		public String toString() {
			return Arrays.toString(state) + " 마지막idx " + this.last;
		}
	}

	static int N, K;
	static int[][] chessman;
	static Node[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());

		// 각 체스별로 r,c,이동방향 우좌상하1234, 해당 지점에서의 순서,
		chessman = new int[K + 1][4];
		board = new Node[N + 2][N + 2];

		// 체스판 정보 받기
		for (int i = 0; i < N + 2; i++) {
			if (i != 0 && i != N + 1)
				stz = new StringTokenizer(bfr.readLine(), " ");

			for (int j = 0; j < N + 2; j++) {
				if (i == 0 || j == 0 || i == N + 1 || j == N + 1)
					board[i][j] = new Node(i, j, 2);
				else
					board[i][j] = new Node(i, j, Integer.parseInt(stz.nextToken()));
			}
		}

		// 체스말 정보 받기
		for (int i = 1; i <= K; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			chessman[i][0] = Integer.parseInt(stz.nextToken());
			chessman[i][1] = Integer.parseInt(stz.nextToken());
			chessman[i][2] = Integer.parseInt(stz.nextToken());
			chessman[i][3] = 0;
			board[chessman[i][0]][chessman[i][1]].state[0] = i;
			board[chessman[i][0]][chessman[i][1]].last = 0;
		}

		// 움직임 시작
		int cnt = 0;
		while (true) {
			// K개 말 순서대로 움직이기
			for (int i = 1; i <= K; i++) {
				// 다음칸 색에 따른 이동 실시
				move(i);

				if (isOver)
					break;
			}

			cnt++;
			
			if (isOver)
				break;

			if (cnt > 1000)
				break;

		}

		if (cnt > 1000)
			cnt = -1;

		System.out.println(cnt);

	}

	static void move(int idx) {
		int color = -1;

		int r = chessman[idx][0];
		int c = chessman[idx][1];

		// 다음칸 계산
		int nextr = r + dr[chessman[idx][2]];
		int nextc = c + dc[chessman[idx][2]];

		color = board[nextr][nextc].color;

		// 파란 색일때 방향 반전 후 다음칸 재계산
		if (color == 2) {
			chessman[idx][2] = reverse[chessman[idx][2]];
			nextr = chessman[idx][0] + dr[chessman[idx][2]];
			nextc = chessman[idx][1] + dc[chessman[idx][2]];

			color = board[nextr][nextc].color;
		}

		// 방향 반전 후 재계산한 칸이 파란색이면 그대로 종료
		if (color == 2) 
			return;
		

		Node cur = board[r][c];
		Node next = board[nextr][nextc];

		int nextIdx = next.last + 1;

		// 다음칸이 흰색일 때
		if (color == 0) {
			int lastNew = chessman[idx][3] - 1;
			for (int i = chessman[idx][3]; i <= cur.last; i++) {
				// 현재 말 번호
				int now = cur.state[i];

				// 다음칸에 저장
				next.state[nextIdx] = now;

				// 옮긴 체스말의 정보 갱신
				chessman[now][0] = nextr;
				chessman[now][1] = nextc;
				chessman[now][3] = nextIdx;

				nextIdx++;
			}
			// 현재칸 마지막 갱신
			cur.last = lastNew;
		}

		// 다음칸이 빨간색일 때
		if (color == 1) {
			int end = chessman[idx][3];
			// 위에서부터 거꾸로 읽어서 저장
			for (int i = cur.last; i >= end; i--) {

				// 현재 말 번호
				int now = cur.state[i];

				// 다음칸에 저장
				next.state[nextIdx] = now;

				// 옮긴 체스말의 정보 갱신
				chessman[now][0] = nextr;
				chessman[now][1] = nextc;
				chessman[now][3] = nextIdx;

				nextIdx++;

				cur.last--;
			}

		}

		// 전부 옮긴 후 다음칸의 마지막 갱신
		next.last = nextIdx - 1;

		// 말이 4개이상 쌓였으면 종료
		if (next.last >= 3) {
			isOver = true;
			return;
		}
	}

}
