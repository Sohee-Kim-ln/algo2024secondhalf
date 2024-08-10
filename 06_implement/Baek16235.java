package algo240810;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek16235 {
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());

		int[][] A = new int[N][N];
		int[][] land = new int[N][N];

		Deque<Tree> list = new LinkedList<>();
		Queue<int[]> parents;

		// 땅 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(stz.nextToken());
				land[i][j] = 5;
			}
		}

		// 나무 정보 받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			int r = Integer.parseInt(stz.nextToken()) - 1;
			int c = Integer.parseInt(stz.nextToken()) - 1;
			int age = Integer.parseInt(stz.nextToken());

			list.add(new Tree(r, c, age));
		}

		// K년이 흐름
		for (int year = 0; year < K; year++) {
			// 죽어서 생성되는 양분 임시저장
			int[][] dead = new int[N][N];

			// 번식 후보 나무 위치 담기
			parents = new LinkedList<>();

			// 봄,여름
			for (int i = list.size() - 1; i >= 0; i--) {
				Tree cur = list.poll();

				// 양분이 충분하면
				if (land[cur.r][cur.c] >= cur.age) {
					land[cur.r][cur.c] -= cur.age;
					cur.age++;

					// 나이가 5의 배수이면 번식 후보
					if (cur.age % 5 == 0) {
						int[] location = { cur.r, cur.c };
						parents.add(location);
					}

					list.add(cur);
				}
				// 양분이 충분하지 못하면 죽은나무 처리
				else {
					dead[cur.r][cur.c] += cur.age / 2;
				}
			}

			// 가을 번식
			while (!parents.isEmpty()) {
				int[] now = parents.poll();
				for (int dir = 0; dir < 8; dir++) {
					int nextr = now[0] + dr[dir];
					int nextc = now[1] + dc[dir];
					// 경계조건
					if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= N)
						continue;

					list.addFirst(new Tree(nextr, nextc, 1));
				}

			}

			// 겨울 양분 추가

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					land[i][j] += A[i][j] + dead[i][j];
				}
			}
		}
		System.out.println(list.size());

	}

	static class Tree {
		int r, c;
		int age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

	}
}
