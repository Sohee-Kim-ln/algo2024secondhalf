package algo241115;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2531_fail2 {
	static final int maxNum = 3001;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 접시 수, 메뉴 가짓수, 연속 수, 쿠폰번호
		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int D = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());

		int[] sushi = new int[N];
		boolean[] picked = new boolean[maxNum];

		// 초밥 받기
		for (int i = 0; i < N; i++)
			sushi[i] = Integer.parseInt(bfr.readLine());

		// 투포인터용 초기 설정
		int start = 0;
		int end = 0;
		int length = 1;
		picked[sushi[start]] = true;

		int endpoint = N + K;
		int cnt = 0;

		int max = Integer.MIN_VALUE;

		// 종료조건 같으면 진행, s가 처음빼고 0이면 종료
		while (cnt < endpoint) {
//			System.out.println("test " + cnt);

			if (start == end && start != 0) {
//				System.out.println("종료조건");
				break;
 
			}

			// end 확장
			end = (end + 1) % N;
			cnt++;

			// 이미 있는 초밥이면
			if (picked[sushi[end]]) {
//				System.out.println("중복초밥");
				// 겹치는거까지 start 당김
				while (sushi[start] != sushi[end]) {
					start = (start + 1) % N;
					picked[sushi[start]] = false;
				}

				// 안겹치게 한번 더
				start = (start + 1) % N;
				picked[sushi[start]] = true;

			}
			// 없는 초밥이면 표시

			picked[sushi[end]] = true;

			// 길이 갱신
			length = end - start + 1;
			if (length < 0)
				length += N;
//			System.out.println(start + " " + end);

			// 길이 K상태
			// 쿠폰이 선택 안된 상태면 선택해서 최대값 갱신
			max = Math.max(max, picked[C] ? length : length + 1);

			// 할 수 있는 최대값에 도달시 조기종료
			if (max == K + 1) {
//				System.out.println("조기종료");
				break;
			}

			// 다음 연산을 위헤 start당기기
			if (length == K) {
				picked[sushi[start]] = false;
				start = (start + 1) % N;

				// 이거 없어도 될거가은데
				length = end - start + 1;
				if (length < 0)
					length += N;
			}
		}

		System.out.println(max);
//		for (int i = 0; i < N; i++) {
//			if (picked[sushi[i]])
//				System.out.println(i + "번 " + sushi[i]);
//			else
//				System.out.println(i + "번 " + sushi[i] + " 선택 안됨");
//		}
	}
}
