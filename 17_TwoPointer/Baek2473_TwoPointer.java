package algo241023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 13832KB 160ms
public class Baek2473_TwoPointer {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		long[] num = new long[N];

		long minAbs = Long.MAX_VALUE;
		long sum = 0;
		int[] res = new int[3];

		// 용액 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 0; i < N; i++) {
			num[i] = Long.parseLong(stz.nextToken());
		}

		// 오름차순 정렬
		Arrays.sort(num);

		// 기준점 하나 잡고 나머지 2개 투포인터 탐색
		int start, end;
		
		for (int i = 0; i < N - 2; i++) {

			start = i + 1;
			end = N-1;

			while (start < end) {
				sum = num[i] + num[start] + num[end];

				if (minAbs > Math.abs(sum)) {
					minAbs = Math.abs(sum);
					res[0] = i;
					res[1] = start;
					res[2] = end;
				}

				if (sum < 0)
					start++;
				else
					end--;
			}

		}

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(num[res[0]]).append(' ').append(num[res[1]]).append(' ').append(num[res[2]]);
		System.out.println(sb);
	}
}
