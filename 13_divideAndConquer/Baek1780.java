package algo240914;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1780 {
	static int N;
	static int[][] paper;

	// -1,0,1 갯수 저장
	static int[] cnt = new int[3];

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		N = Integer.parseInt(stz.nextToken());
		paper = new int[N][N];

		// 종이 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(stz.nextToken());
			}
		}

		// 분할정복 실행
		check(0, 0, N);

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			sb.append(cnt[i]).append("\n");
		}

		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}

	// 지정영역 확인 후 숫자 반환. 통일되지 않았을 시 2 반환
	static int check(int r, int c, int length) {
		// 한칸짜리면 카운트 후 종료
		if (length == 1) {
			cnt[paper[r][c] + 1]++;
			return paper[r][c];
		}

		int lengthCut = length / 3;
		int[] piece = new int[4]; // -1,0,1,2 갯수 저장
		int nextr, nextc;

		// 9개 영역으로 나눠서 확인
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				nextr = r + lengthCut * i;
				nextc = c + lengthCut * j;

				int res = check(nextr, nextc, lengthCut);
				piece[res + 1]++;
			}
		}

		// 통일되지 않은 조각이 있으면 종료
		if (piece[3] != 0)
			return 2;

		// 9개 조각이 -1,0,1 통일이면 합쳐서 계산 후 종료
		for (int i = 0; i < 3; i++) {
			if (piece[i] == 9) {
				cnt[i] -= 8;
				return i - 1;
			}
		}

		return 2;
	}
}
