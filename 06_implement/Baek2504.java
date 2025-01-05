package algo250105;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek2504 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String tempS = bfr.readLine();

		Stack<Character> st = new Stack<>();

		int res = 0;
		int value = 1;

		for (int i = 0; i < tempS.length(); i++) {
			char now = tempS.charAt(i);

			// 여는 괄호 *2
			if (now == '(') {
				st.add(now);
				value *= 2;
			}
			// 여는 괄호 *3
			else if (now == '[') {
				st.add(now);
				value *= 3;
			}
			// 닫는 괄호
			else if (now == ')') {
				// 잘못된 수식일 때
				if (st.isEmpty() || st.peek() != '(') {
					res = 0;
					break;
				}

				// 직전 여는괄호로 더이상 곱해질 수식이 없을 때 저장
				if (tempS.charAt(i - 1) == '(')
					res += value;

				// 일종의 백트래킹
				value /= 2;
				st.pop();

			}
			// 닫는 괄호
			else {
				// 잘못된 수식일 때
				if (st.isEmpty() || st.peek() != '[') {
					res = 0;
					break;
				}

				// 직전 여는괄호로 더이상 곱해질 수식이 없을 때 저장
				if (tempS.charAt(i - 1) == '[')
					res += value;

				// 일종의 백트래킹
				value /= 3;
				st.pop();
			}

		}

		// 출력
		System.out.println(st.isEmpty() ? res : 0);
	}
}
