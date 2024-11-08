package algo241108;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Baek11657 {
	static int N, M;
	static long sum = 0;
	static long[] time;

	static class Bus implements Comparable<Bus> {
		int start;
		int end;
		long cost;

		Bus(int start, int end, long cost) {
			this.start = start;
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

		// 연결정보 저장
		List<Bus> busline = new ArrayList<>();

		// 노선 정보 받아서 pq에서 정렬
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine());
			int start = Integer.parseInt(stz.nextToken());
			int end = Integer.parseInt(stz.nextToken());
			long cost = Long.parseLong(stz.nextToken());

			busline.add(new Bus(start, end, cost));
		}

		// 벨만포드
		// N-1까지 갱신, N번째에서 갱신 시 음수사이클 존재
		for (int i = 1; i <= N; i++) {
			for (Bus b : busline) {
				// 아직 1과 연결되지 않은 곳은 continue
				if (time[b.start] == Long.MAX_VALUE)
					continue;

				// 더 짧은 노선 연결됐다면 갱신
				if (time[b.end] > time[b.start] + b.cost) {

					// N 번째에서 변경이 일어난다면 음수 사이클 존재. 종료
					if (i == N) {
						System.out.println(-1);
						return;
					}

					time[b.end] = time[b.start] + b.cost;
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
