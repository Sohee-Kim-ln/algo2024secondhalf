package algo240929;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek1744 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int[] arr = new int[N];
		int sum = 0;

		// 수열 입력받기
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(bfr.readLine());
		}

		// 정렬
		Arrays.sort(arr);

		// 포인터 초기화
		int pnt = N - 1;

		// 양수인 동안 큰 수부터 2개씩 판단하며 내려가기
		while (pnt >= 0 && arr[pnt] > 0) {
			int add = arr[pnt];
			int mult = arr[pnt];

			// 0이 아니면 계산
			if (pnt != 0) {
				add += arr[pnt - 1];
				mult *= arr[pnt - 1];
			}

			// 안묶는게 이득이면
			if (add >= mult) {
				sum += arr[pnt];
				pnt--;
			}
			// 묶는게 이득이면
			else {
				sum += mult;
				pnt -= 2;
			}
		}

		pnt = 0;

		// 음수인 동안 작은 수부터 2개씩 판단하며 올라가기
		while (pnt < N && arr[pnt] < 0) {
			int add = arr[pnt];
			int mult = arr[pnt];

			// N-1이 아니면 계산
			if (pnt != N - 1) {
				add += arr[pnt + 1];
				mult *= arr[pnt + 1];
			}

			// 안묶는게 이득이면
			if (add >= mult) {
				sum += arr[pnt];
				pnt++;
			}
			// 묶는게 이득이면
			else {
				sum += mult;
				pnt += 2;
			}
		}

		System.out.println(sum);
	}
}
