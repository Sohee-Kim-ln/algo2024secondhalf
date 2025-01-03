package algo250103;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1197 {
	static int[] group;
	static int cnt, cost;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		// 그래프 정보
		int V = Integer.parseInt(stz.nextToken());
		int E = Integer.parseInt(stz.nextToken());

		cnt = V;
		cost = 0;

		// 유니온파인드용
		group = new int[V + 1];

		for (int i = 1; i <= V; i++)
			group[i] = i;

		// 가중치 작은 순 내림차순
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));

		// 간선 정보 받기
		for (int i = 0; i < E; i++) {
			stz = new StringTokenizer(bfr.readLine());

			int A = Integer.parseInt(stz.nextToken());
			int B = Integer.parseInt(stz.nextToken());
			int C = Integer.parseInt(stz.nextToken());

			pq.add(new int[] { A, B, C });
		}

		// 크루스칼 알고리즘. 간선 적은 것 부터  통합
		while (!pq.isEmpty() && cnt != 1) {
			int[] now = pq.poll();

			if (union(now[0], now[1])) {
				cnt--;
				cost += now[2];
			}

		}
		
		System.out.println(cost);
	}

	static int find(int a) {
		if (group[a] == a)
			return a;

		return group[a] = find(group[a]);
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b)
			return false;

		if (a < b)
			group[a] = b;
		else
			group[b] = a;
		
		return true;
	}
}
