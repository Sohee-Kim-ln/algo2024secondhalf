package algo241222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek15565 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		int lion = 0;
		int[] arr = new int[N];
		int idx = 0;
		int min = Integer.MAX_VALUE;

		// 인형들 정보 받기
		String tempS = bfr.readLine();

		for (int i = 0; i < N; i++) {
			// i번째 글자가 1이라 라이언이면
			if (tempS.charAt(i << 1) == '1') {
				arr[idx] = i;
				idx++;
				lion++;
			}
		}

		// 라이언 인형수가 모자라면 종료
		if (lion < K) {
			System.out.println(-1);
			return;
		}

		int left = 0;
		int right = K - 1;

		// 전체 탐색
		for (; right < idx; right++) {
			left = right - K + 1;
			min = Math.min(min, arr[right] - arr[left] + 1);
		}

		// 출력
		System.out.println(min);

	}
}
