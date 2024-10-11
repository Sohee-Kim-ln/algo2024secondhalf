package algo241010;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek3830 {
	static int N, M;
	static int[] parent, weight;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
			N = Integer.parseInt(stz.nextToken());
			M = Integer.parseInt(stz.nextToken());

			// 종료 플래그
			if (N == 0)
				break;

			weight = new int[N + 1];
			parent = new int[N + 1];
			for (int i = 1; i <= N; i++)
				parent[i] = i;

			// 진행
			for (int i = 0; i < M; i++) {
				stz = new StringTokenizer(bfr.readLine(), " ");
				String command = stz.nextToken();
				// 무게 비교 정보 저장
				if (command.equals("!")) {
					int a = Integer.parseInt(stz.nextToken());
					int b = Integer.parseInt(stz.nextToken());
					int w = Integer.parseInt(stz.nextToken());
					union(a, b, w);
				}
				// 대답하기
				else {
					int a = Integer.parseInt(stz.nextToken());
					int b = Integer.parseInt(stz.nextToken());

					// 같그룹 판단 후 출력
					if (find(a) == find(b))
						sb.append(weight[b] - weight[a]).append("\n");
					else
						sb.append("UNKNOWN").append("\n");
				}
			}
		}
		
		System.out.println(sb);

	}

	static void union(int a, int b, int w) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return;

		weight[pb] = weight[a] - weight[b] + w;

		parent[pb] = pa;
	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		int p = find(parent[a]);
		weight[a] += weight[parent[a]];

		return parent[a] = p;
	}
}
