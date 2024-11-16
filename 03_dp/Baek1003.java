package algo241116;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Baek1003 {

	// 메모라이제이션용 Map
	static Map<Integer, int[]> dp = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(bfr.readLine());

		// 초기값인 fibo(0), fibo(1) 저장
		dp.put(0, new int[] { 1, 0 });
		dp.put(1, new int[] { 0, 1 });

		// 각 테케에 대해
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(bfr.readLine());

			// N 계산
			int[] now = fibo(N);

			sb.append(now[0]).append(' ').append(now[1]).append("\n");

		}

		// 출력
		System.out.print(sb);

	}

	// N번쨰 피보나치 수 계산
	static int[] fibo(int N) {

		// 이미 계산돼있으면 바로 리턴
		if (dp.containsKey(N))
			return dp.get(N);

		int[] prev1 = fibo(N - 1);
		int[] prev2 = fibo(N - 2);

		// 점화식 fibo(N) = fibo(N-1)+fibo(N-2);
		int[] now = new int[] { prev1[0] + prev2[0], prev1[1] + prev2[1] };

		// 계산 결과 저장
		dp.put(N, now);

		return now;
	}
}
