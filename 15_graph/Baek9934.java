package algo241212;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek9934 {

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 깊이 정보
		int K = Integer.parseInt(bfr.readLine());
		int length = (1 << K) - 1;
		int[] arr = new int[length];

		// {범위 왼끝, 범위 오른끝}
		Queue<int[]> quu = new LinkedList<>();

		// 수열 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < length; i++)
			arr[i] = Integer.parseInt(stz.nextToken());

		// 초기값
		quu.add(new int[] { 0, length });
		int cnt = 0;
		int depth = 1;
		int max = 1 << (depth - 1);

		// 탐색
		while (depth<=K) {
			int[] now = quu.poll();

			int mid = (now[0] + now[1]) >> 1;

			// 부모노드 기록
			sb.append(arr[mid]).append(' ');
			cnt++;

			// 왼쪽 범위 넣기
			if (mid-1 >= 0) {
				quu.add(new int[] { now[0], mid - 1 });
			}

			// 오른쪽 범위 넣기
			if (mid+1 <= length-1) {
				quu.add(new int[] { mid + 1, now[1] });
			}

			// 현재 깊이 다 기록했으면
			if (cnt == max) {
				// 깊이 관련 정보 갱신
				cnt = 0;
				depth++;
				max = 1 << (depth - 1);

				// 다음줄로 넘기기
				sb.setLength(sb.length() - 1);
				sb.append('\n');
			}
		}
		
		// 출력
		sb.setLength(sb.length() - 1);
		System.out.print(sb);
	}
}
