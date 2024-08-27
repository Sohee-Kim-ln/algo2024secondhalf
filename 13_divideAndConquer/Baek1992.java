package algo240827;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1992 {
	static int N;
	static boolean[][] video;
	static StringBuilder sb = new StringBuilder();

	// z자탐색
	static int[] dr = { 0, 0, 1, 1 };
	static int[] dc = { 0, 1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		N = Integer.parseInt(stz.nextToken());

		video = new boolean[N][N];

		// 영상 정보 입력받기
		for (int i = 0; i < N; i++) {
			char[] line = bfr.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				video[i][j] = line[j] == '1' ? true : false;
			}
		}

		int ans = zip(0, 0, N);

		if(ans!=-1)
			sb.append(ans);
		
		System.out.println(sb);

	}

	static int zip(int startr, int startc, int length) {

		
		if (length == 1)
			return video[startr][startc] ? 1 : 0;

		int half = length >> 1;
		int sum = 0;
		int[] arr = new int[4];
		boolean hasString = false;

		sb.append('(');

		for (int dir = 0; dir < 4; dir++) {
			int nextr = startr + half * dr[dir];
			int nextc = startc + half * dc[dir];
			arr[dir] = zip(nextr, nextc, half);

			if (arr[dir] != -1) {
				sb.append(arr[dir]);
				sum = (sum << 1) ^ arr[dir];
			} else
				hasString = true;
		}

		sb.append(')');

		if (!hasString)
			if (sum == 15 || sum == 0) {
			sb.setLength(sb.length() - 6);
				return sum >> 3;
			}

		return -1;

	}
}
