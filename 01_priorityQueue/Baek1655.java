package algo240829;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Baek1655 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		// 중간값보다 작은큐, 큰 큐
		PriorityQueue<Integer> pqDown = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> pqUp = new PriorityQueue<>();

		// 중간 근처 값 저장용
		int up = Integer.MIN_VALUE;
		int down = Integer.MAX_VALUE;

		StringBuilder sb = new StringBuilder();

		// 숫자 정보 받기
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(bfr.readLine());

			// 중간 근처 값 특정하기
			down = pqDown.isEmpty() ? Integer.MAX_VALUE : pqDown.peek();
			up = pqUp.isEmpty() ? Integer.MIN_VALUE : pqUp.peek();

			// 들어온 수에 따라 up, down과 비교해서 넣기 시작
			// 중간값보다 작으면 작은큐
			if (num < down)
				pqDown.add(num);
			// 중간 다음값보다 크면 큰큐
			else if (num > up) {
				pqUp.add(num);
			}
			// 가운데 범위에 걸쳐있으면
			else {
				// 작은큐로 내려서 중간값으로 만들기
				if (pqDown.size() != i / 2 + 1)
					pqDown.add(num);
				// 큰큐로 올려서 중간 다음값 갱신
				else
					pqUp.add(num);
			}

			// 들어온 값으로 양쪽 큐 균형 안맞으면 중간 맞춰주기
			while (pqDown.size() != i / 2 + 1) {
				if (pqDown.size() > pqUp.size())
					pqUp.add(pqDown.poll());
				else
					pqDown.add(pqUp.poll());
			}

			sb.append(pqDown.peek()).append("\n");
		}
		System.out.println(sb);
	}
}
