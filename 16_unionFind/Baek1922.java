package algo241229;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1922 {
	static int N, M, group;
	static int[] parent;
	static int cost = 0;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());
		M = Integer.parseInt(bfr.readLine());
		group = N;

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		// {a, b, c}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

		// 연결 비용 정보 받기
		for (int i = 0; i < M; i++) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine());
			int A = Integer.parseInt(stz.nextToken());
			int B = Integer.parseInt(stz.nextToken());

			if (A == B)
				continue;

			int C = Integer.parseInt(stz.nextToken());

			pq.add(new int[] { A, B, C });
		}

		while (!pq.isEmpty()) {
			int[] now = pq.poll();

			// 같은 그룹이 아니면 합치기
			if (find(now[0]) != find(now[1])) {
				union(now[0], now[1]);
				cost += now[2];

				if (group == 1)
					break;
			}
		}

		System.out.println(cost);
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		parent[a] = b;
		group--;

	}
}
