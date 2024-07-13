package prog240713;

import java.util.*;
import java.io.*;

// 시간측정 안함
public class ProgPonkemon {
	public static void main(String[] args) throws IOException {
		HashMap<Integer, Integer> pokemap = new HashMap<>();

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 배열을 스트링으로 받기
		String[] pokes = bfr.readLine().split(",");

		// 포켓몬 마리수
		int pokelength = pokes.length;

		// 숫자배열로 변환
		int[] pokei = new int[pokelength];

		for (int i = 0; i < pokelength; i++) {
			if (i != 0 && i != pokelength - 1) {
				pokei[i] = Integer.parseInt(pokes[i]);
			} else {
				pokei[i] = Integer.parseInt(pokes[i].replace("[", "").replace("]", ""));
			}
		}

		// 카운터 배열 및 가짓수
		int[] pokeCount = new int[200000 + 1]; // 0 사용 안함
		int count = 0;

		// 새로운 종류면 카운트 추가
		for (int i = 0; i < pokelength; i++) {
			if (pokeCount[pokei[i]] == 0) {
				pokeCount[pokei[i]]++;
				count++;
			}
		}

		int answer = Math.min(count, pokelength / 2);

		System.out.println(answer);
	}
}
