package algo240731;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek10974 {
	static int[] arr;
	static int N;
	static int mask;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());
		arr = new int[N];
		
		choice(0);

	}

	static void choice(int depth) {
		if (depth >= N) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < N; i++) {
				sb.append(arr[i]);
				if (i != N - 1)
					sb.append(" ");
			}
			// 출력
			System.out.println(sb);
			return;
		}

		for (int i = 0; i < N; i++) {
			// 이미 체크된 수라면
			if ((mask & (1 << i)) != 0)
				continue;
			int prev = mask;

			// 선택 표시
			mask = mask ^ (1 << i);
			arr[depth] = i + 1;
			
			choice(depth + 1);
			
			// 선택 되돌리기
			arr[depth] = 0;
			mask = prev;
		}

	}
}
