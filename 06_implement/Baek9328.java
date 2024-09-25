package algo240925;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek9328 {
	static int H, W, cnt = 0;
	static char[][] building;
	static boolean[][] visited;
	static boolean[] hasKey;

	static ArrayDeque<int[]> adqBFS, locked;

	// 4방 탐색용 델타변수
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());

		StringTokenizer stz;
		StringBuilder sb = new StringBuilder();

		// 각 테케에 대해
		for (int tc = 0; tc < T; tc++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			H = Integer.parseInt(stz.nextToken());
			W = Integer.parseInt(stz.nextToken());
			cnt = 0;

			building = new char[H + 2][W + 2];
			visited = new boolean[H + 2][W + 2];
			hasKey = new boolean[27];

			adqBFS = new ArrayDeque<>();
			locked = new ArrayDeque<>();
			
			// 지도 정보 받기
			for (int h = 1; h <= H; h++) {
				char[] tempC = bfr.readLine().toCharArray();
				for (int w = 1; w <= W; w++) {
					building[h][w] = tempC[w - 1];
				}
			}

			// 열쇠 정보 받기
			String tempS = bfr.readLine();
			if (tempS.charAt(0) != '0')
				for (int i = 0; i < tempS.length(); i++) {
					hasKey[tempS.charAt(i) - 'a'] = true;
				}

			// 가장자리부터 진입 가능한 지점 찾아 탐색
			bfs(0, 0);

			// 잠긴 문 열수 없을때까지 반복
			while (!locked.isEmpty()) {
				int size = locked.size();
				boolean changed = false;

				// 현재 있는 잠긴문 다 확인해보기
				for (int i = 0; i < size; i++) {
					int[] door = locked.poll();
					int code = building[door[0]][door[1]] - 'A';

					// 열 수 있으면 bfs
					if (hasKey[code]) {
						bfs(door[0], door[1]);
						changed = true;
					} else
						locked.add(door);
				}

				// 더이상 열 수 없으면 종료
				if (!changed)
					break;
			}

			sb.append(cnt).append("\n");
		}

		// 출력
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	// bfs로 접근 가능한 지점의 열쇠, 문서, 문 탐색
	static void bfs(int r, int c) {
		adqBFS.add(new int[] { r, c });
		visited[r][c] = true;

		while (!adqBFS.isEmpty()) {
			int[] now = adqBFS.poll();

			// 4방 탐색
			for (int dir = 0; dir < 4; dir++) {
				int nextr = now[0] + dr[dir];
				int nextc = now[1] + dc[dir];

				// 경계조건
				if (nextr < 0 || nextr > H + 1 || nextc < 0 || nextc > W + 1)
					continue;

				// 이미 방문했으면
				if (visited[nextr][nextc])
					continue;

				char letter = building[nextr][nextc];

				// 벽
				if (letter == '*')
					continue;

				// 대문자 문인데 열수 없으면 잠긴 목록에 추가
				if (isUpper(nextr, nextc) && !hasKey[letter - 'A']) {
					locked.add(new int[] { nextr, nextc });
					continue;
				}

				// 소문자 열쇠
				if (isLower(nextr, nextc))
					hasKey[letter - 'a'] = true;

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
