package algo241003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

// List 및 유니온파인드 사용, 17788KB 160ms
public class Baek13023_3 {
	static int N, M;
	static boolean[] visited;
	static int[] parent, size;
	static List<Integer>[] linked, group;
	static boolean hasFive = false;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());

		visited = new boolean[N];
		parent = new int[N];
		size = new int[N];

		linked = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			linked[i] = new ArrayList<>();
			parent[i] = i;
			size[i] = 1;
		}

		// 연결 정보 받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			int front = Integer.parseInt(stz.nextToken());
			int rear = Integer.parseInt(stz.nextToken());

			union(front, rear);

			linked[front].add(rear);
			linked[rear].add(front);
		}

		// 모든 점에 대해 깊이 5가 가능한지 확인
		for (int i = 0; i < N; i++) {
			// 그룹 수가 5미만이면 제외
			if (size[parent[i]] < 5)
				continue;

			visited[i] = true;
			checkFive(i, 1);
			visited[i] = false;

			if (hasFive)
				break;
		}

		// 출력
		if (hasFive)
			System.out.println(1);
		else
			System.out.println(0);

	}

	static void checkFive(int i, int depth) {

		if (depth == 5) {
			hasFive = true;
			return;
		}

		// 연결된 곳에 대해
		for (int next : linked[i]) {
			// 이미 방문한 곳 continue
			if (visited[next])
				continue;

			// 백트래킹
			visited[next] = true;
			checkFive(next, depth + 1);
			visited[next] = false;

			// 찾았으면 종료
			if (hasFive)
				break;
		}
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b) {
			parent[b] = a;
			size[a] += size[b];
			size[b] = 0;
		}
	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}
}
