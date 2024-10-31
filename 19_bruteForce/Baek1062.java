package algo241031;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1062 {
	static int N, K;
	static int learn; // 배운 알파벳 비트마스킹
	static int[] word; // 필요한 알파벳 비트마스킹
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		N = Integer.parseInt(stz.nextToken());
		K = Integer.parseInt(stz.nextToken());

		learn = 0;
		word = new int[N];

		// 필수 글자보다 글자수가 작으면
		if (K < 5) {
			System.out.println(0);
			return;
		}

		// 필수글자 기록
		learn |= 1 << ('a' - 'a');
		learn |= 1 << ('n' - 'a');
		learn |= 1 << ('t' - 'a');
		learn |= 1 << ('i' - 'a');
		learn |= 1 << ('c' - 'a');
		K -= 5;

		// 단어 받기
		for (int i = 0; i < N; i++) {
			String tempS = bfr.readLine();

			tempS = tempS.substring(4, tempS.length() - 4);

			// 배워야 하는 단어 체크
			for (int j = 0; j < tempS.length(); j++) {
				word[i] |= 1 << (tempS.charAt(j) - 'a');
			}
		}

		// 0번 인덱스 글자부터 시작, 현재 배운글자 0개
		teach(0, 0);

		System.out.println(max);

	}

	// idx번째 글자 체크할 차례, 배운 글자수 letter
	static void teach(int idx, int letter) {

		// 다 배웠으면
		if (letter == K) {
			int cnt = 0;

			// 현재 배울 수 있는 단어 갯수 확인
			for (int i = 0; i < N; i++) {
				// 단어의 모든 비트가 learn에도 있으면 ++
				if ((learn & word[i]) == word[i])
					cnt++;
			}

			max = Math.max(max, cnt);
			return;
		}

		// 다 배우지 않았으면
		for (int i = idx; i < 26; i++) {
			// 배우지 않은 글자라면
			if ((learn & (1 << i)) == 0) {
				// 백트래킹
				learn |= (1 << i);
				teach(i, letter + 1);
				learn &= ~(1 << i);
			}
		}

	}
}
