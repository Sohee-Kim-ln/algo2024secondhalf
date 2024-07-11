package algo240711;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Collections;

//49분 50초 / 출력 시 \n이 1개 추가로 붙은 걸 fail처리 되는 것 디버깅에 11분 걸림

public class Baek30024 {
	// 상하좌우
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		// 옥수수 1칸에 대한 이너클래스
		class Corn implements Comparable<Corn> {
			int row;
			int col;
			int value;

			// 생성자
			Corn() {
			}

			Corn(int row, int col, int value) {
				this.row = row;
				this.col = col;
				this.value = value;
			}

			@Override
			public int compareTo(Corn o2) {
				if (this.value > o2.value)
					return 1;
				else if (this.value == o2.value)
					return 0;
				else
					return -1;
			}
		}

		// N M 입력받기
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String[] tempS = bfr.readLine().split(" ");

		int N = Integer.parseInt(tempS[0]); // 행
		int M = Integer.parseInt(tempS[1]); // 열

		// 0 사용 안함, 제일 마지막인 N+1도 사용안함
		int[][] corns = new int[N + 2][M + 2];

		// 옥수수 정보 입력 받기
		for (int i = 1; i <= N; i++) {
			tempS = bfr.readLine().split(" ");

			for (int j = 1; j <= M; j++) {
				corns[i][j] = Integer.parseInt(tempS[j - 1]);
			}
		}

		// K 입력받기
		int K = Integer.parseInt(bfr.readLine());

		// 수확 가능한 옥수수 넣는 pq
		PriorityQueue<Corn> pq = new PriorityQueue<>(Collections.reverseOrder());

		// 가장자리 옥수수 넣기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (i == 1 || i == N || j == 1 || j == M) {
					pq.add(new Corn(i, j, corns[i][j]));
					corns[i][j] = 0;
				}
			}
		}

		// 뽑은 옥수수 카운트
		int cnt = 0;

		// 뽑은 위치 답안 저장용 sb
		StringBuilder sb = new StringBuilder();

		// 옥수수 뽑기 시작
		while (cnt != K) {
			// 가장 가치있는 옥수수 하나 뽑기
			Corn removed = pq.poll();
			sb.append(removed.row).append(" ").append(removed.col);
			cnt++;
			
			if(cnt!=K) sb.append("\n");
			// System.out.println("뽑기 " + removed.row + " " + removed.col + " " +
			// removed.value);

			// 사방탐색
			for (int i = 0; i < 4; i++) {
				int r = removed.row + dr[i];
				int c = removed.col + dc[i];

				if (corns[r][c] != 0) {
					pq.add(new Corn(r, c, corns[r][c]));
					corns[r][c] = 0;
				}
			}

		}

		// 출력
		System.out.print(sb);

	}

}
