package algo241024;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Baek16472Map {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		String S = bfr.readLine();

		Map<Character, Integer> cnt = new HashMap<>();

		int max = Integer.MIN_VALUE;

		// 투포인터용 변수
		int left = 0;
		int right = 0;
		int length = 1;

		while (right < S.length()) {
			char now = S.charAt(right);

			// 문자열에 포함된 문자이면 ++
			if (cnt.containsKey(now))
				cnt.replace(now, cnt.get(now) + 1);

			// 문자열에 포함되지 않은 문자이면
			else {
				// 카운트 불가능하면
				if (cnt.size() >= N) {
					// 카운트 가능한 시점까지 left 이동
					while (cnt.size() >= N) {
						char target = S.charAt(left);

						// 한개 빼기
						cnt.replace(target, cnt.get(target) - 1);

						if (cnt.get(target) == 0)
							cnt.remove(target);

						left++;
					}

				}

				// 현재 글자 카운트용 맵에 넣기
				cnt.put(now, 1);

			}

			// 길이 판별 후 최대 갱신
			length = right - left + 1;
			max = Math.max(max, length);

			right++;
		}

		System.out.println(max);
	}
}
