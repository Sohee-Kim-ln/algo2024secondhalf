package algo241220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Baek2668 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(bfr.readLine());
		int[] arr = new int[N + 1];
		int[] depth = new int[N + 1];

		boolean[] included = new boolean[N + 1];
		Queue<Integer> quu = new LinkedList<>();
		int cnt = 0;

		// 숫자 정보 받기
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(bfr.readLine());

		// 전체 노드에 대해 탐색
		for (int i = 1; i <= N; i++) {
			// 이미 방문된 노드면 continue
			if (depth[i] != 0)
				continue;

			// 방문되지 않은 노드에 대해 depth 기록

			int now = i;
			int next = arr[now];
			depth[now] = 1;

			// 처음부터 자기자신 사이클일 경우
			if (now == next) {
				cnt++;
				included[now] = true;
				continue;
			}

			// 다음이 이전탐색에서 이미 방문된 경우
			if (depth[next] != 0)
				continue;

			quu.add(i);

			// 연결 따라가기
			// 문제 조건상 반드시 사이클이 1개는 있음
			while (!quu.isEmpty()) {
				next = arr[now];

				// 다음 노드가 방문되었다면
				if (depth[next] != 0) {
					// 사이클에 포함 안되는 노드들 빼기
					while (!quu.isEmpty() && quu.peek() != next)
						quu.poll();

					// 사이클에 포함되는 노드 표시하기
					while (!quu.isEmpty()) {
						included[quu.poll()] = true;
						cnt++;
					}
					break;
				}

				quu.add(next);
				depth[next] = depth[now] + 1;
				now = next;
			}

		}

		// 갯수 저장
		sb.append(cnt).append('\n');

		// 사이클 내부인 노드 번호 순서대로 저장
		for (int i = 1; i <= N; i++)
			if (included[i])
				sb.append(i).append('\n');

		// 출력
		System.out.print(sb);
	}
}
