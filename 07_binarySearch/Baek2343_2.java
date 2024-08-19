package algo240819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2343_2 {
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

		// 이분탐색용 변수
		int left = max;
		int right = sum[N];
		int mid = -1;

		// 이분탐색 시작
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

		int sum = 0;
		int cnt = 0;

		for (int i = 1; i <= N; i++) {
			// 현재 강의 합하면 블루레이 넘길 때
			if (sum + lesson[i] > length) {
				sum = 0;
				cnt++;
			}

			// 블루레이 갯수 초과시 확인 종료
			if (cnt > M)
				break;

			// 현재 강의 길이 더함
			// sum이 초기화된 상태면 현재 강의가 해당 블루레이 맨앞
			sum += lesson[i];
		}

		// 마지막 블루레이 세기
		cnt++;

		// 블루레이 갯수가 M을 넘으면 자르기 실패
		if (cnt > M)
			result = false;

		return result;
	}
}
