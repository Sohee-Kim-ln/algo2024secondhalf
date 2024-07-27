package algo240727;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;

// 설계 1분, 구현 48분 17초. 구현 시 익숙하지 않은 MessageFormat을 사용하면서 구현 지연

public class Baek4659 {
	static String form = "<{0}> is{1} acceptable.";
	static char[] vowels = { 'a', 'e', 'i', 'o', 'u' };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String password = "";

		boolean hasVowel;
		int vowelChain = 0;
		int notVowelChain = 0;
		char prev;
		boolean isAvailable = true;

		while (true) {
			// 변수 초기화
			hasVowel = false;
			vowelChain = 0;
			notVowelChain = 0;
			prev = '0';
			isAvailable = true;

			// 비번 입력받기
			password = bfr.readLine();
			char[] tc = password.toCharArray();
			
			// 종료 명령어 판별
			if(password.equals("end"))
				break;

			for (int i = 0; i < tc.length; i++) {
				char now = tc[i];

				// 이전 글자와 같고
				if (now == prev) {
					// 연속이 허용되지 않는 글자라면
					if (now != 'e' && now != 'o') {
						isAvailable = false;
						break;
					}
				}

				// 모음이라면
				if (isVowel(now)) {
					// 보유 여부 표시
					if (!hasVowel)
						hasVowel = true;

					// 모음연속++. 이전이 자음일 경우 0으로 초기화시킬 것이므로 신경X
					vowelChain++;

					// 모음이 3연속이면 fail
					if (vowelChain >= 3) {
						isAvailable = false;
						break;
					}

					// 자음연속이 있었으면
					if (notVowelChain > 0)
						notVowelChain = 0;

				}
				// 자음이라면
				else {
					// 자음연속++
					notVowelChain++;

					// 자음이 3연속이면 fail
					if (notVowelChain >= 3) {
						isAvailable = false;
						break;
					}

					// 모음연속이 있었으면
					if (vowelChain > 0)
						vowelChain = 0;

				}
				prev = now;
			}

			// 모음 없으면 fail
			if (!hasVowel)
				isAvailable = false;

			String not;

			// 가능여부에 따라 문자열 작성 후 출력
			if (isAvailable)
				not = "";
			else
				not = " not";

			System.out.println(MessageFormat.format(form, password, not));

		}
	}

	// 모음여부 판단
	static boolean isVowel(char input) {
		for (int i = 0; i < vowels.length; i++)
			if (input == vowels[i])
				return true;
		return false;
	}
}
