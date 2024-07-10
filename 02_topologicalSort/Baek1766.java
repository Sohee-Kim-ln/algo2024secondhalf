package algo240710;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 1시간 45분 33초 / 문제 해석 실수로 완전히 다른방향으로 작성했다가 처음부터 다시 풀은 시간 포함됨.


public class Baek1766 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = 0; // 문제 수
		int M = 0; // 먼저 푸는 것이 좋은 정보 갯수

		String[] temp = bfr.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		// 특정 숫자의 뒤에 와야 하는 숫자에 대한 연결정보 저장할 pq. 0안씀
		PriorityQueue<Integer>[] after = new PriorityQueue[N + 1];

		for (int i = 1; i <= N; i++) {
			after[i] = new PriorityQueue<>();
		}

		// 해당 번호를 풀어야 풀 수 있는 문제 수
		int[] lines = new int[N + 1];

		// 라인 정보 저장
		for (int i = 0; i < M; i++) {
			temp = bfr.readLine().split(" ");
			int prev = Integer.parseInt(temp[0]);
			int next = Integer.parseInt(temp[1]);

			if (after[prev] == null)
				after[prev] = new PriorityQueue<Integer>();
			after[prev].add(next);
			lines[next]++;

		}

		// 전체 문자열 저장할 sb
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 1부터 시작해서 선행조건 없는 문제 pq에 넣기
		for (int i = 1; i <= N; i++) {
			if (lines[i] == 0)
				pq.add(i);
		}

		// 문제하나 풀 때마다 선행조건에서 제거하기
		while (!pq.isEmpty()) {
			// 풀문제 꺼내기
			int curr = pq.poll();
			sb.append(curr).append(" ");

			// 풀문제를 선행으로 갖는 문제들에서 line--하고, 더이상 선행조건이 없어지면 pq에 넣기
			while (!after[curr].isEmpty()) {
				int nextProb = after[curr].poll();
				lines[nextProb]--;
				if (lines[nextProb] == 0)
					pq.add(nextProb);
			}
		}

		System.out.println(sb);
	}
}
