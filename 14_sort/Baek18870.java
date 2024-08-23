package algo240823;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek18870 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		int N = Integer.parseInt(stz.nextToken());

		PriorityQueue<Node> pq = new PriorityQueue<>();
		long[] origin = new long[N];
		long[] arr = new long[N];

		// 숫자 정보 입력 받기
		stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 0; i < N; i++) {
			origin[i] = Long.parseLong(stz.nextToken());
			pq.add(new Node(i, origin[i]));
		}

		Node now = pq.poll();

		int cnt = 0;
		long latest = now.value;
		arr[now.index] = cnt;

		// 값이 작은 순서대로 정렬된 pq에서 반복 뽑기
		while (!pq.isEmpty()) {
			now = pq.poll();

			// 이전 수와 같지 않으면 카운트 ++ 해서 저장
			if (now.value != latest)
				cnt++;

			arr[now.index] = cnt;

			latest = now.value;
		}

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append(" ");
		}
		sb.setLength(sb.length() - 1);

		System.out.print(sb);

	}

	static class Node implements Comparable<Node> {
		int index;
		long value;

		Node(int index, long value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			if (this.value > o.value)
				return 1;
			else if (this.value == o.value)
				return 0;
			else
				return -1;
		}
	}
}
