package algo241115;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2531 {

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 접시 수, 메뉴 가짓수, 연속 수, 쿠폰번호
		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int D = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());

		int[] sushi = new int[N];
		int[] picked = new int[D + 1];

		int cnt = 0;

		// 초밥 받기
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(bfr.readLine());
			if (i < K) {
				if (picked[sushi[i]] == 0)
					cnt++;
				picked[sushi[i]]++;
			}
		}

		// 투포인터용 초기 설정
		int start = 0;
		int end = K - 1;
		int endpoint = N + K;

		int max = Integer.MIN_VALUE;

		// endpoint가 한바퀴 돌기 위해 N+K번 진행
		for (int i = 0; i < endpoint; i++) {

			// end 확장
			end = (end + 1) % N;

			if (picked[sushi[end]] == 0)
				cnt++;

			picked[sushi[end]]++;

			// start 축소
			picked[sushi[start]]--;

			if (picked[sushi[start]] == 0)
				cnt--;

			start = (start + 1) % N;

			// 쿠폰이 선택 안된 상태면 선택해서 최대값 갱신
			max = Math.max(max, picked[C] != 0 ? cnt : cnt + 1);

			// 할 수 있는 최대값에 도달시 조기종료
			if (max == K + 1) {
//				System.out.println("조기종료");
				break;
			}
		}

		System.out.println(max);
	}
}
