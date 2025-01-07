package algo250107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간초과
public class Baek17298Fail2 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bfr.readLine());

		int[] arr = new int[N];
		int[] res = new int[N];

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		Arrays.fill(res, -1);

		// 수열 정보 받기
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stz.nextToken());
			int idx = i - 1;

			while (idx >= 0) {

				if (res[idx] == -1 && arr[idx] < arr[i])
					res[idx] = arr[i];

				idx--;
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++)
			sb.append(res[i]).append(' ');

		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}
}
