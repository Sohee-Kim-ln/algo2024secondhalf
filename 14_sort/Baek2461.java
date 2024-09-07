package algo240907;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Comparator;

public class Baek2461 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int[][] school = new int[N][M];
		int[] idx = new int[N];
		int diff = Integer.MAX_VALUE;

		// 학생 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			for (int j = 0; j < M; j++) {
				school[i][j] = Integer.parseInt(stz.nextToken());
			}

			// 학급 내 오름차순 정렬
			Arrays.sort(school[i]);
		}

		// 현재 포인터가 가리키는 학생 비교용 우선큐
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
		int max = Integer.MIN_VALUE;

		// 초기값 넣기
		for (int i = 0; i < N; i++) {
			pq.add(new int[] { i, school[i][0] });
			max = Math.max(max, school[i][0]);
		}

		// 포인터 이동하면서 계산
		while (true) {
			int[] min = pq.poll();
			int row = min[0];
			
			// 최저차 저장
			diff = Math.min(diff, max - min[1]);

			// 계산범위 벗어나서 더이상 최저row를 갱신할 수 없을 때 break;
			if (++idx[row] == M)
				break;

			max = Math.max(max, school[row][idx[row]]);
			pq.add(new int[] { row, school[row][idx[row]] });
		}

		System.out.println(diff);
	}
}
