package algo240919;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baek9576 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());

		StringBuilder sb = new StringBuilder();

		// 테케 별로 계산
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
			int N = Integer.parseInt(stz.nextToken());
			int M = Integer.parseInt(stz.nextToken());

			// 구간시작, 구간끝, 구간내 가능한 책 수
			int[][] student = new int[M][3];
			boolean[] used = new boolean[N + 1];
			int cnt = 0;

			// 요청 정보 받기
			for (int i = 0; i < M; i++) {
				stz = new StringTokenizer(bfr.readLine(), " ");
				int start = Integer.parseInt(stz.nextToken());
				int end = Integer.parseInt(stz.nextToken());
				student[i][0] = start;
				student[i][1] = end;
			}

			// 종료점 기준 오름차순, 시작점 기준 오름차순
			Arrays.sort(student, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] != o2[1])
						return o1[1] - o2[1];
					else
						return o1[0] - o2[0];
				}
			});

			// 정렬된 결과에 따라 분배 가능한 책 찾기
			for (int i = 0; i < M; i++) {
				int left = student[i][0];
				int right = student[i][1];
				
				// 해당 구간에서 제일 앞번호 책 주기
				for (int j = left; j <= right; j++) {
					if (!used[j]) {
						used[j] = true;
						cnt++;
						break;
					}
				}
				if (cnt == N)
					break;
			}

			sb.append(cnt).append("\n");
		}
		
		// 출력
		sb.setLength(sb.length()-1);
		System.out.println(sb);

	}
}
