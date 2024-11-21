package algo241121;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek17406 {
	static int N, M, K;
	static int[][] arrOrigin, order;
	static int masked = 0;
	static int min = Integer.MAX_VALUE;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[] mr = { -1, -1, 1, 1 };
	static int[] mc = { -1, 1, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		K = Integer.parseInt(stz.nextToken());
		arrOrigin = new int[N][M];
		order = new int[K][3];

		// 배열 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine());

			for (int j = 0; j < M; j++)
				arrOrigin[i][j] = Integer.parseInt(stz.nextToken());

		}

		// 회전 정보 받기
		for (int i = 0; i < K; i++) {
			stz = new StringTokenizer(bfr.readLine());

			order[i][0] = Integer.parseInt(stz.nextToken()) - 1;
			order[i][1] = Integer.parseInt(stz.nextToken()) - 1;
			order[i][2] = Integer.parseInt(stz.nextToken());
		}

		// 회전 백트래킹 수행
		backtracking(arrOrigin, 0);

		// 출력
		System.out.println(min);

	}

	static int[][] rotate(int[][] arr, int idx) {

		int[][] arrNew = new int[N][M];

		// 깊은 복사
		for (int i = 0; i < N; i++) {
			arrNew[i] = arr[i].clone();
		}

		// 회전 명령
		int r = order[idx][0];
		int c = order[idx][1];
		int s = order[idx][2];

		// 각 반지름 가지는 원에 대해
		for (int rad = 1; rad <= s; rad++) {
			// 각 4변에서 이동
			for (int dir = 0; dir < 4; dir++) {
				// 해당변 시작 꼭짓점
				int nr = r + rad * mr[dir];
				int nc = c + rad * mc[dir];

				// 꼭지점부터 해당 변 길이-1만큼 이동수행
				for (int i = 0; i < (rad << 1); i++) {
					arrNew[nr + (i + 1) * dr[dir]][nc + (i + 1) * dc[dir]] = arr[nr + i * dr[dir]][nc + i * dc[dir]];
				}
			}
		}

		return arrNew;
	}

	static void backtracking(int[][] arr, int mask) {

		// 전부 사용시 종료
		if (mask == (1 << K) - 1) {
			// 각 행의 최소값 계산
			for (int i = 0; i < N; i++) {
				int sum = 0;

				for (int j = 0; j < M; j++)
					sum += arr[i][j];

				min = Math.min(min, sum);
			}
			return;
		}

		// 아직 사용하지 않은 명령 수행
		for (int i = 0; i < K; i++) {
			// 사용여부 확인
			if ((mask & 1 << i) != 0)
				continue;

			backtracking(rotate(arr, i), mask | (1 << i));
		}
	}
}
