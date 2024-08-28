package algo240828;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2630 {
	static int N;
	static int[][] paper;
	static int[] cnt = new int[2];

	// 사분면들 탐색용 델타변수. 좌상 우상 좌하 우하
	static int[] dr = { 0, 0, 1, 1 };
	static int[] dc = { 0, 1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());
		paper = new int[N][N];

		int sum = 0;

		// 종이 정보 받기
		StringTokenizer stz;
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(stz.nextToken());
				sum += paper[i][j];
			}
		}

		// 종이가 전부 같은 색이면 자르지 않음
		if (sum == N * N || sum == 0)
			cnt[paper[0][0]]++;
		else
			cut(0, 0, N);

		System.out.println(cnt[0]);
		System.out.println(cnt[1]);

	}

	// 자르기
	static int cut(int startr, int startc, int length) {
		if (length == 1)
			return paper[startr][startc];

		int half = length >> 1;
		int[] sum = new int[3];

		// 사분면 4개 탐색
		for (int dir = 0; dir < 4; dir++) {
			int nextr = startr + half * dr[dir];
			int nextc = startc + half * dc[dir];

			int result = cut(nextr, nextc, half);

			sum[result]++;
		}

		// 4개 사분면 전부 흰색이거나 파란색일 경우 해당 색 표시 반환
		if (sum[0] == 4)
			return 0;

		if (sum[1] == 4)
			return 1;

		// 사분면 색이 다를 때 색 별 카운트 추가
		cnt[0] += sum[0];
		cnt[1] += sum[1];

		return 2;

	}
}
