package algo240825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek13904 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		int N = Integer.parseInt(stz.nextToken());

		PriorityQueue<Homework> pqDay = new PriorityQueue<>();
		PriorityQueue<Homework> pqScore = new PriorityQueue<>(new ScoreComparator());

		int sum = 0;

		// 과제 정보 입력 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			int dday = Integer.parseInt(stz.nextToken());
			int score = Integer.parseInt(stz.nextToken());

			pqDay.add(new Homework(dday, score));
		}

		int lastDday = 0; // 이전 과제의 기한 저장용 변수
		int maxDday = Integer.MIN_VALUE; // 현재까지 할 수 있는 최대 과제 수

		// 날짜순 정렬된 과제에 대해 1개씩 점수순pq에 추가해가면서 고려하기
		while (!pqDay.isEmpty()) {
			Homework temp = pqDay.poll();

			maxDday = Math.max(maxDday, temp.dday);

			// 이미 최대기한 만큼 과제가 있다면 1개 뺄지 탐색
			if (pqScore.size() >= maxDday) {
				// 이미 같은 날의 큰 점수가 입력된 상태라면
				if (lastDday == temp.dday) {
					// 앞선 날짜 중 가장 점수 작은 날 읽어와서
					Homework minScore = pqScore.peek();

					// 현재 점수가 더 크면 작은 과제 빼기
					if (minScore.score < temp.score) {
						sum -= minScore.score;
						pqScore.poll();
					}
					// 현재 점수가 작거나 같으면 다음 과제 고려 시작
					else
						continue;
				}
			}

			// 현재 과제 추가하기
			sum += temp.score;
			pqScore.add(temp);

			lastDday = temp.dday;

		}

		System.out.println(sum);

	}

	static class Homework implements Comparable<Homework> {
		int dday, score;

		Homework(int dday, int score) {
			this.dday = dday;
			this.score = score;
		}

		// 날짜순 오름차순, 이후 점수순 내림차순
		@Override
		public int compareTo(Homework o) {
			if (this.dday > o.dday)
				return 1;
			else if (this.dday < o.dday)
				return -1;
			else {
				if (this.score < o.score)
					return 1;
				else if (this.score > o.score)
					return -1;
				else
					return 0;
			}
		}

		@Override
		public String toString() {
			return this.dday + " " + this.score;
		}

	}

	// 점수 오름차순
	static class ScoreComparator implements Comparator<Homework> {

		@Override
		public int compare(Homework o1, Homework o2) {
			if (o1.score < o2.score)
				return -1;
			else if (o1.score > o2.score)
				return 1;
			else
				return 0;
		}

	}
}
