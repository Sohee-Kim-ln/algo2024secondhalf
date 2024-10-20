package algo241020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek13549adq {
	static int N, K, maxTime;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		N = Integer.parseInt(stz.nextToken());
		K = Integer.parseInt(stz.nextToken());
		maxTime = Math.max(K, N) + 2;

		// 도달 시간 저장 배열
		int[] time = new int[maxTime + 1];
		Arrays.fill(time, Integer.MAX_VALUE);

		ArrayDeque<Integer> adq = new ArrayDeque<>();

		// 초기화
		time[N] = 0;
		adq.add(N);
		int next = -1;

		// 다익스트라 탐색
		while (!adq.isEmpty()) {
			int now = adq.poll();

			// +1 시 최소시간 갱신
			next = now + 1;
			if (isValid(next) && time[next] > time[now] + 1) {
				time[next] = time[now] + 1;
				adq.add(next);
			}

			// -1 시 최소시간 갱신
			next = now - 1;
			if (isValid(next) && time[next] > time[now] + 1) {
				time[next] = time[now] + 1;
				adq.add(next);

			}

			// 순간이동 시 최소시간 갱신
			next = now << 1;
			if (isValid(next) && time[next] > time[now]) {
				time[next] = time[now];
				adq.add(next);
			}

		}

		// 출력
		System.out.println(time[K]);

	}

	static boolean isValid(int pos) {
		if (pos >= 0 && pos <= maxTime)
			return true;
		else
			return false;
	}
}