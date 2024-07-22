package algo240722;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Baek4485 {

	// 델타변수
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = -1;
		int number = 0;
		while (N != 0) {
			number++;
			// 크기 입력받기
			N = Integer.parseInt(bfr.readLine());

			// 종료플래그 들어오면 반복문 탈출
			if (N == 0)
				break;

			// 다음 고려 후보 저장 우선큐
			PriorityQueue<Node> pq = new PriorityQueue<>();

			// 방문여부 저장
			boolean[][] visited = new boolean[N][N];

			// 소모루피 저장
			int[][] rupee = new int[N][N];

			// 현재 지점까지 소모되는 최소비용 저장 배열
			int[][] min = new int[N][N];

			// 최소비용 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					min[i][j] = Integer.MAX_VALUE;
				}
			}

			String[] tempS;

			// 소모루피 입력받기
			for (int i = 0; i < N; i++) {
				tempS = bfr.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					rupee[i][j] = Integer.parseInt(tempS[j]);
				}
			}

			min[0][0] = rupee[0][0];

			pq.add(new Node(0, 0, 5));

			int cnt = 0;
			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				int curr = cur.r;
				int curc = cur.c;

				// 4방탐색
				for (int i = 0; i < 4; i++) {
					int nextr = curr + dr[i];
					int nextc = curc + dc[i];

					// 경계조건
					if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= N)
						continue;

					// 방문하지 않은 노드면 다음 방문 후보로 넣기
					if (!visited[nextr][nextc]) {
						visited[nextr][nextc] = true;
						// 최소거리 갱신
						min[nextr][nextc] = Math.min(min[nextr][nextc], min[curr][curc] + rupee[nextr][nextc]);
						pq.add(new Node(nextr, nextc, min[nextr][nextc]));

					}
				}
			}

			
			System.out.println(MessageFormat.format("Problem {0}: {1}", number , min[N - 1][N - 1]));
		}

	}

	static class Node implements Comparable<Node> {
		int r;
		int c;
		int value;

		Node() {
		}

		Node(int r, int c, int value) {
			this.r = r;
			this.c = c;
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
