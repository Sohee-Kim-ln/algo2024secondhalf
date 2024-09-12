package algo240912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//작은가방부터 계산하는데, 해당 가방에 담을 수 있는 가장 가치 큰 보석을 담을 것
public class Baek1202 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());

		Jewel[] arr = new Jewel[N];
		PriorityQueue<Integer> pqBag = new PriorityQueue<>();
		PriorityQueue<Integer> pqValue = new PriorityQueue<>(Comparator.reverseOrder());

		long sum = 0;

		// 보석 정보 받기
		int m, v;
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			m = Integer.parseInt(stz.nextToken());
			v = Integer.parseInt(stz.nextToken());
			arr[i] = new Jewel(m, v);
		}

		// 무게 오름차순, 가치 내림차순 정렬
		Arrays.sort(arr);

		// 가방 정보 받기
		for (int i = 0; i < K; i++) {
			pqBag.add(Integer.parseInt(bfr.readLine()));
		}

		// 배열 idx 포인터
		int p = 0;

		// 작은 배낭부터 넣을 수 있는 가장 큰 가치 찾기
		while (!pqBag.isEmpty()) {
			int max = pqBag.poll();

			// 현재 가방에 넣을 수 있으면 pqValue 추가 및 포인터 이동
			while (p < N && arr[p].m <= max) {
				pqValue.add(arr[p++].v);
			}

			if (!pqValue.isEmpty())
				sum += (long) pqValue.poll();
		}
		
		System.out.println(sum);
	}

	static class Jewel implements Comparable<Jewel> {
		int m, v;

		Jewel(int m, int v) {
			this.m = m;
			this.v = v;
		}

		// 무게 오름차순, 가치 내림차순 정렬
		@Override
		public int compareTo(Jewel o) {
			if (this.m > o.m)
				return 1;
			else if (this.m < o.m)
				return -1;
			else {
				if (this.v < o.v)
					return 1;
				else if (this.v == o.v)
					return 0;
				else
					return -1;
			}
		}
	}
}
