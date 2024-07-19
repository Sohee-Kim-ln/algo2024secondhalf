package algo240719;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek14002 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		int[] arr = new int[N + 1];

		// 배열정보 입력받기
		String[] tempS = bfr.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(tempS[i - 1]);
		}

		// i를 길이로 갖는 부분수열들의 끝숫자 중 제일 작은 숫자
		int[] lis = new int[N + 1];
		int length = 0;

		// 이 숫자를 끝숫자로 하는 가장 긴 수열길이 저장
		int[] maxlen = new int[N + 1];

		// arr 배열 처음부터 끝까지 돌면서
		for (int i = 1; i <= N; i++) {
			// lis배열 끝수보다 크면 뒤에 연장
			if (lis[length] < arr[i]) {
				lis[++length] = arr[i];
				maxlen[i] = length;
			}

			// lis 배열 끝수보다 작으면 앞에서 교체
			else {
				// 이분탐색 lower bound
				int start = 1;
				int end = length;
				int mid = 0;
				int key = arr[i];

				while (start < end) {
					mid = (start + end) >> 1;

					if (lis[mid] < key)
						start = mid + 1;
					else
						end = mid;
				}

				lis[end] = key;
				maxlen[i] = end;
			}
		}

		// 만들어진 부분수열 저장 배열
		int[] arrNew = new int[length + 1];

		int lengNew = length;

		// 거꾸로 거슬러가면서 부분수열 배열 추적해서 만들기
		for (int i = N; i > 0; i--) {
			if (maxlen[i] == lengNew) {
				arrNew[lengNew] = arr[i];
				lengNew--;
			}
		}

		// 출력용 sb
		StringBuilder sb = new StringBuilder();

		// 만들어진 수열 앞부터 탐색출력
		for (int i = 1; i <= length; i++) {
			sb.append(arrNew[i]);
			if (i != length)
				sb.append(" ");
		}

		System.out.println(length);
		System.out.print(sb);
	}
}
