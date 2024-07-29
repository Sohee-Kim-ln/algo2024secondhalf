package algo240729;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek2961 {
	static int mask = 0;
	static int N;

	static int[] sours;
	static int[] bitters;

	static long min = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 재료 갯수
		N = Integer.parseInt(bfr.readLine());

		sours = new int[N];
		bitters = new int[N];

		// 맛 정보 입력받기
		String[] tempS;
		for (int i = 0; i < N; i++) {
			tempS = bfr.readLine().split(" ");
			sours[i] = Integer.parseInt(tempS[0]);
			bitters[i] = Integer.parseInt(tempS[1]);

		}
		
		// 요리 시작
		cook(0);
		
		System.out.println(min);

	}

	static void cook(int num) {
		int prevMask = mask;
		// 끝까지 다 고려 끝났을 때
		if (num >= N) {
			// 어느 재료도 사용되지 않았으면 종료
			if (mask == 0)
				return;

			// 신맛은 곱
			long sour = 1;

			// 쓴맛은 합
			long bitter = 0;

			// 사용된 재료들의 맛 계산
			for (int i = 0; i < N; i++) {
				if ((mask & (1 << i)) != 0) {
					sour *= sours[i];
					bitter += bitters[i];
				}
			}

			// 신 쓴 맛 차이 구하기
			long diff = Math.abs(sour - bitter);
			System.out.println(Integer.toBinaryString(mask)+" 신"+ sour+" 쓴"+bitter+" 차이 "+ diff);

			// 최소값이면 저장
			min = min < diff ? min : diff;
			
			return;
		}
		// i를 요리했을 때
		mask = mask ^ (1 << num);
		cook(num + 1);

		mask = prevMask;
		// i를 요리하지 않았을 때
		cook(num + 1);

	}
}
