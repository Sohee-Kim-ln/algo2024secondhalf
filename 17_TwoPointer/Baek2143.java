package algo250102;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2143 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		Long T = Long.parseLong(bfr.readLine());

		// A배열 변수
		int N = Integer.parseInt(bfr.readLine());

		int[] A = new int[N];
		int sizeA = N * (N + 1) / 2;
		long[] sumA = new long[sizeA];
		// A배열 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(stz.nextToken());
		}

		// A 부배열
		int idx = 0;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = i; j < N; j++) {
				sum += A[j];
				sumA[idx++] = sum;
			}
		}

		// B배열 변수
		int M = Integer.parseInt(bfr.readLine());

		int[] B = new int[M];
		int sizeB = M * (M + 1) / 2;
		long[] sumB = new long[sizeB];

		// B배열 정보 받기
		stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(stz.nextToken());
		}

		// B 부배열
		idx = 0;
		for (int i = 0; i < M; i++) {
			int sum = 0;
			for (int j = i; j < M; j++) {
				sum += B[j];
				sumB[idx++] = sum;
			}
		}

		// 부배열 정렬
		Arrays.sort(sumA);
		Arrays.sort(sumB);

		// 투포인터 변수
		long cnt = 0;
		int left = 0;
		int right = sizeB - 1;

		// 투포인터
		while (left < sizeA && right >= 0) {
			long nowA = sumA[left];
			long nowB = sumB[right];
			long sum = nowA + nowB;
			
			if (sum > T)
				right--;
			else if (sum < T)
				left++;
			// 합이 T라면 (sum == T)
			else {
				long cntA = 0;
				long cntB = 0;

				// 같은 수 A에서 카운트 및 진행
				while (left < sizeA && nowA == sumA[left]) {
					left++;
					cntA++;
				}

				// 같은 수 B에서 카운트 및 진행
				while (right >= 0 && nowB == sumB[right]) {
					right--;
					cntB++;
				}

				// 몇쌍인지 곱해서 저장
				cnt += cntA * cntB;
			}
		}

		// 출력
		System.out.println(cnt);
	}
}
