package algo240905;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek7453 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int NN = N * N;
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];

		int[] AB = new int[NN];
		int[] CD = new int[NN];

		// 4줄 정보 받기
		StringTokenizer stz;
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			A[i] = Integer.parseInt(stz.nextToken());
			B[i] = Integer.parseInt(stz.nextToken());
			C[i] = Integer.parseInt(stz.nextToken());
			D[i] = Integer.parseInt(stz.nextToken());
		}

		// 2줄씩 묶어서 합 계산 후 저장
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[index] = A[i] + B[j];
				CD[index] = C[i] + D[j];
				index++;
			}
		}

		// 오름차순 정렬
		Arrays.sort(AB);
		Arrays.sort(CD);

		// 투포인터용 변수
		int pointerAB = 0;
		int pointerCD = NN - 1;
		long res = 0;
		int startAB, startCD;

		// 각 배열에 포인터 두고 투포인터 진행

		while (pointerAB < NN && pointerCD >= 0) {
			int sum = AB[pointerAB] + CD[pointerCD];

			// 합이 크면 더할 값 cd--로 내리기
			if (sum > 0)
				pointerCD--;
			// 합이 작으면 더할 값 ab++로 올리기
			else if (sum < 0)
				pointerAB++;
			// 합이 0이 될 때
			else {
				// AB에서 같은 수 스킵
				startAB = pointerAB;
				while (pointerAB < NN && AB[startAB] == AB[pointerAB])
					pointerAB++;

				// CD에서 같은 수 스킵
				startCD = pointerCD;
				while (pointerCD >= 0 && CD[startCD] == CD[pointerCD])
					pointerCD--;
				
				// 스킵된 갯수들로 만들 수 있는 조합 계산
				res += (long)(pointerAB - startAB) * (long)(startCD - pointerCD);
			}

		}

		System.out.println(res);

	}
}
