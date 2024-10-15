package algo241015;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek28703_2 {
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
		while (true) {
			int now = pq.poll();

			if (now >= origin)
				break;

			int doub = now << 1;

			boolean changed = false;

			// 2 반복곱이 가능하면 최대값 전까지 반복곱
			while (now << 1 <= origin) {
				now <<= 1;
				changed = true;
			}

			// 반복곱이 없었다면 *2 하고 결과물로 최대값 갱신
			if (!changed) {
				now <<= 1;
				max = Math.max(max, doub);
			}

			pq.add(now);
			diff = Math.min(diff, max - pq.peek());

		}

		// 출력
		System.out.println(diff);

	}
}
