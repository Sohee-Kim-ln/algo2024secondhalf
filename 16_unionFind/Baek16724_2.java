package algo241009;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 그룹 저장을 좌표 저장에서 순번 저장으로 변경
// 메모리 25856KB, 시간 240ms
public class Baek16724_2 {
	static int[][] board;
	static int[] parent;
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
		parent = new int[N * M];

		// 화살표 정보 받기. 0123 상하좌우
		for (int i = 0; i < N; i++) {
			String temp = bfr.readLine();
			for (int j = 0; j < M; j++) {
				parent[i * M + j] = i * M + j;

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
				union(i, j);
			}
		}

		System.out.println(cnt);
	}

	static void union(int r, int c) {

		int now = find(r * M + c);
		int next = find((r + dr[board[r][c]]) * M + c + dc[board[r][c]]);

		if (now == next)
			return;

		parent[now] = next;
		cnt--;
	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}
}
