package algo241217;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek2056 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bfr.readLine());

		// 0안씀
		int[] time = new int[N + 1];

		int max = Integer.MIN_VALUE;

		Queue<Integer> quu = new LinkedList<>();

		StringTokenizer stz;

		// 선행작업 정보 받으면서 계산
		for (int i = 1; i <= N; i++) {
			stz = new StringTokenizer(bfr.readLine());

			// 현재 작업
			time[i] = Integer.parseInt(stz.nextToken());
			int cntIn = Integer.parseInt(stz.nextToken());

			// i로 진입하는 것 중 최대 시간 찾기
			int maxLast = 0;

			for (int j = 0; j < cntIn; j++) {
				int prev = Integer.parseInt(stz.nextToken());
				maxLast = Math.max(maxLast, time[prev]);
			}

			// 현재 노드의 최소시간 = 이전작업 최대시간 + 현재작업 시간
			time[i] += maxLast;

			// 최대 갱신
			max = Math.max(max, time[i]);
		}

		// 출력
		System.out.println(max);
	}
}
