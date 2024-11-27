package algo241127;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1461 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		// 양수, 음수별 절댓값 내림차순
		PriorityQueue<Integer> pqP = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> pqM = new PriorityQueue<>(Collections.reverseOrder());

		int sum = 0;
		int max = Integer.MIN_VALUE;

		// 책 원래위치 정보 받기
		stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(stz.nextToken());

			if (temp > 0)
				pqP.add(temp);
			else
				pqM.add(-temp);
		}

		// 음수쪽 걸음 계산
		while (!pqM.isEmpty()) {

			// 가장 멀리있는 책 걸음 세기
			int now = pqM.poll();
			sum += now << 1;

			// 제일 멀리있어서 안돌아올 경로 저장
			max = Math.max(max, now);

			// 현재 책이랑 같이 옮길 M-1개 pqM에서 빼기
			for (int i = 0; i < M - 1; i++) {
				if (!pqM.isEmpty())
					pqM.poll();
				else
					break;
			}

		}

		// 양수쪽 걸음 계산
		while (!pqP.isEmpty()) {

			// 가장 멀리있는 책 걸음 세기
			int now = pqP.poll();
			sum += now << 1;

			// 제일 멀리있어서 안돌아올 경로 저장
			max = Math.max(max, now);

			// 현재 책이랑 같이 옮길 M-1개 pqM에서 빼기
			for (int i = 0; i < M - 1; i++) {
				if (!pqP.isEmpty())
					pqP.poll();
				else
					break;
			}
		}

		System.out.println(sum - max);

	}
}
