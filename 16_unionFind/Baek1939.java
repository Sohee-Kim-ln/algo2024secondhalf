package algo241008;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1939 {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		int start, end, weight = 0;

		// 가중치 순으로 정렬하는 우선큐 {시작,끝,가중치}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[2], o1[2]));

		// 그룹정보 저장. 0안씀
		parent = new int[N + 1];

		for (int i = 1; i <= N; i++)
			parent[i] = i;

		// 연결정보 받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			int a = Integer.parseInt(stz.nextToken());
			int b = Integer.parseInt(stz.nextToken());
			int m = Integer.parseInt(stz.nextToken());
			pq.add(new int[] { a, b, m });

		}

		// 운행정보 받기
		stz = new StringTokenizer(bfr.readLine(), " ");

		start = Integer.parseInt(stz.nextToken());
		end = Integer.parseInt(stz.nextToken());

		// 연결될 때까지 반복
		while (find(start) != find(end)) {
			int[] temp = pq.poll();

			union(temp[0], temp[1]);
			weight = temp[2];
		}

		System.out.println(weight);

	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		parent[a] = b;
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = find(parent[a]);
	}
}
