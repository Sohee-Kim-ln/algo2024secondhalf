package algo240907;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최적화 덜 됨
public class Baek2461_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int[][] school = new int[N][M];
		int[] idx = new int[N];

		// 학생 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			for (int j = 0; j < M; j++) {
				school[i][j] = Integer.parseInt(stz.nextToken());
			}

			// 학급 내 오름차순 정렬
			Arrays.sort(school[i]);
		}

		// 최대 중간 최소 저장용
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int minRow = -1;

		int diff = Integer.MAX_VALUE;

		// 포인터 이동하면서 계산
		while (true) {
			// 최대,중간,최소 찾기
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				if (max < school[i][idx[i]])
					max = school[i][idx[i]];

				if (min > school[i][idx[i]]) {
					min = school[i][idx[i]];
					minRow = i;
				}
			}

			// 최저 차이면 저장
			diff = Math.min(diff, max - min);

			idx[minRow]++;
			if (idx[minRow] == M)
				break;
		}

		System.out.println(diff);
	}
}
