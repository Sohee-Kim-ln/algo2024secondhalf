package algo241017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek13334 {

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int L;
		int max = Integer.MIN_VALUE;
		int start = 0;

		// 끝점 기준 오름차순
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

		// 시작점 오름차순 후 끝점 오름차순
		PriorityQueue<int[]> inRail = new PriorityQueue<>(
				(o1, o2) -> o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));

		// 통근 정보 받기
		for (int i = 0; i < N; i++) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
			int a = Integer.parseInt(stz.nextToken());
			int b = Integer.parseInt(stz.nextToken());

			if (a < b)
				pq.add(new int[] { a, b });
			else
				pq.add(new int[] { b, a });
		}

		// 철로 길이 받기
		L = Integer.parseInt(bfr.readLine());

		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			start = now[1] - L;
			inRail.add(now);

			while (!inRail.isEmpty() && inRail.peek()[0] < start)
				inRail.poll();

			max = Math.max(max, inRail.size());
		}

		System.out.println(max);

	}

}
