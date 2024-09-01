package algo240901;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek2252 {
	static int N, M;

	static ArrayList<Integer>[] graph;
	static int[] degreeIn;
	static int[] res;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());

		degreeIn = new int[N + 1];
		res = new int[N + 1];

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();

		// 연결정보 받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			int start = Integer.parseInt(stz.nextToken());
			int end = Integer.parseInt(stz.nextToken());

			graph[start].add(end);
			degreeIn[end]++;
		}

		topologySort();

		// 출력
		for (int i = 1; i <= N; i++) {
			sb.append(res[i]).append(" ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}

	// 위상정렬
	static void topologySort() {
		Queue<Integer> quu = new LinkedList<>();

		// 진입차수 0인 노드 삽입
		for (int i = 1; i <= N; i++) {
			if (degreeIn[i] == 0)
				quu.add(i);
		}

		for (int i = 1; i <= N; i++) {
			// 그래프 사이클 존재로 인해 위상정렬 불가능 및 함수종료
			if (quu.isEmpty())
				return;

			res[i] = quu.poll();

			for (int node : graph[res[i]]) {
				degreeIn[node]--;
				if (degreeIn[node] == 0)
					quu.add(node);
			}
		}
	}
}
