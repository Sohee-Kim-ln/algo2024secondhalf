package algo250107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek17298 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bfr.readLine());

		int[] arr = new int[N];
		int[] res = new int[N];

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		Stack<Integer> st = new Stack<>();

		// 수열 정보 받기
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(stz.nextToken());

		// 거꾸로 계산 시작
		for (int i = N - 1; i >= 0; i--) {
			// 현재 수보다 큰 수 만날 때까지 빼기
			while (!st.isEmpty() && arr[i] >= st.peek())
				st.pop();

			// 오른쪽에 큰 수가 없으면
			if (st.isEmpty())
				res[i] = -1;
			else
				res[i] = st.peek();

			// 현재 수 추가
			st.add(arr[i]);

		}

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++)
			sb.append(res[i]).append(' ');

		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
