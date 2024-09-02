package algo240902;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek1005 {
	static int N, K, W;
	static ArrayList<Integer>[] after;
	static int[] delay, degreeIn, time;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bfr.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
			N = Integer.parseInt(stz.nextToken());
			K = Integer.parseInt(stz.nextToken());

			// 0 안씀
			delay = new int[N + 1];
			degreeIn = new int[N + 1];
			time = new int[N + 1];

			after = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				after[i] = new ArrayList<>();
			}

			// 건물 건설시간 정보 받기
			stz = new StringTokenizer(bfr.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				delay[i] = Integer.parseInt(stz.nextToken());
			}

			// 연결정보 받기
			for (int i = 0; i < K; i++) {
				stz = new StringTokenizer(bfr.readLine(), " ");
				int start = Integer.parseInt(stz.nextToken());
				int end = Integer.parseInt(stz.nextToken());

				after[start].add(end);
				degreeIn[end]++;
			}

			W = Integer.parseInt(bfr.readLine());

			// 위상 정렬용 큐
			Queue<Integer> quu = new LinkedList<>();

			// 진입차수 0인 노드 넣기
			for (int i = 1; i <= N; i++)
				if (degreeIn[i] == 0) {
					time[i]=delay[i];
					quu.add(i);
				}

			// 목표 계산될때까지 반복
			while (!quu.isEmpty()) {
				int cur = quu.poll();

				// 목표 노드가 계산됐으면 반복 종료
				if (cur == W)
					break;

				// 진출 마다 차수--, 0된 노드 큐에 넣고 반복
				for (int next : after[cur]) {
					time[next] = Math.max(time[next], time[cur] + delay[next]);
					degreeIn[next]--;
					if (degreeIn[next] == 0)
						quu.add(next);
				}
			}

			// 출력
			System.out.println(time[W]);
		}
	}
}
