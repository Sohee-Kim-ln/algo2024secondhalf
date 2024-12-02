package algo241202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek1707 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 테케 갯수
		int K = Integer.parseInt(bfr.readLine());
		StringTokenizer stz;
		StringBuilder sb = new StringBuilder();

		// 각 테케에 대해
		for (int tc = 0; tc < K; tc++) {
			// 그래프 정보 받기
			stz = new StringTokenizer(bfr.readLine());
			int V = Integer.parseInt(stz.nextToken());
			int E = Integer.parseInt(stz.nextToken());

			LinkedList<Integer>[] from = new LinkedList[V + 1];
			for (int i = 0; i < V + 1; i++)
				from[i] = new LinkedList<>();

			// 0 방문안함 1 빨강 2 검정
			int[] color = new int[V + 1];

			boolean isBG = true;

			// 간선 정보 받기
			for (int i = 0; i < E; i++) {
				stz = new StringTokenizer(bfr.readLine());
				int left = Integer.parseInt(stz.nextToken());
				int right = Integer.parseInt(stz.nextToken());

				from[left].add(right);
				from[right].add(left);

			}

			// BFS
			Queue<Integer> quu = new LinkedList<>();

			// 모든 정점에 대해 확인
			for (int i = 1; i < V + 1; i++) {
				if (!isBG)
					break;

				if (color[i] == 0) {
					quu.add(i);
					color[i] = 1;

					// BFS
					while (!quu.isEmpty() && isBG) {
						int now = quu.poll();

						// 연결된 정점들 색깔 판단
						for (int next : from[now]) {
							// 이미 방문되었고 같은 색이 나왔다면 이분X
							if (color[now] == color[next]) {
								isBG = false;
								break;
							}

							// 방문되지 않았다면 색 저장 후 큐에 추가
							if (color[next] == 0) {
								color[next] = color[now] == 1 ? 2 : 1;
								quu.add(next);
							}
						}
					}
				}
			}

			// 해당 테케 결과 저장
			sb.append(isBG ? "YES" : "NO").append('\n');
		}

		// 출력
		System.out.print(sb);

	}
}
