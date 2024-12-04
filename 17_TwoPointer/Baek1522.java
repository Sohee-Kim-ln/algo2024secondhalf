package algo241204;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek1522 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String origin = bfr.readLine();
		int length = origin.length();

		boolean[] isA = new boolean[length];
		int cntA = 0;
		int min = Integer.MAX_VALUE;

		// a갯수 세면서 boolean 변환
		for (int i = 0; i < length; i++) {
			if (origin.charAt(i) == 'a') {
				isA[i] = true;
				cntA++;
			}
		}

		// a 연속의 길이로 잘랐을 때 b의 갯수
		int cntB = 0;

		// a 연속길이 만큼에서의 b 첫번째 계산
		for (int i = 0; i < cntA; i++) {
			if (!isA[i])
				cntB++;
		}

		min = cntB;

		int start = 0;
		int end = cntA - 1;

		// 두번째 이후 계산
		while (start < length) {
			// 시작점 옮기기 전 B면 --
			if (!isA[start])
				cntB--;

			// 시작 끝 한칸씩 이동
			start++;
			end = (end + 1) % length;

			// 새로 더해진 글자가 B면 ++
			if (!isA[end])
				cntB++;

			// 최솟값 갱신
			min = Math.min(min, cntB);
		}

		// 출력
		System.out.println(min);
	}
}
