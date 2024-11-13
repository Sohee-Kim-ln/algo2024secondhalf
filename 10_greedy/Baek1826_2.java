package algo241113;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1826_2 {

	static class Station implements Comparable<Station> {
		int dist, fuel;

		Station(int dist, int fuel) {
			this.dist = dist;
			this.fuel = fuel;
		}

		@Override
		public int compareTo(Station o) {
			return o.fuel - this.fuel;
		}

		@Override
		public String toString() {
			return "Station [dist=" + dist + ", fuel=" + fuel + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 주유소 갯수
		int N = Integer.parseInt(bfr.readLine());

		StringTokenizer stz;

		Station[] stations = new Station[N];

		// 주유소 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine());
			int a = Integer.parseInt(stz.nextToken());
			int b = Integer.parseInt(stz.nextToken());

			// 저장할 것
			stations[i] = new Station(a, b);
		}

		// 주유소 거리별 정렬
		Arrays.sort(stations, (o1, o2) -> (o1.dist - o2.dist));

		// 마을위치, 현재 연료
		stz = new StringTokenizer(bfr.readLine());
		int L = Integer.parseInt(stz.nextToken());
		int P = Integer.parseInt(stz.nextToken());

		// 현재 위치, 주유소 인덱스, 멈춘 횟수
		int idx = 0;
		int cnt = 0;

		// 연료 내림차순 pq
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		// 마을 도착까지 반복
		while (P < L) {
			// 현재 갈 수 있는 마을 다 넣기
			while (idx < N && stations[idx].dist <= P) {
				pq.add(stations[idx++].fuel);
			}

			// 갈 수 있는 정거장이 없으면 종료
			if (pq.isEmpty()) {
				System.out.println(-1);
				return;
			}

			// 현재위치 이후의 가장 연료값이 큰 주유소 뽑아서 이동
			P += pq.poll();
			cnt++;

		}

		// 출력
		System.out.println(cnt);
	}
}
