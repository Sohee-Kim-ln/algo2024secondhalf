package algo240830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// bfs. 플로이드워셜에 비해 큐의 추가삭제로 인해 시간이 길어짐
public class Baek2458_3 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int res = 0;

		// i번에서 연결된 번호 정보. 0안씀
		ArrayList<Integer>[] from = new ArrayList[N + 1];

		// 고려후보 넣을 큐
		Queue<Integer> quu;

		// 방문 가능 표시 배열. 자기자신 방문가능
		boolean[][] canVisit = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			canVisit[i][i] = true;
			from[i] = new ArrayList<>();
		}

		// 선 정보 받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			int start = Integer.parseInt(stz.nextToken());
			int end = Integer.parseInt(stz.nextToken());

			from[start].add(end);
			canVisit[start][end] = true;
		}

		// 각 노드에서 갈 수 있는곳 bfs 돌리기
		for (int i = 1; i <= N; i++) {
			quu = new LinkedList<>();

			// bfs 시작할 자식들 큐에 넣기
			for (int idx : from[i]) {
				quu.add(idx);
			}

			// 갈 수 있는 만큼 bfs로 표시
			while (!quu.isEmpty()) {
				int cur = quu.poll();
				canVisit[i][cur] = true;

				// 방문하지 않은 자식 큐에 넣기
				for (int child : from[cur]) {
					if (canVisit[i][child])
						continue;
					quu.add(child);
				}
			}
		}

		// i로 들어올 수 있는 노드 + i에서 출발해서 갈 수 있는 노드가 N-1일 때 순서 알 수 있음
		for (int i = 1; i <= N; i++) {
			int cnt = 0;

			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;

				if (canVisit[i][j] || canVisit[j][i])
					cnt++;
			}

			if (cnt == N - 1)
				res++;
		}

		System.out.println(res);
	}
}
