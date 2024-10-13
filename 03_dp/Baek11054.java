package algo241013;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek11054 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int[] num = new int[N];
		int[] dpl = new int[N];
		int[] dpr = new int[N];
		int max = Integer.MIN_VALUE;

		// 수열 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(stz.nextToken());

		// 점화식 dpl[i] = dpl[i보다 작은 수]+1
		// 왼쪽 최장 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				// 자신보다 더 작은 수의 dp+1
				if (num[j] < num[i])
					dpl[i] = Math.max(dpl[j] + 1, dpl[i]);
			}
		}

		// 오른쪽 최장 구하기, 역순
		for (int i = N - 1; i >= 0; i--) {
			for (int j = N - 1; j > i; j--) {
				if (num[j] < num[i]) {
					dpr[i] = Math.max(dpr[j] + 1, dpr[i]);
				}
			}

			max = Math.max(dpl[i] + dpr[i] + 1, max);
		}

//		System.out.println(Arrays.toString(dpl));
//		System.out.println(Arrays.toString(dpr));

		System.out.println(max);
	}
}
