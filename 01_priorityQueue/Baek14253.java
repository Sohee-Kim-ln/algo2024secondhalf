package algo240818;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek14253 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		int a = -1;

		for (int i = 0; i < N; i++) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
			a = Integer.parseInt(stz.nextToken());
			// 아이들을 만났다면
			if (a == 0) {
				// 선물이 없다면 -1 출력
				if (pq.isEmpty())
					System.out.println(-1);
				else
					System.out.println(pq.poll());
			}
			// 거점지라면
			else {
				// 선물을 우선큐에 넣기
				for (int j = 0; j < a; j++) {
					pq.add(Integer.parseInt(stz.nextToken()));
				}
			}
		}
	}
}
