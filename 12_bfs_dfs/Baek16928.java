package algo241205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek16928 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		int[] dist = new int[101];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;

		int[] from = new int[101];

		Queue<Integer> quu = new LinkedList<>();

		// 사다리뱀 정보 받기
		for (int i = 0; i < N+M; i++) {
			stz = new StringTokenizer(bfr.readLine());
			int start = Integer.parseInt(stz.nextToken());
			int end = Integer.parseInt(stz.nextToken());

			from[start] = end;
		}

		quu.add(1);

		while (!quu.isEmpty()) {
			int now = quu.poll();

			// 최소도달 보다 주사위 횟수가 높으면 더이상 고려하지 않음
			if (dist[100] < dist[now])
				continue;

			// 주사위 숫자별 진행
			for (int i = 1; i <= 6; i++) {
				int next = now + i;

				// 경계조건
				if (next > 100)
					continue;

				// 연결된 곳이 있다면 이동
				if (from[next] != 0)
					next = from[next];

				if (dist[next] > dist[now] + 1) {
					dist[next] = dist[now] + 1;
					quu.add(next);
				}

			}
		}
		
		System.out.println(dist[100]);

	}
}
