package algo240718;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 23분 9초

public class Baek12738 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		// 수열 정보 저장용. 0 안씀
		int[] arr = new int[N + 1];

		// 수열 정보 입력받기
		String[] tempS = bfr.readLine().split(" ");

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(tempS[i - 1]);
		}

		// i만큼의 길이를 가지는 부분 수열 중 최저값 저장. 0 안씀
		int[] lis = new int[N + 1];

		// 수열 원소의 범위가 -1,000,000,000 이므로 int 최저값으로 초기화
		Arrays.fill(lis, Integer.MIN_VALUE);

		// 길이 1 인 최장부분수열 초기값 설정
		lis[1] = arr[1];
		int length = 1;

		for (int i = 2; i <= N; i++) {
			// 부분수열 마지막보다 크다면 뒤에 추가
			if (lis[length] < arr[i]) {
				lis[++length] = arr[i];
			}
			// 부분수열 마지막보다 작다면 앞에서 교체. lower bound로.
			else {
				int start = 1;
				int end = length;
				int mid = 0;
				int key = arr[i];
				while (start < end) {
					mid = (start + end) / 2;

					if (lis[mid] < key)
						start = mid + 1;
					else
						// if(lis[mid]>=key)
						end = mid;
				}
				// 찾은 지점에 넣기
				lis[end] = key;
			}

		}

		System.out.println(length);

	}
}
