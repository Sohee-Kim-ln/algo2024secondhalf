package algo241011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek16562 {
	static int N, M, K, sum;
	static int[] money, parent;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		K = Integer.parseInt(stz.nextToken());

		money = new int[N + 1];
		parent = new int[N + 1];
		visited = new boolean[N + 1];

		sum = 0;

		// 비용 정보 받기
		stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			money[i] = Integer.parseInt(stz.nextToken());
		}

		// 친구 정보 받기

		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			int a = Integer.parseInt(stz.nextToken());
			int b = Integer.parseInt(stz.nextToken());

			union(a, b);
		}

		for (int i = 1; i <= N; i++) {
			int now = find(i);

			if (visited[now])
				continue;

			sum += money[now];
			visited[now] = true;
		}

		System.out.println(sum <= K ? sum : "Oh no");

	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (money[a] < money[b])
			parent[b] = a;
		else
			parent[a] = b;

	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}
}
