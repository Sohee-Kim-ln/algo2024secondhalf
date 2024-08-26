package algo240826;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1074 {
	// z모양 탐색용 델타변수
	static int[] dr = { 0, 0, 1, 1 };
	static int[] dc = { 0, 1, 0, 1 };

	static int N, R, C, ans;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		N = Integer.parseInt(stz.nextToken());
		R = Integer.parseInt(stz.nextToken());
		C = Integer.parseInt(stz.nextToken());

		int length = 1 << N;

		cut(length, 0, 0, 0);

		System.out.println(ans);

	}

	static void cut(int length, int startr, int startc, int score) {
		if (length == 1) {
			ans = score;
			return;
		}

		int half = length >> 1;

		// z자형 탐색
		for (int i = 0; i < 4; i++) {
			int nextr = startr + half * dr[i];
			int nextc = startc + half * dc[i];

			// 현재 범위 안에 R,C가 없으면 다음 범위 탐색
			if (nextr + half - 1 < R || nextc + half - 1 < C)
				continue;
			// 해당하는 범위라면 범위 쪼개기 시작
			else {
				cut(half, nextr, nextc, score + half * half * i);
				break;
			}
		}
	}
}
