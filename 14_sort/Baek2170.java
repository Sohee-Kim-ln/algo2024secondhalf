package algo240824;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek2170 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		int N = Integer.parseInt(stz.nextToken());

		// 시작이 앞인 점부터 꺼낼 우선큐
		PriorityQueue<Line> pq = new PriorityQueue<>(new LineComparator());

		// 선들 정보 입력받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			long start = Long.parseLong(stz.nextToken());
			long end = Long.parseLong(stz.nextToken());
			pq.add(new Line(start, end));
		}

		// 첫 선 정보 저장
		Line temp = pq.poll();
		long firstStart = temp.start;
		long lastEnd = temp.end;
		long sum = 0;

		// 앞의 선부터 뽑으면서 현재 선의 연장한계 찾기
		while (!pq.isEmpty()) {
			temp = pq.poll();

			// 연장이 더이상 되지 않으면 sum 계산 후 선 정보 갱신
			if (lastEnd < temp.start) {
				sum += lastEnd - firstStart;
				firstStart = temp.start;
				lastEnd = temp.end;
			}
			// 연장
			else
				lastEnd = Math.max(lastEnd, temp.end);
		}

		// 마지막 선 계산
		sum += lastEnd - firstStart;

		System.out.println(sum);

	}

	static class Line {
		long start, end;

		Line(long start, long end) {
			this.start = start;
			this.end = end;
		}
	}

	static class LineComparator implements Comparator<Line> {
		@Override
		public int compare(Line o1, Line o2) {
			if (o1.start < o2.start)
				return -1;
			else if (o1.start == o2.start)
				return 0;
			else
				return 1;
		}

	}
}
