package algo240813;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek15900 {
	static List<Integer>[] tree;
	static int N;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());
		StringTokenizer stz;

		// 0안씀
		tree = new List[N + 1];
		visited = new boolean[N + 1];

		// 트리 초기선언
		for (int i = 1; i <= N; i++)
			tree[i] = new ArrayList<Integer>();

		// 노드 정보 저장
		for (int i = 1; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			int prev = Integer.parseInt(stz.nextToken());
			int next = Integer.parseInt(stz.nextToken());
			tree[prev].add(next);
			tree[next].add(prev);
		}

		int ans = dfs(1);

		// 합이 홀수일 때 성원 승리
		if (ans % 2 != 0)
			System.out.println("Yes");
		// 합이 짝수일 때 형섭 승리
		else
			System.out.println("No");

	}

	static int dfs(int start) {
		int sum = 0;

		// dfs용 큐
		Queue<int[]> quu = new LinkedList<>();

		// 초기값 넣기
		visited[start] = true;
		quu.add(new int[] { start, 0 });

		// dfs 시작
		while (!quu.isEmpty()) {
			int[] cur = quu.poll();
			int num = cur[0];
			int depth = cur[1];

			// 리프 확인 플래그
			boolean isLeaf = true;

			// 자식노드 확인
			for (int child : tree[num]) {
				if (visited[child])
					continue;

				isLeaf = false;
				visited[child] = true;
				quu.add(new int[] { child, depth + 1 });
			}

			if (isLeaf)
				sum += depth;
		}

		return sum;
	}
}