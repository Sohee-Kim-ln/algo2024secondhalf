package algo241024;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek16472Arr {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		String S = bfr.readLine();

		int[] cnt = new int[26];
		int size = 0;
		int max = Integer.MIN_VALUE;

		// 투포인터용 변수
		int left = 0;
		int right = 0;
		int length = 1;

		while (right < S.length()) {
			int now = S.charAt(right) - 'a';

			// 문자열에 포함된 문자이면 ++
			if (cnt[now] != 0)
				cnt[now]++;

			// 문자열에 포함되지 않은 문자이면
			else {
				// 카운트 불가능하면
				if (size >= N) {
					// 카운트 가능한 시점까지 left 이동
					while (size >= N) {
						int target = S.charAt(left) - 'a';

						// 한개 빼기
						cnt[target]--;

						if (cnt[target] == 0)
							size--;

						left++;
					}

				}

				// 현재 글자 카운트용 맵에 넣기
				cnt[now]++;
				size++;

			}

			// 길이 판별 후 최대 갱신
			length = right - left + 1;
			max = Math.max(max, length);

			right++;
		}

		System.out.println(max);
	}
}
