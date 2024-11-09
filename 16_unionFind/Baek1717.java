package algo241109;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1717 {
	static int[] parent;
	static int N, M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		// 집합 수, 명령 수
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		// 명령 M개 수행
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine());
			boolean doUnion = stz.nextToken().charAt(0) == '0';
			int a = Integer.parseInt(stz.nextToken());
			int b = Integer.parseInt(stz.nextToken());

			if (doUnion)
				union(a, b);
			else
				sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");

		}

		// 출력
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
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
