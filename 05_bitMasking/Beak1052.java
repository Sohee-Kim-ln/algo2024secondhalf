package algo240714;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 설계 18분 40초
public class Beak1052 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String[] tempS = bfr.readLine().split(" ");

		int N = Integer.parseInt(tempS[0]); // 현재 물 양
		int K = Integer.parseInt(tempS[1]); // 최대 옮길 수 있는 물병 수

		// N을 2진수로 바꾸면 합쳤을 때 어느 크기 물병이 존재 표시되는 상황
		// 앞자리부터 K-1개의 1을 제외한 후
		// 남은 수의 그 바로 윗자리 수 - 남은 수 전체 하면 필요한 물병이 나옴

		int water = N;

		int cnt = 0;

		int largest = 0;

		// K-1 만큼 윗자릿수들 없애기
		while (cnt < K - 1) {
			// 맨윗자릿수 찾기
			largest = Integer.highestOneBit(water);
			// 찾은 맨윗자리수 제거
			water = water - largest;
			cnt++;
		}

		// 1병으로 만들기 시작

		// 사야하는 물병
		int bottle = 0;

		// 남은 물이 1병이 아니라서 만들어야 한다면
		if (water - Integer.highestOneBit(water) != 0) {
			// 남은 물보다 1자리 큰 이진수에서 남은 물 빼기
			int big = (Integer.highestOneBit(water)) << 1;

			bottle = big - water;
		}

		// 남은 물이 1병이라면 물병 변수 초기화로 인해 자동으로 0

		System.out.println(bottle);

	}
}
