package algo241224;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek10986 {

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		long cnt = 0;
		// 0부터 i까지 누적합
		int[] sum = new int[N];
		int[] cntMod = new int[M];

		// 누적합 정보 받기
		stz = new StringTokenizer(bfr.readLine());

		sum[0] = Integer.parseInt(stz.nextToken()) % M;
		cntMod[sum[0]]++;

		// mod 계산 및 해당 나머지값을 가지는 0-i 누적합 카운트+;
		for (int i = 1; i < N; i++) {
			sum[i] = (sum[i - 1] + Integer.parseInt(stz.nextToken())) % M;
			cntMod[sum[i]]++;
		}

		// 0부터 i까지로 조건에 해당되는 것들
		cnt = cntMod[0];

		// 같은 나머지값 가지는 인덱스끼리 빼면 무조건 0
		// n개중 2개 고르기 Combination 계산
		for (int i = 0; i < M; i++) {
			// 해당 나머지 값을 가지는 것 중 2개 고르기
			if (cntMod[i] >= 2) {
				long now = (long) cntMod[i];
				cnt += (now * (now - 1)) / 2;
			}
		}

		System.out.println(cnt);
	}
}
