package algo241111;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1865_2 {
	static final int INF = 500 * 10000;

	static class Road {
		int s, e, t;

		Road(int s, int e, int t) {
			this.s = s;
			this.e = e;
			this.t = t;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer stz;

		int TC = Integer.parseInt(bfr.readLine());

		// 각 테케에 대해
		for (int tc = 0; tc < TC; tc++) {
			stz = new StringTokenizer(bfr.readLine());
			int N = Integer.parseInt(stz.nextToken());
			int M = Integer.parseInt(stz.nextToken());
			int W = Integer.parseInt(stz.nextToken());
			int length = 2 * M + W;

			Road[] roads = new Road[length];

			// i->j 소요시간
			int[] time = new int[N + 1];

			Arrays.fill(time, INF);
			time[1] = 0;

			// 도로 정보 받기
			for (int i = 0; i < M; i++) {
				stz = new StringTokenizer(bfr.readLine());
				int s = Integer.parseInt(stz.nextToken());
				int e = Integer.parseInt(stz.nextToken());
				int t = Integer.parseInt(stz.nextToken());

				roads[2 * i] = new Road(s, e, t);
				roads[2 * i + 1] = new Road(e, s, t);
			}

			// 웜홀 정보 받기
			for (int i = 2 * M; i < length; i++) {
				stz = new StringTokenizer(bfr.readLine());
				int s = Integer.parseInt(stz.nextToken());
				int e = Integer.parseInt(stz.nextToken());
				int t = Integer.parseInt(stz.nextToken());

				roads[i] = new Road(s, e, -t);
			}

			// 벨만포드용 업데이트 체크변수
			boolean isUpdated = false;

			// 벨만포드 전체 노드수-1 만큼 갱신 수행
			for (int i = 1; i < N; i++) {
				isUpdated = false;

				for (Road now : roads) {
					if (time[now.e] > time[now.s] + now.t) {
						time[now.e] = time[now.s] + now.t;
						isUpdated = true;
					}
				}

				// 더이상 업데이트가 없으면 음수사이클 없으므로 종료
				if (!isUpdated)
					break;
			}

			boolean isRoof = false;

			// N-1까지 업데이트가 있었으면
			if (isUpdated) {
				// 마지막 N번째 확인
				for (int i = 1; i <= N; i++) {
					for (Road now : roads) {
						if (time[now.e] > time[now.s] + now.t) {
							isRoof = true;
							break;
						}
					}

					if (isRoof)
						break;
				}
			}

			if (isRoof)
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}

		System.out.print(sb);

	}
}
