package algo241102;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baek16936 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		// {값, 2갯수, 3갯수}
		long[][] arr = new long[N][3];

		// 수열 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < N; i++) {
			long temp = Long.parseLong(stz.nextToken());
			arr[i][0] = temp;

			long cnt = 0;

			// 2 갯수 계산
			while (temp > 0) {
				if (temp % 2 != 0)
					break;

				cnt++;
				temp /= 2;
			}

			arr[i][1] = cnt;

			// 3 갯수 계산
			temp = arr[i][0];
			cnt = 0;

			while (temp > 0) {
				if (temp % 3 != 0)
					break;

				temp /= 3;
				cnt++;
			}

			arr[i][2] = cnt;

		}

		Arrays.sort(arr, new Comparator<long[]>() {

			@Override
			public int compare(long[] o1, long[] o2) {
				if (o1[2] > o2[2])
					return -1;
				else if (o1[2] < o2[2])
					return 1;
				else
					return Long.compare(o1[1],o2[1]);
			}

		});

//		// 테스트
//		for (int i = 0; i < N; i++)
//			System.out.println(Arrays.toString(arr[i]));

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++)
			sb.append(arr[i][0]).append(' ');

		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}
}
