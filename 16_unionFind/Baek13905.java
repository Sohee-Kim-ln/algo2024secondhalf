package algo241107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baek13905 {
	static int[] parent;
	static int N, M, S, E, res;
	static int[][] bridge;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		// 집수, 다리수
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int res = 0;

		stz = new StringTokenizer(bfr.readLine());

		// 시작 위치, 도착 위치
		int S = Integer.parseInt(stz.nextToken());
		int E = Integer.parseInt(stz.nextToken());

		// 연결 정보 저장용, {섬1, 섬2,무게제한}
		bridge = new int[M][3];

		// 연결된 부모
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		// 다리 정보 받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine());

			bridge[i][0] = Integer.parseInt(stz.nextToken());
			bridge[i][1] = Integer.parseInt(stz.nextToken());
			bridge[i][2] = Integer.parseInt(stz.nextToken());

		}

		// 무게제한 내림차순으로 정렬
		Arrays.sort(bridge, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[2] - o1[2];

			}
		});

		// 큰 다리부터 union
		for (int[] now : bridge) {

			// 현재 꺼낸 다리에 연결된 섬 union
			if (find(now[0]) != find(now[1]))
				union(now[0], now[1]);

			// 연결됐으면 탐색 종료
			if (find(S) == find(E)) {
				res = now[2];
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
