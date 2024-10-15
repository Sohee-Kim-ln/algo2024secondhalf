package algo241015;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek28703 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		int diff = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int origin;

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 배열 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(stz.nextToken());
			max = Math.max(max, temp);

			pq.add(temp);
		}

		// 초기 상태
		diff = max - pq.peek();
		origin = max;

		// 작은 수 뽑아서 2배 후 넣기
		while (pq.peek() < origin) {
			int now = pq.poll();
			int doub = now * 2;
			pq.add(doub);

			max = Math.max(max, doub);
			diff = Math.min(diff, max - pq.peek());

//			System.out.println(pq.toString() + max);
		}

		// 출력
		System.out.println(diff);

	}
}
