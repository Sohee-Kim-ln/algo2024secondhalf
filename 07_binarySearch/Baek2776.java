package algo241206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2776 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bfr.readLine());

		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			// 수첩1 정보 받기
			int N = Integer.parseInt(bfr.readLine());
			int[] saw = new int[N];

			StringTokenizer stz = new StringTokenizer(bfr.readLine());
			for (int i = 0; i < N; i++)
				saw[i] = Integer.parseInt(stz.nextToken());

			// 수첩1 오름차순 정렬
			Arrays.sort(saw);

			// 수첩2 정보 받기
			int M = Integer.parseInt(bfr.readLine());

			stz = new StringTokenizer(bfr.readLine());

			// M개 숫자에 대해 이분탐색
			for (int i = 0; i < M; i++) {
				int target = Integer.parseInt(stz.nextToken());

				int start = 0;
				int end = N - 1;
				int mid;

				boolean isFound = false;

				// 이분탐색
				while (start <= end) {
					mid = (start + end) / 2;

					if (saw[mid] < target)
						start = mid + 1;
					else if (saw[mid] > target)
						end = mid - 1;

					else {
						isFound = true;
						break;
					}
				}

				sb.append(isFound ? "1\n" : "0\n");
			}
		}
		
		// 출력
		sb.setLength(sb.length()-1);
		System.out.print(sb);

	}
}
