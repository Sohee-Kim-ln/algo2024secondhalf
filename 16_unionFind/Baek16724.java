package algo241009;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//그룹 저장을 좌표 저장으로 한 상태
//메모리 97980KB, 시간 332ms
public class Baek16724 {
	static int[][] board;
	static int[][][] parent;
	static int N, M, cnt;

	// 4방 델타변수 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());

		cnt = N * M;

		board = new int[N][M];
		parent = new int[N][M][2];

		// 화살표 정보 받기. 0123 상하좌우
		for (int i = 0; i < N; i++) {
			String temp = bfr.readLine();
			for (int j = 0; j < M; j++) {
				parent[i][j][0] = i;
				parent[i][j][1] = j;

				if (temp.charAt(j) == 'D')
					board[i][j] = 1;
				else if (temp.charAt(j) == 'L')
					board[i][j] = 2;
				else if (temp.charAt(j) == 'R')
					board[i][j] = 3;
			}
		}

		// 전체 탐색하면서 그룹 묶기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				union(new int[] { i, j });
			}
		}

		System.out.println(cnt);
	}

	static void union(int[] now) {
		int r = now[0];
		int c = now[1];
		int[] next = new int[] { r + dr[board[r][c]], c + dc[board[r][c]] };

		now = find(now);
		next = find(next);

		if (now[0] == next[0] && now[1] == next[1])
			return;

		parent[r][c] = next;
		cnt--;
	}

	static int[] find(int[] input) {
		int r = input[0];
		int c = input[1];

		if (r == parent[r][c][0] && c == parent[r][c][1])
			return input;

		return parent[r][c] = find(parent[r][c]);
	}
}
