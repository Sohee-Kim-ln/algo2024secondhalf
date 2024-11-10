package algo241110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2610 {
	static int[] parent;
	static int N, M, K;
	static int[][] dist;
	static int[][] groupInfo;
	static final int MAX = 100;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 사람 수, 관계 수, 그룹 수
		N = Integer.parseInt(bfr.readLine());
		M = Integer.parseInt(bfr.readLine());
		K = N;

		// 플로이드 워셜용
		dist = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++)
			Arrays.fill(dist[i], MAX);

		// 유니온파인드용 그룹표시
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		// 관계 정보 받기
		StringTokenizer stz;
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine());
			int a = Integer.parseInt(stz.nextToken());
			int b = Integer.parseInt(stz.nextToken());

			dist[a][b] = 1;
			dist[b][a] = 1;

			union(a, b);
		}

		// 각 그룹에서 {그룹의 현재 거리합 최소, 해당시점의 대표}
		groupInfo = new int[N + 1][2];

		// 플로이드 워셜
		for (int mid = 1; mid <= N; mid++) {
			for (int start = 1; start <= N; start++) {
				for (int end = 1; end <= N; end++) {
					if (start == end)
						continue;

					if (dist[start][end] > dist[start][mid] + dist[mid][end])
						dist[start][end] = dist[start][mid] + dist[mid][end];
				}
			}
		}

		// 그룹별 대표 탐색
		for (int i = 1; i <= N; i++) {
			int maxNow = 0;
			int root = find(i);

			// i번이 해당그룹 대표일 때 거리 최대 계산
			for (int j = 1; j <= N; j++)
				if (i != j && root == find(j)) {
					maxNow = Math.max(maxNow, dist[i][j]);
				}

			// 기록 없거나 최소일 때 갱신
			if (groupInfo[root][0] == 0 || groupInfo[root][0] > maxNow) {
				groupInfo[root][0] = maxNow;
				groupInfo[root][1] = i;
			}
		}

		// 대표 번호순으로 정렬
		Arrays.sort(groupInfo, (o1, o2) -> Integer.compare(o1[1], o2[1]));

		// 출력
		StringBuilder sb = new StringBuilder();

		sb.append(K).append("\n");

		// 오름차순 정렬로 뒤에서 N-K+1부터 N까지가 대표있는 그룹
		for (int i = N - K + 1; i <= N; i++)
			sb.append(groupInfo[i][1]).append("\n");

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

		if (a != b) {
			K--;
			parent[a] = b;
		}

	}
}
