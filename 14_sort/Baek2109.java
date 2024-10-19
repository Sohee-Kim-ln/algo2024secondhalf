package algo241019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek2109 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		Lecture[] lec = new Lecture[N];

		int sum = 0;
		int lastDay = 0;

		// 비용 오름차순 우선큐
		PriorityQueue<Lecture> pq = new PriorityQueue<>(new Comparator<Lecture>() {
			@Override
			public int compare(Lecture o1, Lecture o2) {
				return Integer.compare(o1.pay, o2.pay);
			}
		});

		// 강연 정보 받기
		for (int i = 0; i < N; i++) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

			lec[i] = new Lecture(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()));

		}

		// 날짜 오름차순 후 비용 내림차순 정렬
		Arrays.sort(lec);

		// 날짜를 늘려가며 해당 날짜까지의 강연까지 가능한 갯수 유지
		for (int i = 0; i < N; i++) {
			lastDay = lec[i].day;
			pq.add(lec[i]);

			while (pq.size() > lastDay)
				pq.poll();

		}

		// 남은 강연 비용 합
		while (!pq.isEmpty())
			sum += pq.poll().pay;

		// 출력
		System.out.println(sum);

	}

	static class Lecture implements Comparable<Lecture> {
		int pay;
		int day;

		Lecture(int pay, int day) {
			this.pay = pay;
			this.day = day;
		}

		// 날짜 오름차순 후 비용 내림차순
		@Override
		public int compareTo(Lecture o) {
			if (this.day != o.day)
				return Integer.compare(this.day, o.day);
			else
				return Integer.compare(o.pay, this.pay);
		}

		@Override
		public String toString() {
			return "Lecture [pay=" + pay + ", day=" + day + "]";
		}

	}
}
