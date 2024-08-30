package algo240830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 플로이드 워셜
public class Baek2458_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int res = 0;

		// i번에서 연결된 번호 정보. 0안씀
		boolean[][] canVisit = new boolean[N + 1][N + 1];

		// 자기자신 방문가능
		for (int i = 1; i <= N; i++) {
			canVisit[i][i] = true;
		}

		// 선 정보 받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			int start = Integer.parseInt(stz.nextToken());
			int end = Integer.parseInt(stz.nextToken());

			canVisit[start][end] = true;
		}

		// 플로이드 워셜
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (canVisit[i][k] && canVisit[k][j])
						canVisit[i][j] = true;
				}
			}
		}

		// 모든 노드에 대해
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				// i로 들어올 수 있는 노드 + i에서 출발해서 갈 수 있는 노드 세기
				if (canVisit[i][j] || canVisit[j][i])
					cnt++;
			}

			// N-1개 노드 정보 알 때 순서 알 수 있음
			if (cnt == N - 1)
				res++;
		}

		System.out.println(res);
	}
}
