package algo241020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek13549pq {
	static int N, K, maxTime;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		N = Integer.parseInt(stz.nextToken());
		K = Integer.parseInt(stz.nextToken());
		maxTime = Math.max(K, N) + 2;

		// 도달 시간 저장 배열
		int[] time = new int[maxTime + 1];
		Arrays.fill(time, Integer.MAX_VALUE);

		boolean[] visited = new boolean[maxTime + 1];

		// {현재위치, 시간} 이동 오름차순;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

		// 초기화
		time[N] = 0;
		pq.add(new int[] { N, 0 });

		int next = -1;

		// 다익스트라 탐색
		while (!pq.isEmpty()) {
			int[] edge = pq.poll();
			int now = edge[0];

			// 종료시점 확인
			if (now == K)
				break;

			// 방문 확인
			if (visited[now])
				continue;

			visited[now] = true;

			// 순간이동 시 최소시간 갱신
			next = now << 1;
			if (isValid(next) && time[next] > time[now]) {
				time[next] = time[now];
				pq.add(new int[] { next, time[next] });
			}

			// -1 시 최소시간 갱신
			next = now - 1;
			if (isValid(next) && time[next] > time[now] + 1) {
				time[next] = time[now] + 1;
				pq.add(new int[] { next, time[next] });

			}

			// +1 시 최소시간 갱신
			next = now + 1;
			if (isValid(next) && time[next] > time[now] + 1) {
				time[next] = time[now] + 1;
				pq.add(new int[] { next, time[next] });
			}

		}

		// 출력
		System.out.println(time[K]);

	}

	static boolean isValid(int pos) {
		if (pos >= 0 && pos <= maxTime)
			return true;
		else
			return false;
	}
}