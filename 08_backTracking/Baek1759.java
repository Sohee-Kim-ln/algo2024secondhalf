package algo240720;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 49분 33초

public class Baek1759 {
	static StringBuilder sb = new StringBuilder();
	static StringBuilder sbAnswer = new StringBuilder();
	static boolean[] used;
	static int cntUsed = 0;

	static int L = 0;
	static char[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String[] tempS = bfr.readLine().split(" ");

		L = Integer.parseInt(tempS[0]);
		int C = Integer.parseInt(tempS[1]);

		// 문자 배열 받기
		arr = bfr.readLine().replaceAll(" ", "").toCharArray();
		Arrays.sort(arr);

		// 사용여부 배열 초기화
		used = new boolean[C];

		make(0, 0);

		System.out.println(sbAnswer);
	}

	static void make(int index, int cntVowel) {
		// 암호 길이에 도달하면 sb에 담기
		if (cntUsed == L) {
			// 모음 1개 이상, 자음 2개 이상일 때 답 저장
			if (cntVowel >= 1 && (L - cntVowel) >= 2) {
				if (sbAnswer.length() != 0)
					sbAnswer.append("\n");

				for (int i = 0; i < arr.length; i++) {
					if (used[i]) {
						sbAnswer.append(arr[i]);
					}
				}
			}
			return;
		}

		// 계산해야할 인덱스가 arr 범위를 넘으면 리턴
		if (index >= arr.length)
			return;

		// 사용 했을 때 표시
		used[index] = true;
		cntUsed++;

		// 모음이면
		if (isVowel(arr[index]))
			make(index + 1, cntVowel + 1);
		// 자음이면
		else
			make(index + 1, cntVowel);

		// 사용 안했을 때
		used[index] = false;
		cntUsed--;

		make(index + 1, cntVowel);

	}

	static boolean isVowel(char input) {
		if (input == 'a' || input == 'e' || input == 'i' || input == 'o' || input == 'u')
			return true;
		return false;
	}
}
