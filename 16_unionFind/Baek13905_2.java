package algo241107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek13905_2 {
	static int[] parent;
	static int N, M, S, E, res;
	static PriorityQueue<Bridge> pq;

	static class Bridge implements Comparable<Bridge> {
		int a, b, w;

		Bridge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Bridge o) {
			return o.w - this.w;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		// 집수, 다리수
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		res = 0;

		stz = new StringTokenizer(bfr.readLine());

		// 시작 위치, 도착 위치
		S = Integer.parseInt(stz.nextToken());
		E = Integer.parseInt(stz.nextToken());

		// 연결 정보 저장용
		pq = new PriorityQueue<>();

		// 연결된 부모
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		// 다리 정보 받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine());

			int front = Integer.parseInt(stz.nextToken());
			int rear = Integer.parseInt(stz.nextToken());
			int weight = Integer.parseInt(stz.nextToken());

			pq.add(new Bridge(front, rear, weight));

		}

		// 큰 다리부터 union
		while (!pq.isEmpty()) {
			Bridge now = pq.poll();

			// 현재 꺼낸 다리에 연결된 섬 union
			if (find(now.a) != find(now.b))
				union(now.a, now.b);

			// 연결됐으면 탐색 종료
			if (find(S) == find(E)) {
				res = now.w;
				break;
			}
		}

		// 출력
		System.out.println(res);

	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		parent[a] = b;
	}
}
