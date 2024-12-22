package algo241222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek15565Fail {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		boolean[] isLion = new boolean[N];
		int lion = 0;

		ArrayDeque<Integer> adq = new ArrayDeque<>();

		// 인형들 정보 받기
		String tempS = bfr.readLine();

		for (int i = 0; i < N; i++) {
			// i번째 글자가 1이라 라이언이면
			if (tempS.charAt(i << 1) == '1') {
				isLion[i] = true;
				lion++;
				adq.add(i);
			}

		}

		// 라이언 인형수가 모자라면 종료
		if (lion < K) {
			System.out.println(-1);
			return;
		}

		int left = adq.pollFirst();
		int right = adq.isEmpty() ? left : adq.pollLast();

		// 라이언이 K보다 많을동안 반복
		while (lion > K) {
			int nl, nr;

			// 큐 상황따라 다음 left right 지정
			if (adq.isEmpty()) {
				nl = right;
				nr = left;
			} else {
				nl = adq.peekFirst();
				nr = adq.peekLast();
			}

			// 더 긴쪽 제거
			if (nl - left >= right - nr) {
				left = nl;
				if (!adq.isEmpty())
					adq.pollFirst();
				lion--;
			} else {
				right = nr;
				if (!adq.isEmpty())
					adq.pollLast();
				lion--;
			}
		}

		// 출력
		System.out.println(right - left + 1);

	}
}
