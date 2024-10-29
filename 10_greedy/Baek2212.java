package algo241029;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2212 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int K = Integer.parseInt(bfr.readLine());
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		// 중계기 수가 센서 이상일 경우 0출력 후 종료
		if (N <= K) {
			System.out.println(0);
			return;
		}

		// 좌표 저장 배열
		int[] sensor = new int[N];

		// 좌표 차이 저장 배열
		int[] diff = new int[N - 1];

		int sum = 0;

		// 센서 좌표 입력받기
		for (int i = 0; i < N; i++)
			sensor[i] = Integer.parseInt(stz.nextToken());

		// 좌표 오름차순 정렬
		Arrays.sort(sensor);

		// 좌표 차이 계산
		for (int i = 0; i < N - 1; i++)
			diff[i] = sensor[i + 1] - sensor[i];

		// 차이 오름차순 정렬
		Arrays.sort(diff);

		// K개 집중국 = 집중국 사이 K-1번 빔. 큰거부터 K-1개 차이 뺴기
		for (int i = 0; i < N - K; i++)
			sum += diff[i];

		System.out.println(sum);

	}
}
