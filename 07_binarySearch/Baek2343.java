package algo240819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2343 {
	static int N, M;
	static int[] lesson;
	static int[] sum; // 0번부터 i번 인덱스까지의 합

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		lesson = new int[N + 1];
		sum = new int[N + 1]; // 0번부터 i번 인덱스까지의 합
		int max = Integer.MIN_VALUE;

		// 강의 정보 받기
		stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 1; i <= N; i++) {
			lesson[i] = Integer.parseInt(stz.nextToken());
			sum[i] = sum[i - 1] + lesson[i];
			max = Math.max(max, lesson[i]);
		}

		int left = max;
		int right = sum[N];
		int mid = -1;

		while (left <= right) {
			mid = (left + right) / 2;

			// 조건에 맞게 자를 수 있다면 블루레이 크기가 너무 큰 것이므로 줄여서 탐색
			if (canCutAs(mid))
				right = mid - 1;
			// 자르기 실패했으면
			else
				left = mid + 1;
		}
		System.out.println(left);

	}

	static boolean canCutAs(int length) {
		boolean result = true;

		int cnt = 0;
		int lastCut = 0;

		for (int i = 1; i <= N; i++) {
			// 현재 길이
			int now = sum[i] - sum[lastCut];

			// 블루레이 넘기거나 같으면
			if (now >= length) {
				// 블루레이 갯수++
				cnt++;

				// 자를 때 마지막 인덱스 저장
				if (now == length)
					lastCut = i;
				else
					lastCut = i - 1;

				// 블루레이 갯수가 M을 넘으면 강제종료
				if (cnt > M) {
					break;
				}
			}
		}

		if (lastCut != N)
			cnt++;

		// 블루레이 갯수가 M을 넘으면 자르기 실패
		if (cnt > M)
			result = false;

		return result;
	}
}
