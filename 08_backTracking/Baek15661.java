package algo241128;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek15661 {
	static int N;
	static int[][] score;
	static int mask = 0;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;

		N = Integer.parseInt(bfr.readLine());
		score = new int[N][N];

		// 능력치 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine());

			for (int j = 0; j < N; j++)
				score[i][j] = Integer.parseInt(stz.nextToken());
		}

		// 스타트팀에 0번 선수 항상 추가
		mask |= 1 << 0;

		// 1번 선수부터 백트래킹
		backtracking(1);

		System.out.println(min);

	}

	static void backtracking(int idx) {
		// 전부 판별했으면
		if (idx == N) {
			// 한쪽 팀에 몰렸으면 종료
			if (mask == (1 << N) - 1)
				return;

			int teamS = 0;
			int teamL = 0;

			// 스타트팀 점수 계산
			for (int i = 0; i < N; i++) {
				// i가 선택된 선수면
				for (int j = i + 1; j < N; j++) {
					// i 선택됨, j선택됨
					if ((mask & (1 << i)) != 0) {
						if ((mask & (1 << j)) != 0)
							teamS += score[i][j] + score[j][i];
					}
					// j가 선택된 선수면
					else {
						if ((mask & (1 << j)) == 0)
							teamL += score[i][j] + score[j][i];
					}
				}
			}

			// 최소값 갱신
			min = Math.min(min, Math.abs(teamS - teamL));
			return;
		}

		// 해당 팀원을 고르지 않았을 때
		backtracking(idx + 1);

		// 해당 팀원을 골랐을 때
		mask |= 1 << idx;
		backtracking(idx + 1);
		mask ^= 1 << idx;

	}
}
