package algo241026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek3190 {
	// 방향용 델타변수 우 하 좌 상
	static int[] dr = new int[] { 0, 1, 0, -1 };
	static int[] dc = new int[] { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		StringTokenizer stz;

		// 0빈칸 1사과 2뱀
		int[][] board = new int[N + 1][N + 1];

		ArrayDeque<int[]> snake = new ArrayDeque<>();
		int time = 0;

		// 뱀 초기 상태 저장
		snake.add(new int[] { 1, 1 });
		int dir = 0;
		board[1][1] = 2;

		// 사과 정보 받기
		int K = Integer.parseInt(bfr.readLine());

		for (int i = 0; i < K; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			int r = Integer.parseInt(stz.nextToken());
			int c = Integer.parseInt(stz.nextToken());

			board[r][c] = 1;
		}

		// 움직임 정보 받기
		int L = Integer.parseInt(bfr.readLine());
		int[] orderT = new int[L];
		boolean[] orderD = new boolean[L];

		for (int i = 0; i < L; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			orderT[i] = Integer.parseInt(stz.nextToken());
			orderD[i] = stz.nextToken().charAt(0) == 'D' ? true : false;
		}

		// 수행할 명령 가리키는 인덱스
		int idx = 0;

		// 뱀 진행
		while (true) {
			time++;

			int[] head = snake.peekFirst();
			// 앞 위치 계산
			int nextr = head[0] + dr[dir];
			int nextc = head[1] + dc[dir];

			// 경계조건(벽이면 종료)
			if (nextr < 1 || nextr > N || nextc < 1 || nextc > N)
				break;

			// 셀프 충돌 시 종료
			if (board[nextr][nextc] == 2)
				break;

			// 앞이 사과가 아니라면 꼬리 제거
			if (board[nextr][nextc] == 0) {
				int[] tail = snake.pollLast();
				board[tail[0]][tail[1]] = 0;
			}

			// 앞 연장
			board[nextr][nextc] = 2;
			snake.addFirst(new int[] { nextr, nextc });

			// 명령에 따른 방향 회전
			if (idx < L && time == orderT[idx]) {
				// 시계 : 반시계
				dir = orderD[idx] ? (dir + 1) % 4 : (dir + 3) % 4;
				idx++;
			}
		}

		// 출력
		System.out.println(time);

	}
}
