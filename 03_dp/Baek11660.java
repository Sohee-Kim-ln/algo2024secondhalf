package algo241214;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek11660 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		int[][] arr = new int[N + 1][N + 1];
		int[][] sum = new int[N + 1][N + 1];

		// 표 정보 받기
		for (int i = 1; i <= N; i++) {
			stz = new StringTokenizer(bfr.readLine());

			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(stz.nextToken());

				sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + arr[i][j];
			}
		}

		// 범위합 구하기 M번 수행
		for (int tc = 0; tc < M; tc++) {
			stz = new StringTokenizer(bfr.readLine());

			int x1 = Integer.parseInt(stz.nextToken());
			int y1 = Integer.parseInt(stz.nextToken());
			int x2 = Integer.parseInt(stz.nextToken());
			int y2 = Integer.parseInt(stz.nextToken());

			sb.append(sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1]);
			sb.append('\n');
		}
		
		// 출력
		System.out.print(sb);
	}
}
