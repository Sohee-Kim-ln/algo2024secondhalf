package algo240802;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Baek2667 {
	// 하상우좌
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 지도크기
		int N = Integer.parseInt(bfr.readLine());

		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];

		// 지도 입력받기
		for (int i = 0; i < N; i++) {
			String[] tempS = bfr.readLine().split("");

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tempS[j]);
			}
		}

		int danzi = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 방문하지 않은 아파트 찾기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 아파트가 아니면
				if (map[i][j] == 0)
					continue;
				
				// 이미 방문했으면
				if (visited[i][j] == true)
					continue;

				// bfs 시작
				int cnt = 0;
				Queue<Node> quu = new LinkedList<>();

				// 시작점 표시 후 넣기
				danzi++;
				visited[i][j] = true;
				cnt++;
				quu.add(new Node(i, j));

				while (!quu.isEmpty()) {
					Node curr = quu.poll();
					// 4방탐색
					for (int k = 0; k < 4; k++) {
						int nextr = curr.r + dr[k];
						int nextc = curr.c + dc[k];

						// 경계조건
						if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= N)
							continue;

						if (map[nextr][nextc] != 0 && !visited[nextr][nextc]) {
							visited[nextr][nextc] = true;
							cnt++;
							quu.add(new Node(nextr, nextc));
						}
					}
				}

				pq.add(cnt);

			}
		}

		System.out.println(danzi);
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
