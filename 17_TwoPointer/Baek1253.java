package algo241117;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1253 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		int[] num = new int[N];
		int cnt = 0;

		// 숫자 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(stz.nextToken());
		}

		// 투포인터용 정렬
		Arrays.sort(num);

		// 모든 수를 목표로 두고 탐색
		for (int i = 0; i < N; i++) {

			// 투포인터용 변수
			int left = 0;
			int right = N - 1;

			// 투포인터
			while (left < N && right >= 0) {
				// 자기 자신 제외
				if (left == i)
					left++;

				if (right == i)
					right--;

				// 종료조건
				if (left >= right)
					break;

				// 값 비교 후 포인터 이동
				if (num[left] + num[right] < num[i])
					left++;
				else if (num[left] + num[right] > num[i])
					right--;
				else {
					cnt++;
					break;
				}
			}
		}

		System.out.println(cnt);
	}
}
