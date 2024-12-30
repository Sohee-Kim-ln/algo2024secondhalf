package algo241230;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek20119 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		boolean[] visited = new boolean[N + 1];

		int[] degreeIn = new int[M + 1];
		int[] res = new int[M + 1];

		// included[물약] = 물약이 사용된 레시피 번호들
		List<Integer>[] included = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++)
			included[i] = new LinkedList<>();

		// 조제 가능약 저장용
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 위상 추적용
		Queue<Integer> quu = new LinkedList<>();

		// 레시피 정보 받기
		for (int i = 1; i <= M; i++) {
			stz = new StringTokenizer(bfr.readLine());
			int K = Integer.parseInt(stz.nextToken());
			degreeIn[i] = K;

			// 재료가 들어가는 레시피번호 저장
			for (int j = 0; j < K; j++)
				included[Integer.parseInt(stz.nextToken())].add(i);

			// 결과
			res[i] = Integer.parseInt(stz.nextToken());

		}

		// 소지 물약 정보 받기
		int L = Integer.parseInt(bfr.readLine());

		stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < L; i++) {
			int temp = Integer.parseInt(stz.nextToken());

			quu.add(temp);
			pq.add(temp);
			visited[temp] = true;
		}

		// 위상 정렬
		while (!quu.isEmpty()) {
			int now = quu.poll();

			for (int recipe : included[now]) {

				degreeIn[recipe]--;

				if (degreeIn[recipe] == 0 && !visited[res[recipe]]) {
					quu.add(res[recipe]);
					pq.add(res[recipe]);
					visited[res[recipe]] = true;
				}
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(pq.size()).append('\n');

		while (!pq.isEmpty()) {
			sb.append(pq.poll()).append(' ');
		}

		sb.setLength(sb.length() - 1);
		System.out.print(sb);
	}
}
