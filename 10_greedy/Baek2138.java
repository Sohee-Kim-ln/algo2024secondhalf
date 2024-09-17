package algo240917;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek2138 {
	static int N;
	static boolean[] bulb, origin, target;
	static int cnt = 0;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());
		bulb = new boolean[N];
		origin = new boolean[N];
		target = new boolean[N];

		// 전구정보 입력받기
		char[] temp = bfr.readLine().toCharArray();
		for (int i = 0; i < N; i++) {
			bulb[i] = temp[i] == '1';
			origin[i] = bulb[i];
		}

		// 목표 정보 입력받기
		temp = bfr.readLine().toCharArray();
		for (int i = 0; i < N; i++) {
			target[i] = temp[i] == '1';
		}

		// 첫 스위치 안누르고 계산
		calc(false);

		// 첫 스위치 누르고 계산
		bulb = origin;
		calc(true);

		// 출력
		if (min == Integer.MAX_VALUE)
			min = -1;

		System.out.println(min);
	}

	static void calc(boolean zeroOn) {
		cnt = 0;

		if (zeroOn)
			push(0);

		// 1부터 N-1까지 이전 칸을 기준으로 누를지 결정
		for (int i = 1; i < N; i++) {
			if (target[i - 1] != bulb[i - 1])
				push(i);
		}

		// 성공했으면 최소값 갱신
		if (target[N - 1] == bulb[N - 1])
			min = Math.min(min, cnt);
	}

	// idx번 스위치 누르기
	static void push(int idx) {
		// 누른 횟수 증가
		cnt++;

		// 상태 전환
		if (idx - 1 >= 0)
			bulb[idx - 1] = !bulb[idx - 1];

		bulb[idx] = !bulb[idx];

		if (idx + 1 < N)
			bulb[idx + 1] = !bulb[idx + 1];
	}
}
