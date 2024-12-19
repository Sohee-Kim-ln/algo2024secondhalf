package algo241219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1946 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());

		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(bfr.readLine());

			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));

			// 정보 받기
			StringTokenizer stz;
			for (int i = 0; i < N; i++) {
				stz = new StringTokenizer(bfr.readLine());
				int a = Integer.parseInt(stz.nextToken());
				int b = Integer.parseInt(stz.nextToken());

				pq.add(new int[] { a, b });

			}

			// 서류1등은 무조건 합격
			int[] now = pq.poll();
			int minInterview = now[1];
			int cnt = 1;

			for (int i = 1; i < N; i++) {
				now = pq.poll();

				// 면접점수가 최대 등수보다 작다면
				if (minInterview > now[1]) {
					cnt++;
					minInterview = now[1];
				}
			}

			// 결과 저장
			sb.append(cnt).append('\n');
		}

		// 출력
		System.out.print(sb);

	}
}
