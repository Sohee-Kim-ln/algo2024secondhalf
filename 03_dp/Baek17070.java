package algo241001;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek17070 {
	static int N;
	static boolean[][] isBlank;
	static boolean[][][] completed;
	static int[][][] cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bfr.readLine());

		isBlank = new boolean[N + 2][N + 2];
		completed = new boolean[N + 2][N + 2][3];
		cnt = new int[N + 2][N + 2][3];

		// 집 상태 받기
		for (int i = 1; i <= N; i++) {
			char[] tempC = bfr.readLine().replace(" ", "").toCharArray();
			for (int j = 1; j <= N; j++) {
				if (tempC[j - 1] == '0')
					isBlank[i][j] = true;
				else
					isBlank[i][j] = false;
			}
		}

		// 초기값 설정
		cnt[1][2][0] = 1;
		completed[1][2][0] = true;

		System.out.println(dp(N, N, 0) + dp(N, N, 1) + dp(N, N, 2));

	}

	// 0가로 1세로 2대각선
	// dp(r,c,0) = dp(r  ,c-1, 0 2)
	// dp(r,c,1) = dp(r-1,c  , 1 2)
	// dp(r,c,2) = dp(r-1,c-1, 0 1 2)

	// dp용 델타변수
	static int[] dr = { 0, -1, -1 };
	static int[] dc = { -1, 0, -1 };

	static int dp(int r, int c, int dir) {

		// 이미 계산된 결과 반환
		if (completed[r][c][dir])
			return cnt[r][c][dir];

		boolean isOver = false;

		if (!isBlank[r][c])
			isOver = true;

		// 방향에 따른 이전칸 계산
		int prevr = r + dr[dir];
		int prevc = c + dc[dir];

		int sum = 0;

		// 경계조건. 0반환 트리거 켜기
		if (prevr < 1 || prevr > N || prevc < 1 || prevc > N)
			isOver = true;

		// 이전칸이 벽이면 0반환 트리거 켜기
		if (!isBlank[prevr][prevc])
			isOver = true;

		// 대각으로 와야하는데 필수칸이 비어있지 않으면 트리거 켜기
		if (dir == 2 && (!isBlank[r - 1][c] || !isBlank[r][c - 1]))
			isOver = true;

		// 방향에 따라 이전칸에서 오는 루트 더하기
		if (!isOver) {
			if (dir != 1)
				sum += dp(prevr, prevc, 0);
			if (dir != 0)
				sum += dp(prevr, prevc, 1);

			sum += dp(prevr, prevc, 2);
		}

		cnt[r][c][dir] = sum;
		completed[r][c][dir] = true;
		return sum;
	}
}
