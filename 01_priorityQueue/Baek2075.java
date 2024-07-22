package algo240723;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baek2075 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		long[][] board = new long[N][N];

		PriorityQueue<Node> pq = new PriorityQueue<>();

		int cnt = 0;

		// 표 입력받기

		for (int i = 0; i < N; i++) {
			String[] tempS = bfr.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(tempS[j]);

				if (i == N - 1)
					pq.add(new Node(i, j, board[i][j]));
			}
		}

		while (cnt != N) {
			Node temp = pq.poll();
			cnt++;

			if (cnt == N) {
				System.out.println(temp.value);
				break;
			}
			
			// 하나 위의 숫자 위치 보기
			int nextr = temp.r - 1;

			if (nextr < 0)
				continue;

			pq.add(new Node(nextr, temp.c, board[nextr][temp.c]));

		}

	}

	static class Node implements Comparable<Node> {
		int r;
		int c;
		long value;

		Node() {
		}

		Node(int r, int c, long value) {
			this.r = r;
			this.c = c;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			if (this.value > o.value)
				return -1;
			else if (this.value == o.value)
				return 0;
			else
				return 1;
		}
	}
}
