package algo240831;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1613 {

	static int inf = 500;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());

		int prev, next, res; // 임시변수

		// 전후관계 플로이드 저장용 배열
		int[][] event = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(event[i], inf);
			event[i][i] = 0;
		}

		// 사건 전후관계 정보 받기
		for (int i = 0; i < K; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			prev = Integer.parseInt(stz.nextToken());
			next = Integer.parseInt(stz.nextToken());

			event[prev][next] = 1;
		}

		// 플로이드 워셜
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (event[i][j] > event[i][k] + event[k][j])
						event[i][j] = event[i][k] + event[k][j];
				}
			}
		}

		// 두 사건 관계 출력
		int S = Integer.parseInt(bfr.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < S; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			prev = Integer.parseInt(stz.nextToken());
			next = Integer.parseInt(stz.nextToken());

			res = 0;

			if (event[prev][next] != inf)
				res = -1;
			if (event[next][prev] != inf)
				res = 1;

			sb.append(res).append("\n");

		}

		System.out.print(sb);
	}
}
