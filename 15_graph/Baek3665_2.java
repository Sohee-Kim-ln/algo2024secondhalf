package algo240903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek3665_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer stz;

		// 각 케이스에 대해
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(bfr.readLine());
			int[] lastYear = new int[N + 1];
			int[] thisYear = new int[N + 1];
			int[] degreeIn = new int[N + 1];

			boolean isPossible = true;

			ArrayList<Integer>[] win = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++)
				win[i] = new ArrayList<>();

			// 작년 정보 받기
			stz = new StringTokenizer(bfr.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				lastYear[i] = Integer.parseInt(stz.nextToken());
				degreeIn[lastYear[i]] = i - 1;

				for (int j = 1; j < i; j++) {
					win[lastYear[j]].add(lastYear[i]);
				}
			}


			// 올해 순위 바뀐 정보 받기
			int M = Integer.parseInt(bfr.readLine());

			for (int i = 0; i < M; i++) {
				stz = new StringTokenizer(bfr.readLine(), " ");
				int prev = Integer.parseInt(stz.nextToken());
				int next = Integer.parseInt(stz.nextToken());

				if (win[next].contains(prev)) {
					win[next].remove((Integer) prev);
					win[prev].add(next);
					degreeIn[prev]--;
					degreeIn[next]++;
				} else {
					win[prev].remove((Integer) next);
					win[next].add(prev);
					degreeIn[prev]++;
					degreeIn[next]--;
				}
			}

			// 위상 정렬용 큐 및 초기 시작점
			Queue<Integer> quu = new LinkedList<>();

			for (int i = 1; i <= N; i++) {
				if (degreeIn[i] == 0)
					quu.add(i);
			}

			// 위상정렬 시작
			int pointer = 1;
			while (pointer <= N && !quu.isEmpty()) {
				int cur = quu.poll();
				thisYear[pointer] = cur;
				pointer++;

				for (int lose : win[cur]) {
					degreeIn[lose]--;
					if (degreeIn[lose] == 0)
						quu.add(lose);
				}

				// 순위가 같은 팀이 있는 경우 불가능
				if (quu.size() > 1) {
					isPossible = false;
					break;
				}
			}

			// 순환 있어서 불가능한 경우
			if (pointer != N + 1)
				isPossible = false;

			if (isPossible) {
				for (int i = 1; i <= N; i++)
					sb.append(thisYear[i]).append(" ");
				sb.setLength(sb.length() - 1);
				sb.append("\n");
			} else
				sb.append("IMPOSSIBLE\n");
		}
		System.out.println(sb);

	}
}
