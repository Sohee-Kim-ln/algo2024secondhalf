package algo241225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek9470 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bfr.readLine());

		StringBuilder sb = new StringBuilder();

		// 각 테케에 대해
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine());
			int K = Integer.parseInt(stz.nextToken());
			int M = Integer.parseInt(stz.nextToken());
			int P = Integer.parseInt(stz.nextToken());

			// 0 안씀
			// 진입차수
			int[] degreeIn = new int[M + 1];

			// 최대인 순서 저장
			int[] maxIn = new int[M + 1];

			// 최대 순서의 갯수
			int[] cnt = new int[M + 1];

			// 결과
			int[] order = new int[M + 1];

			Queue<Integer> quu = new LinkedList<>();

			LinkedList<Integer>[] from = new LinkedList[M + 1];
			for (int i = 1; i <= M; i++) {
				from[i] = new LinkedList<>();
			}

			// 노드 정보 받기
			for (int i = 0; i < P; i++) {
				stz = new StringTokenizer(bfr.readLine());
				int A = Integer.parseInt(stz.nextToken());
				int B = Integer.parseInt(stz.nextToken());

				from[A].add(B);

				degreeIn[B]++;
			}

			// in==0인 노드 큐에넣기
			for (int i = 1; i <= M; i++)
				if (degreeIn[i] == 0)
					quu.add(i);

			// 큐가 빌때까지 반복
			while (!quu.isEmpty()) {
				// 꺼낸 노드의 순서 계산
				int now = quu.poll();

				if (cnt[now] == 1)
					order[now] = maxIn[now];
				else
					order[now] = maxIn[now] + 1;

				// 다음 노드 진입 지우기
				for (int next : from[now]) {
					degreeIn[next]--;

					if (maxIn[next] < order[now]) {
						maxIn[next] = order[now];
						cnt[next] = 1;
					} else if (maxIn[next] == order[now])
						cnt[next]++;

					// 다음노드 in==0이면 큐에 넣기
					if (degreeIn[next] == 0)
						quu.add(next);
				}

			}

			// 결과 저장
			sb.append(K).append(' ').append(order[M]).append('\n');

		}
		// 출력
		System.out.print(sb);

	}
}
