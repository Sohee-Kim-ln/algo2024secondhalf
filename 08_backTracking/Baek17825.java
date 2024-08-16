package algo240816;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek17825 {
	static Node[] board;
	static int[] horse;
	static int[] dice;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		dice = new int[10];
		board = new Node[33];
		horse = new int[5];// 0번말 안씀

		// 게임판 입력. 0번 시작칸, 32번 도착칸
		board[0] = new Node(0, 1);

		for (int i = 1; i < 20; i++) {
			board[i] = new Node(2 * i, i + 1);
		}
		board[20] = new Node(40, 32);

		setBlue(5, 21);
		setBlue(10, 28);
		setBlue(15, 27);

		board[21] = new Node(13, 22);
		board[22] = new Node(16, 23);
		board[23] = new Node(19, 24);
		board[24] = new Node(25, 30);
		board[25] = new Node(26, 24);
		board[26] = new Node(27, 25);
		board[27] = new Node(28, 26);
		board[28] = new Node(22, 29);
		board[29] = new Node(24, 24);
		board[30] = new Node(30, 31);
		board[31] = new Node(35, 20);
		board[32] = new Node(0, -1);

		// 주사위 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(stz.nextToken());
		}

		// 백트래킹 시작
		btk(0, 0);

		// 출력
		System.out.println(max);

	}

	static void btk(int order, int sum) {
		// 주사위 계산 다 끝났으면
		if (order == 10) {
			max = Math.max(max, sum);
			return;
		}

		// 말 이동시키기
		for (int i = 1; i <= 4; i++) {
			// 선택된 말의 현재 위치
			int now = horse[i];
			int origin = horse[i];

			// 도착칸에 도달한 말이면 고려안함
			if (now == 32)
				continue;

			// 이동시작
			for (int move = 0; move < dice[order]; move++) {
				// 도착칸 도달시 이동 종료
				if (now == 32)
					break;

				if (move == 0 && board[now].isBlue)
					now = board[now].blue;
				else
					now = board[now].red;
			}

			// 이동 끝난 위치에 이미 말이 있으면
			if (now != 32 && board[now].horseNum != 0)
				continue;
			// 이동 끝난 위치에 말이 없으면 이동후 다음순서 계산
			else {
				// 이전칸 비우고 새칸 정보 저장
				board[origin].horseNum = 0;
				board[now].horseNum = i;
				horse[i] = now;

				btk(order + 1, sum + board[now].score);

				// 되돌리기
				horse[i] = origin;
				board[now].horseNum = 0;
				board[origin].horseNum = i;
			}

		}

	}

	static void setBlue(int index, int blue) {
		board[index].isBlue = true;
		board[index].blue = blue;
	}

	static class Node {
		int score;
		boolean isBlue;
		int red;
		int blue;
		int horseNum = 0;

		Node(int score, int red) {
			this.score = score;
			this.red = red;
			this.blue = -1;
			this.isBlue = false;
		}
	}
}
