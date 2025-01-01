package algo250101;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1011 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());

		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine());

			// 거리 입력받기
			int X = Integer.parseInt(stz.nextToken());
			int Y = Integer.parseInt(stz.nextToken());
			int dist = Y - X;

			// 홀수 번 이동 2x-1 최대거리 = x(x+1)/2*2 -x = x^2
			// 짝수 번 이동 2x 최대거리 = x(x+1)/2*2 = x(x+1)

			int sqrt = (int) Math.sqrt(dist);

			// 홀수번이고 제곱과 일치
			if (dist == sqrt * sqrt)
				sb.append((sqrt << 1) - 1);
			// 짝수번
			else if (sqrt * sqrt < dist && dist <= sqrt * (sqrt + 1))
				sb.append(sqrt << 1);
			// 홀수번이고 큼
			else
				sb.append((sqrt << 1) + 1);

			sb.append('\n');
		}

		// 출력
		System.out.print(sb);
	}
}
