package algo240906;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek1948 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int M = Integer.parseInt(bfr.readLine());
		int[] degreeIn = new int[N + 1];
		int[] delay = new int[N + 1];

		int start, end, cnt = 0;

		// 도로 정보 저장용
		ArrayList<Road>[] roads = new ArrayList[N + 1];
		ArrayList<Road>[] roadsR = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			roads[i] = new ArrayList<>();
			roadsR[i] = new ArrayList<>();
		}
		// 도로 정보 받기
		StringTokenizer stz;
		int from, to, time;
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			from = Integer.parseInt(stz.nextToken());
			to = Integer.parseInt(stz.nextToken());
			time = Integer.parseInt(stz.nextToken());
			roads[from].add(new Road(to, time));
			roadsR[to].add(new Road(from, time));
			degreeIn[to]++;
		}

		// 출발 도착 정보 받기
		stz = new StringTokenizer(bfr.readLine(), " ");
		start = Integer.parseInt(stz.nextToken());
		end = Integer.parseInt(stz.nextToken());

		// 위상정렬용 큐
		Queue<Integer> quu = new LinkedList<>();
		quu.add(start);

		// 위상정렬. 도시i 까지 오는 가장 긴 시간 구하기
		while (!quu.isEmpty()) {
			int cur = quu.poll();

			for (Road road : roads[cur]) {
				int next = road.dest;

				// 현재 루트가 더 오래걸린다면 대체
				if (delay[next] < delay[cur] + road.time) {
					delay[next] = delay[cur] + road.time;
				}

				degreeIn[next]--;
				if (degreeIn[next] == 0)
					quu.add(next);
			}
		}

		// 역추적 시작점은 도착마을
		quu.add(end);
		boolean[] visited = new boolean[N + 1];
		
		// BFS로 역추적
		while (!quu.isEmpty()) {
			int cur = quu.poll();

			// 방문여부 확인
			if (visited[cur])
				continue;

			visited[cur] = true;

			for (Road road : roadsR[cur]) {
				int prev = road.dest;
				
				// 최단루트로 갈 때 소요시간 == 현재 도로 시간일 때
				if (delay[cur] - delay[prev] == road.time) {
					quu.add(prev);
					cnt++;
				}
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(delay[end]).append("\n").append(cnt);
		System.out.println(sb);
	}

	static class Road {
		int dest;
		int time;

		Road(int dest, int time) {
			this.dest = dest;
			this.time = time;
		}
	}
}
