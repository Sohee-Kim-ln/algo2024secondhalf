package algo241021;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek5904 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int[] size = new int[28];

		// 초기값 설정
		// S(n) = S(n-1)*2+ 3+n; (n==0일 때 제외)
		size[0] = 3;

		int part = -1;
		int order = N;

		char ans = 'a';

		int idx = 0;

		// N번째를 포함하는 Moo 수열 찾기
		while (size[idx] < N) {
			idx++;
			size[idx] = (size[idx - 1] << 1) + idx + 3;
		}

		// 수열번호, 파트번호에 따른 글자 판별 시작
		while (idx > 0) {

			// 몇번째 파트인지 판별
			if (order < size[idx - 1])
				part = 0;
			else if (order <= size[idx - 1] + idx + 3)
				part = 1;
			else
				part = 2;

			// 가운데 파트라면 글자 지정 후 종료
			if (part == 1) {
				if (order == size[idx - 1] + 1)
					ans = 'm';
				else
					ans = 'o';

				break;
			}

			// 뒤파트라면 idx-1 수열에서의 순서 계산
			if (part == 2)
				order -= size[idx - 1] + idx + 3;

			// 수열 번호 줄이기
			idx--;

		}

		// 가운데가 아닌채로 종료됐다면 지정
		if (idx == 0)
			ans = order == 1 ? 'm' : 'o';

		System.out.println(ans);

	}
}
