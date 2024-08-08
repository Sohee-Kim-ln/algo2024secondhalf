package algo240808;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek2812Timeover {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String[] tempS = bfr.readLine().split(" ");

		int N = Integer.parseInt(tempS[0]);
		int K = Integer.parseInt(tempS[1]);

		int length = N - K;

		Stack<Integer> st = new Stack<>();
		Stack<Integer> st2 = new Stack<>();

		int[] nums = new int[N];

		tempS = bfr.readLine().split("");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(tempS[i]);

		}

		int pointer = N - 1;
		while (pointer >= 0) {
			int curr = nums[pointer];

			// 스택에 쌓인 숫자가 일정 길이 미만이면 넣기
			if (st.size() < length) {
				st.add(curr);
			}
			// 목표길이 도달한 상태면
			else {
				// 맨 윗수보다 후보 수가 크면
				if (st.peek() <= curr) {
					st2.add(st.pop());

					// 아래로 내려가면서 커지는 시점 찾아서 커지기 전 수 빼고 넣기
					while (!st.isEmpty()) {
						if (st2.peek() >= st.peek())
							st2.add(st.pop());
						else {
							st2.pop();
							break;
						}
					}

					// 빠진 수가 하나도 없다면 맨 아랫자리 빼기
					if (st2.size() == length)
						st2.pop();

					// 스택 원래대로 복원하기
					while (!st2.isEmpty()) {
						st.add(st2.pop());
					}

					// 맨 윗자리에 후보수 붙이기
					st.add(curr);

				}

			}
			pointer--;
		}

		// 출력
		StringBuilder sb = new StringBuilder();

		while (!st.isEmpty()) {
			sb.append(st.pop());
		}
		System.out.println(sb);

	}
}
