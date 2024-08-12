package algo240812;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2564 {
	static int R, C;
	// 1234북남서동의 반대
	static int[] otherside = { 0, 2, 1, 4, 3 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		R = Integer.parseInt(stz.nextToken());
		C = Integer.parseInt(stz.nextToken());
		int N = Integer.parseInt(bfr.readLine());
		int[][] arr = new int[N][2];

		// 가게 좌표 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			arr[i][0] = Integer.parseInt(stz.nextToken());
			arr[i][1] = Integer.parseInt(stz.nextToken());
		}

		// 동근이 위치 받기
		int[] dong = new int[2];
		stz = new StringTokenizer(bfr.readLine(), " ");
		dong[0] = Integer.parseInt(stz.nextToken());
		dong[1] = Integer.parseInt(stz.nextToken());

		// 동근이 위치 역산
		// 1234 북남서동
		int[] X = cal(dong[0], dong[1]);

		int sum = 0;
		// 상점 위치에 따른 거리 계산
		for (int i = 0; i < N; i++) {
			int[] now = cal(arr[i][0], arr[i][1]);

			// 상점이 동근이의 완전반대편이 아니라면
			if (arr[i][0] != otherside[dong[0]]) {
				int distNow = Math.abs(X[0] - now[0]) + Math.abs(X[1] - now[1]);
				sum += distNow;
			}
			// 상점이 동근이의 반대편이라면
			else {
				// 두 방향 중 짧은 쪽 선택
				int less = Math.min(dong[1] + arr[i][1], 2 * R - (dong[1] + arr[i][1]));

				// 남북이라면
				if (dong[0] <= 2)
					sum += less + C;

				// 동서라면
				else
					sum += less + R;

			}
		}
		System.out.println(sum);

	}

	static int[] cal(int dir, int dist) {
		int[] ans = { 0, 0 };

		// 북
		if (dir == 1) {
			ans[0] = dist;
		}
		// 남
		else if (dir == 2) {
			ans[0] = dist;
			ans[1] = C;
		}
		// 서
		else if (dir == 3) {
			ans[1] = dist;
		}
		// 동
		else {
			ans[0] = R;
			ans[1] = dist;
		}

		return ans;
	}
}
