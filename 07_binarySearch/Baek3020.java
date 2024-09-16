package algo240916;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek3020 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		int N = Integer.parseInt(stz.nextToken());
		int H = Integer.parseInt(stz.nextToken());

		int half = N >> 1;

		int[] down = new int[half + 2];
		int[] up = new int[half + 2];

		int min = Integer.MAX_VALUE;
		int cnt = 0;

		// 동굴 정보 받기
		for (int i = 0; i < half; i++) {
			down[i] = Integer.parseInt(bfr.readLine());
			up[i] = Integer.parseInt(bfr.readLine());
		}
		down[half] = 0;
		down[half + 1] = H;
		up[half] = 0;
		up[half + 1] = H;

		// 크기 별 정렬
		Arrays.sort(down);
		Arrays.sort(up);

		int start, end, res, mid = -1;

		// 모든 구간에 대해 계산
		for (int h = 1; h <= H; h++) {
			// 석순에 대해 이분 탐색
			start = 0;
			end = half + 1;
			res = 0;

			// 자를 수 있는 가장 작은 인덱스 찾기
			while (start < end) {
				mid = (start + end) / 2;

				// 자르면 포함해서 앞쪽 탐색
				if (down[mid] >= h)
					end = mid;
				else
					start = mid + 1;
			}

			res += half - end + 1;

			// 종유석에 대해 이분 탐색
			start = 0;
			end = half + 1;

			// 자를 수 있게 되는 가장 작은 인덱스 찾기
			while (start < end) {
				mid = (start + end) / 2;

				// 자르면 포함해서 앞쪽 탐색
				if (up[mid] > H - h)
					end = mid;
				else
					start = mid + 1;
			}

			res += half - end + 1;

			// 최소 결과 시 갱신 혹은 카운트
			if (res == min)
				cnt++;
			else if (res < min) {
				cnt = 1;
				min = res;
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(min).append(" ").append(cnt);
		System.out.println(sb);
	}
}
