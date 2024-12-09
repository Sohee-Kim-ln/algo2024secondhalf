package algo241209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek2637 {
	static int[][] res;
	static LinkedList<int[]>[] need;
	static boolean[] visited;
	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bfr.readLine());
		int M = Integer.parseInt(bfr.readLine());

		// 부품 수 메모라이제이션 용
		res = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		// 관계 저장
		need = new LinkedList[N + 1];

		for (int i = 1; i <= N; i++)
			need[i] = new LinkedList<>();

		// 부품 관계 받기
		for (int i = 0; i < M; i++) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine());

			int next = Integer.parseInt(stz.nextToken());
			int prev = Integer.parseInt(stz.nextToken());
			int quantity = Integer.parseInt(stz.nextToken());

			need[next].add(new int[] { prev, quantity });
		}

		dp(N);

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < N + 1; i++) {
			if (res[N][i] != 0)
				sb.append(i).append(' ').append(res[N][i]).append('\n');
		}
		
		System.out.print(sb);
		
//		System.out.println();
//		for(int i=1;i<N+1;i++) {
//			System.out.println(Arrays.toString(res[i]));
//		}
	}

	static void dp(int num) {
		// 이미 계산됐으면 종료
		if (visited[num])
			return;

		// 기본부품이면
		if (need[num].size() == 0)
			res[num][num]++;

		// 연결된 모든 노드에 대해 탐색
		for (int[] now : need[num]) {
			int prev = now[0];
			int quantity = now[1];

			// 기본부품이면
			if (need[prev].size() == 0)
				res[num][prev] += quantity;

			else {
				dp(prev);

				for (int i = 1; i < N + 1; i++)
					res[num][i] += res[prev][i]*quantity;

			}
		}

		visited[num] = true;

	}
}
