package algo240925;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek9328_3 {
	static int H, W, cnt = 0;
	static char[][] building;
	static boolean[][] visited;
	static boolean[] hasKey;
	static int[][] door;

	static ArrayDeque<int[]> adqBFS = new ArrayDeque<>();

	// 4방 탐색용 델타변수
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());

		StringTokenizer stz;

		// 각 테케에 대해
		for (int tc = 0; tc < T; tc++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			H = Integer.parseInt(stz.nextToken());
			W = Integer.parseInt(stz.nextToken());

			building = new char[H + 2][W + 2];
			visited = new boolean[H + 2][W + 2];
			hasKey = new boolean[26];
			door = new int[26][2];
			cnt = 0;

			// 지도 정보 받기
			for (int h = 1; h <= H; h++) {
				char[] tempC = bfr.readLine().toCharArray();
				for (int w = 1; w <= W; w++) {
					building[h][w] = tempC[w - 1];
					// 문이면 위치 저장
					if (isUpper(h, w)) {
						int code = building[h][w] - 'A';
						door[code][0] = h;
						door[code][1] = w;
					}
				}
			}

			// 열쇠 정보 받기
			String tempS = bfr.readLine();
			if (tempS.charAt(0) != '0')
				for (int i = 0; i < tempS.length(); i++) {
					hasKey[tempS.charAt(i) - 'a'] = true;
				}

			// 탐색
			for (int r = 1; r <= H; r++) {
				for (int c = 1; c <= W; c++) {
					// 가장자리에 대해
					if (r == 1 || r == H || c == 1 || c == W) {
						// 이미 방문했으면 패스
						if (visited[r][c])
							continue;

						// bfs 시작 가능한 지점인지 확인
						check(r, c);
					}
				}
			}

			// 탐색
			bfs();

			System.out.println(cnt);
		}
	}

	// bfs 시작 가능한 지점인지 확인하는 함수
	static void check(int r, int c) {
		char letter = building[r][c];

		// 벽
		if (letter == '*')
			return;

		// 대문자 문
		if (isUpper(r, c)) {
			door[letter - 'A'][0] = r;
			door[letter - 'A'][1] = c;

			// 열지 못하면 종료
			if (!hasKey[letter - 'A'])
				return;
		}

		// 소문자 열쇠
		if (isLower(r, c))
			hasKey[letter - 'a'] = true;

		// 문서
		if (letter == '$')
			cnt++;

		adqBFS.add(new int[] { r, c });
		visited[r][c] = true;
		bfs();
	}

	// bfs로 접근 가능한 지점의 열쇠, 문서, 문 탐색
	static void bfs() {
		while (!adqBFS.isEmpty()) {
			int[] now = adqBFS.poll();

			// 4방 탐색
			for (int dir = 0; dir < 4; dir++) {
				int nextr = now[0] + dr[dir];
				int nextc = now[1] + dc[dir];

				// 경계조건
				if (nextr < 1 || nextr > H || nextc < 1 || nextc > W)
					continue;

				// 이미 방문했으면
				if (visited[nextr][nextc])
					continue;

				char letter = building[nextr][nextc];
				int code;

				// 벽
				if (letter == '*')
					continue;

				// 대문자 문
				if (isUpper(nextr, nextc)) {
					code = letter - 'A';
					door[code][0] = nextr;
					door[code][1] = nextc;

					if (hasKey[code]) {
						adqBFS.add(new int[] { nextr, nextc });
						visited[nextr][nextc] = true;
					}
					continue;
				}

				// 소문자 열쇠
				if (isLower(nextr, nextc)) {
					code = letter - 'a';
					hasKey[code] = true;

					// 접근 가능한 문이면 bfs에 포함
					if (door[letter - 'a'][0] != 0) {
						int rDoor = door[code][0];
						int cDoor = door[code][1];

						adqBFS.add(new int[] { rDoor, cDoor });
						visited[rDoor][cDoor] = true;
					}
				}

				// 문서
				if (letter == '$')
					cnt++;

				adqBFS.add(new int[] { nextr, nextc });
				visited[nextr][nextc] = true;
			}
		}
	}

	// 대문자 확인
	static boolean isUpper(int r, int c) {
		char code = building[r][c];

		if (code >= 'A' && code <= 'Z')
			return true;

		return false;
	}

	// 소문자 확인
	static boolean isLower(int r, int c) {
		char code = building[r][c];

		if (code >= 'a' && code <= 'z')
			return true;

		return false;
	}
}
