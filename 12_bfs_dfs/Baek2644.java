package algo240805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Baek2644 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 사람수
		int N = Integer.parseInt(bfr.readLine());

		// 촌수 계산 타겟
		String[] tempS = bfr.readLine().split(" ");

		int start = Integer.parseInt(tempS[0]);
		int end = Integer.parseInt(tempS[1]);

		// 연결정보 저장할 리스트
		LinkedList<Integer>[] linked = new LinkedList[N + 1];

		for (int i = 1; i <= N; i++) {
			linked[i] = new LinkedList<Integer>();
		}

		// 연결 수
		int M = Integer.parseInt(bfr.readLine());

		// 연결정보 저장
		for (int i = 0; i < M; i++) {
			tempS = bfr.readLine().split(" ");
			int parent = Integer.parseInt(tempS[0]);
			int child = Integer.parseInt(tempS[1]);

			linked[parent].add(child);
			linked[child].add(parent);
		}

		// 시작지점부터의 거리. -1이면 아직 방문 안함
		int[] dist = new int[N + 1];
		Arrays.fill(dist, -1);

		// dfs 돌릴 스택
		Stack<Integer> st = new Stack<>();

		// 시작지점 넣기
		dist[start] = 0;
		st.add(start);

		while (!st.isEmpty()) {
			int cur = st.pop();

			// 연결된 노드 탐색
			for (int i = 0; i < linked[cur].size(); i++) {
				int temp = linked[cur].get(i);
				
				// 연결된 노드가 방문되지 않았다면
				if (dist[temp] == -1) {
					dist[temp] = dist[cur] + 1;
					st.add(temp);
				}
			}
		}

		System.out.println(dist[end]);

	}
}
