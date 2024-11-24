package algo241124;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek19638 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int centi = Integer.parseInt(stz.nextToken());
		int T = Integer.parseInt(stz.nextToken());
		int cnt = 0;

		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

		// 거인 키 정보 받기
		for (int i = 0; i < N; i++)
			pq.add(Integer.parseInt(bfr.readLine()));

		// 뿅망치 사용
		while (true) {

			int now = pq.poll();

			// 가장 큰 거인이 센티보다 작은경우
			if (now < centi) {
				sb.append("YES").append('\n').append(cnt);
				break;
			}

			// 센티보다 작지 않지만 망치 횟수 다 쓴 경우
			if (cnt == T) {
				sb.append("NO").append('\n').append(now);
				break;
			}

			// 망치로 줄여서 넣기
			cnt++;

			pq.add(Math.max(1, now / 2));

		}
		
		// 출력
		System.out.println(sb);
	}
}
