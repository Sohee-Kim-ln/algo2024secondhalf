package algo241023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 14040KB 624ms
public class Baek2473_BinarySearch {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		long[] num = new long[N];

		long minAbs = Long.MAX_VALUE;
		long sum;
		int[] res = new int[3];

		// 용액 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(stz.nextToken());
		}

		// 오름차순 정렬
		Arrays.sort(num);

		// 이분탐색용 임시변수
		int start, end, mid;

		// 2개 선택 후
		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {

				start = j + 1;
				end = N - 1;

				// 나머지 1개 이분탐색
				while (start <= end) {
					mid = (start + end) >> 1;
					sum = num[i] + num[j] + num[mid];

					// 최소 절댓값 시 갱신
					if (minAbs > Math.abs(sum)) {
						minAbs = Math.abs(sum);
						res[0] = i;
						res[1] = j;
						res[2] = mid;
					}

					if (sum < 0)
						start = mid + 1;
					else if (sum > 0)
						end = mid - 1;
					else
						break;
				}
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(num[res[0]]).append(' ').append(num[res[1]]).append(' ').append(num[res[2]]);
		System.out.println(sb);
	}
}
