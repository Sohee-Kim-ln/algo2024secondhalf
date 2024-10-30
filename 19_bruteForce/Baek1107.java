package algo241030;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1107 {
	static boolean[] broken;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int M = Integer.parseInt(bfr.readLine());
		broken = new boolean[10];

		int min = Math.abs(N - 100);

		// 이미 목적채널 이거나 || 숫자가 전부 고장났으면 +- 결과 출력
		if (N == 100 || M == 10) {
			System.out.println(min);
			return;
		}

		// 고장난 버튼 정보 받기
		if (M != 0) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

			for (int i = 0; i < M; i++)
				broken[Integer.parseInt(stz.nextToken())] = true;
		}

		int diff = 0;
		int now;
		int pushed;
		boolean isChanged = false;

		// N에서 차이 1씩 늘려가며 가능여부 확인
		while (!isChanged) {

			// 더했을 때
			now = N + diff;
			pushed = pushCount(now);

			// 누를 수 있는지 검사 후 갱신
			if (pushed != -1) {
				min = Math.min(min, diff + pushed);
				isChanged = true;
			}

			// 뺐을 때
			now = N - diff;
			pushed = pushCount(now);

			// 누를 수 있는지 검사 후 갱신
			if (now >= 0 && pushed != -1) {
				min = Math.min(min, diff + pushed);
				isChanged = true;
			}

			diff++;
		}

		System.out.println(min);

	}

	// 이 숫자를 누를 수 있는지 검사
	static int pushCount(int input) {

		boolean isPossible = true;
		int cnt = 0;

		// 0만 들어왔다면 고장여부 따라 결과 저장
		if (input == 0) {
			if (broken[input])
				isPossible = false;
			else
				cnt = 1;
		}

		if (input < 0)
			isPossible = false;

		// 뒷자리 부터 숫자 떼서 누를 수 있는지 검사
		while (input > 0) {
			int num = input % 10;
			input /= 10;
			cnt++;

			if (broken[num]) {
				isPossible = false;
				break;
			}
		}

		return isPossible ? cnt : -1;
	}
}
