package algo240717;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 설계포함 1시간 15분 32초

public class Baek12015 {

	static int[] lis;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 수열 길이
		int N = Integer.parseInt(bfr.readLine());

		String[] tempS = bfr.readLine().split(" ");

		// 수열 정보, 0안씀
		int[] arr = new int[N + 1];

		// 수열 정보 저장
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(tempS[i - 1]);
		}

		// 길이가 i인 부분수열의 마지막 수 중 가장 작은 수
		lis = new int[N + 1];

		// 길이가 1인 부분수열 초기값 설정
		lis[1] = arr[1];

		// 고려중인 현재 위치
		int curr = 1;

		// 최장 증가 부분수열의 마지막 인덱스
		int last = 1;

		// 길이 2부터 계산 시작을 위해 ++;
		curr++;

		// 맨 앞부터 시작해서 주어진 수열 끝까지 돌 때까지 반복
		while (curr <= N) {
			// 부분수열 맨 마지막보다 현재수가 크면 연장
			if (lis[last] < arr[curr]) {
				last++;
				lis[last] = arr[curr];

			}
			// 부분수열 맨 마지막보다 현재 수가 작으면
			else {
				// 이분탐색으로 lis에서 대체가능한 수 찾기
				int start = 1;
				int end = last;
				int mid = 0;
				while (start < end) {
					mid = (start + end) / 2;

					if (lis[mid] >= arr[curr])
						end = mid;

					else
						start = mid + 1;

				}

				lis[start] = arr[curr];

			}

			curr++;
		}

		System.out.println(last);
	}

}
