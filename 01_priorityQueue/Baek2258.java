package algo241018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek2258 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int sumP = 0;
		int sumW = 0;
		int last = 0;
		int min = Integer.MAX_VALUE;

		PriorityQueue<Meat> pq = new PriorityQueue<>();

		// 고기 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			pq.add(new Meat(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())));
		}

		while (!pq.isEmpty()) {
			Meat now = pq.poll();

			// 꺼낸 고기가 이전보다 큰 값이면
			if (now.price != last) {
				last = now.price;
				sumP = now.price;
				sumW += now.weight;
			}
			// 꺼낸 고기가 이전과 같은 값이면
			else {
				sumP += now.price;
				sumW += now.weight;
			}

			// 고기 양이 요구치를 넘겼다면 최소값 갱신
			if (sumW >= M)
				min = Math.min(min, sumP);
		}

		System.out.println(sumW < M ? -1 : min);

	}

	static class Meat implements Comparable<Meat> {
		int weight;
		int price;

		Meat(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}

		// 가격 오름차순 후 무게 내림차순
		@Override
		public int compareTo(Meat o) {
			if (this.price != o.price)
				return Integer.compare(this.price, o.price);
			return Integer.compare(o.weight, this.weight);

		}
	}
}
