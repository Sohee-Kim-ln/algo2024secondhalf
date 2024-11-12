package algo241112;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek1738 {
	static class Road {
		int s, e, c;

		Road(int s, int e, int c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}
	}

	static final int INF = 20000 * 1000;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz;

		stz = new StringTokenizer(bfr.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		// cost 0안씀
		int[] cost = new int[N + 1];
		int[] prev = new int[N + 1];
		int[] length = new int[N + 1];
		// {시작, 끝, 비용}
		Road[] roads = new Road[M];

		ArrayList<Integer>[] before = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			before[i] = new ArrayList<>();
		}

		// 골목 정보 받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine());

			int U = Integer.parseInt(stz.nextToken());
			int V = Integer.parseInt(stz.nextToken());
			int W = Integer.parseInt(stz.nextToken());

			roads[i] = new Road(U, V, W);

			before[V].add(U);
		}

		boolean updated = false;

		// 최대값 갱신 벨만포드. N-1번 수행
		for (int i = 1; i < N; i++) {
			updated = false;

			for (Road now : roads) {
				if (cost[now.e] < cost[now.s] + now.c) {
					cost[now.e] = cost[now.s] + now.c;
					prev[now.e] = now.s;
					length[now.e] = length[now.s] + 1;
					updated = true;
				}
			}

			// 갱신 없으면 종료
			if (!updated)
				break;

		}

		// 사이클 추적용 방문표시 변수
		boolean[] inCycle = new boolean[N + 1];
		boolean hasCycle = false;

		// N-1업데이트 있으면 사이클부분 찾기
		if (updated) {
			for (Road now : roads) {
				// 갱신이 있으면 사이클 표시
				if (cost[now.e] < cost[now.s] + now.c) {
					inCycle[now.s] = true;
					inCycle[now.e] = true;
					hasCycle = true;
				}
			}

		}

		// 사이클이 있으면 N부터 DFS역추적으로 사이클에 닿는지 확인
		if (hasCycle) {
			boolean[] visited = new boolean[N + 1];

			Queue<Integer> quu = new LinkedList<>();
			quu.add(N);
			visited[N] = true;

			// DFS
			while (!quu.isEmpty()) {
				int polled = quu.poll();

				// 사이클에 닿았다면 종료
				if (inCycle[polled]) {
					System.out.println(-1);
					return;
				}

				// 이전 노드 탐색 후 추가
				for (int now : before[polled]) {
					if (visited[now])
						continue;

					quu.add(now);
					visited[now] = true;
				}
			}
		}

		// 목적지에 도달하지 못했으면 종료
		if (prev[N] == 0) {
			System.out.println(-1);
			return;
		}

		// 출력
		StringBuilder sb = new StringBuilder("1 ");

		// 스택용
		int[] res = new int[length[N] + 1];
		int idx = 0;
		int now = N;
		while (now != 1) {
			// 추적하는 루트 내에 사이클이 있으면 그대로 종료
			if (inCycle[now]) {
				System.out.println(-1);
				return;
			}

			res[idx++] = now;
			now = prev[now];

		}

		for (int i = length[N]-1; i >= 0; i--)
			sb.append(res[i]).append(' ');

		sb.setLength(sb.length() - 1);
		System.out.print(sb);

	}
}
