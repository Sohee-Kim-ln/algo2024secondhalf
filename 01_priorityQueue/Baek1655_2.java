package algo240829;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Baek1655_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		// 중간값보다 작은큐, 큰 큐
		PriorityQueue<Integer> pqDown = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> pqUp = new PriorityQueue<>();

		StringBuilder sb = new StringBuilder();

		// 숫자 정보 받기
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(bfr.readLine());

			if (pqDown.size() == pqUp.size())
				pqDown.offer(num);
			else
				pqUp.offer(num);

			if (!pqUp.isEmpty())
				if (pqDown.peek() > pqUp.peek()) {
					pqUp.offer(pqDown.poll());
					pqDown.offer(pqUp.poll());
				}

			sb.append(pqDown.peek()).append("\n");
		}
		System.out.println(sb);
	}
}
