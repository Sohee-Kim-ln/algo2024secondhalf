package algo241108;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek11657TooMuchTime {
	static int N, M;
	static long sum = 0;
	static long[] time;

	static class Bus implements Comparable<Bus> {
		int end;
		long cost;

		Bus(int end, long cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Bus o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		// 도시 수, 버스 수
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());

		time = new long[N + 1];
		Arrays.fill(time, Long.MAX_VALUE);
		time[1] = 0;

		// 연결정보 저장용
		ArrayList<Bus>[] from = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++)
			from[i] = new ArrayList<>();

		// 노선 정보 받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine());
			int start = Integer.parseInt(stz.nextToken());
			int end = Integer.parseInt(stz.nextToken());
			long cost = Long.parseLong(stz.nextToken());
			from[start].add(new Bus(end, cost));

			if (cost < 0)
				sum += cost;

		}

		// 탐색대상 저장큐용 어레이덱
		ArrayDeque<Integer> adq = new ArrayDeque<>();

		// 초기값 지정
		adq.add(1);

		while (!adq.isEmpty()) {
			int now = adq.poll();

			for (Bus b : from[now]) {
				// 최소가 갱신된다면
				if (time[b.end] > time[now] + b.cost) {
					time[b.end] = time[now] + b.cost;

					// 음수 사이클이라면
					if (time[b.end] < sum) {
						System.out.println(-1);
						return;
					}
					adq.add(b.end);
				}
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			if (time[i] == Long.MAX_VALUE)
				sb.append(-1).append("\n");
			else
				sb.append(time[i]).append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}
}
