package algo241122;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Comparator;

public class Baek10800 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int[] ans = new int[N + 1];

		int[] sum = new int[N + 1];
		int total = 0;
		int idx, color, size;

		// {번호, 색깔, 크기}
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			// 크기 오름차순, 이후 색깔 오름차순
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[2] != o2[2])
					return o1[2] - o2[2];
				else
					return o1[1] - o2[1];
			}
		});

		// 공 정보 받기
		StringTokenizer stz;
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine());

			color = Integer.parseInt(stz.nextToken());
			size = Integer.parseInt(stz.nextToken());

			pq.add(new int[] { i, color, size });
		}

		int[] sumSame = new int[N + 1];
		int sizeLast = Integer.MIN_VALUE;
		int sumTotal = 0;
		// 작은 공부터 계산
		while (!pq.isEmpty()) {
			int[] now = pq.poll();

			idx = now[0];
			color = now[1];
			size = now[2];

			// 다른 사이즈면 같사이즈 카운트 배열 및 총합 리셋
			if (sizeLast != size) {
				sizeLast = size;
				sumSame = new int[N + 1];
				sumTotal = 0;
			}

			// 전체 총합 계산
			sum[color] += size;
			total += size;

			// (전체 총합-같색 총합) - (겹치는 총합-같색 겹치는 총합);
			ans[idx] = total - sum[color] - sumTotal + sumSame[color];

			// 중복 크기 나올 때를 위해 저장
			sumSame[color] += size;
			sumTotal += size;
		}

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++)
			sb.append(ans[i]).append('\n');

		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}
}
