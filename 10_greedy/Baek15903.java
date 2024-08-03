package algo240803;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baek15903 {
	static PriorityQueue<Long> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String[] tempS = bfr.readLine().split(" ");

		int N = Integer.parseInt(tempS[0]);
		int M = Integer.parseInt(tempS[1]);
		long sum = 0;
		
		// 카드 입력받기
		tempS = bfr.readLine().split(" ");
		
		for (int i = 0; i < N; i++) {
			pq.add(Long.parseLong(tempS[i]));
		}

		// 카드 M번 합치기
		for (int i = 0; i < M; i++) {
			addCards();
		}

		// 남은 카드 합 계산
		while (!pq.isEmpty()) {
			sum += pq.poll();
		}

		System.out.println(sum);

	}

	static void addCards() {
		long smaller = pq.poll();
		long bigger = pq.poll();
//		int cnt = 0;
//
//		// 안같은거 뽑을 때까지 반복
//		while (smaller == bigger) {
//			bigger = pq.poll();
//			cnt++;
//		}
//
//		// 같은 거 뽑은 것들 다시 넣어주기
//		while (cnt != 0) {
//			pq.add(smaller);
//			cnt--;
//		}

		pq.add(smaller + bigger);
		pq.add(smaller + bigger);
	}
}
