package algo240903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 미완
public class Baek3665_3 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer stz;

		// 각 케이스에 대해
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(bfr.readLine());
			int[] lastYear = new int[N + 1];
			int[] thisYear = new int[N + 1];
			int[] degreeIn = new int[N + 1];
			boolean[][] win = new boolean[N + 1][N + 1];

			boolean isPossible = true;

			// 작년 정보 받기
			stz = new StringTokenizer(bfr.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				lastYear[i] = Integer.parseInt(stz.nextToken());
				degreeIn[lastYear[i]] = i - 1;

				for (int j = 1; j < i; j++) {
					win[lastYear[j]][lastYear[i]] = true;
				}
			}

			// 올해 순위 바뀐 정보 받기
			int M = Integer.parseInt(bfr.readLine());

			for (int i = 0; i < M; i++) {
				stz = new StringTokenizer(bfr.readLine(), " ");
				int prev = Integer.parseInt(stz.nextToken());
				int next = Integer.parseInt(stz.nextToken());

				int winner = win[prev][next] ? next : prev;
				int loser = win[prev][next] ? prev : next;

				degreeIn[winner]--;
				degreeIn[loser]++;
			}

			// 올해 순위 계산
			for (int i = 1; i <= N; i++) {
				if (thisYear[degreeIn[i] + 1] != 0) {
					isPossible = false;
					break;
				}
				thisYear[degreeIn[i] + 1] = i;
			}

			// 출력
			if (isPossible) {
				for (int i = 1; i <= N; i++)
					sb.append(thisYear[i]).append(" ");
				sb.setLength(sb.length() - 1);
				sb.append("\n");
			} else
				sb.append("IMPOSSIBLE\n");
		}
		System.out.println(sb);

	}

}
