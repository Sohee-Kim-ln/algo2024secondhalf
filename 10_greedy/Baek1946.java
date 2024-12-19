package algo241219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1946 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());

		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(bfr.readLine());
			boolean[] isPoped = new boolean[N];

			PriorityQueue<int[]> pqA = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
			PriorityQueue<int[]> pqB = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[2], o1[2]));

			int cnt = N;

			// 정보 받기
			StringTokenizer stz;
			for (int i = 0; i < N; i++) {
				stz = new StringTokenizer(bfr.readLine());
				int a = Integer.parseInt(stz.nextToken());
				int b = Integer.parseInt(stz.nextToken());

				pqA.add(new int[] { i, a, b });
				pqB.add(new int[] { i, a, b });
			}

			boolean finished = false;

			// 소거되는 지원자 없을 때까지 반복
			while (!finished) {
//				System.out.println(pqA.toString());
//				System.out.println(pqB.toString());
				int[] nowA = pqA.peek();
				int[] nowB = pqB.peek();

				// 아직 안없앤 A나올때까지 뽑기
				while (isPoped[nowA[0]]) {
					pqA.poll();
					nowA = pqA.peek();
				}

				// 아직 안없앤 A나올때까지 뽑기
				while (isPoped[nowB[0]]) {
					pqB.poll();
					nowB = pqB.peek();
				}
				
//				System.out.println(Arrays.toString(nowA));
//				System.out.println(Arrays.toString(nowB));

				// A쪽 자격검증
				if (nowA[1] > nowB[1] && nowA[2] > nowB[2]) {
					cnt--;
					isPoped[nowA[0]] = true;
					pqA.poll();
					continue;
				}

				// B쪽 자격검증
				if (nowA[1] < nowB[1] && nowA[2] < nowB[2]) {
					cnt--;
					isPoped[nowB[0]] = true;
					pqB.poll();
					continue;
				}

				// 더이상 소거되는 지원자 없으면 종료플래그 켜기
				finished = true;

			}

			// 결과 저장
			sb.append(cnt).append('\n');
		}

		// 출력
		System.out.print(sb);

	}
}
