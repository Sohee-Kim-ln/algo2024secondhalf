package algo240915;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2110 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		int N = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());

		int[] house = new int[N];
		int res = 0;

		// 집 정보 받기
		for (int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(bfr.readLine());
		}

		// 좌표 순으로 집 정렬
		Arrays.sort(house);

		int start = 1;
		int end = house[N - 1] - house[0];
		int mid;

		// 거리에 대해 이분탐색
		while (start <= end) {
			mid = (start + end) / 2;

			// 첫집 설치
			int cnt = 1; // 설치 수
			int last = 0; // 직전 설치 인덱스

			for (int i = 1; i < N; i++) {
				// 설치할 수 있으면
				if (house[i] - house[last] >= mid) {
					last = i;
					cnt++;

					if (cnt >= C)
						break;
				}
			}

			// C개 이상 설치 가능하면 간격 늘려보기
			if (cnt >= C) {
				start = mid + 1;
				res = mid;
			}
			// C개 설치 불가능하면 간격 줄여보기
			else
				end = mid - 1;

		}
		System.out.println(res);

	}
}
