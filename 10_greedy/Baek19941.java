package algo240726;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek19941 {

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String[] tempS = bfr.readLine().split(" ");

		int N = Integer.parseInt(tempS[0]);
		int K = Integer.parseInt(tempS[1]);

		boolean[] visited = new boolean[N];

		// 햄버거 사람 줄 정보 입력 받기
		char[] line = new char[N];
		line = bfr.readLine().toCharArray();

		// 햄버거 최대 수 저장
		int max = 0;

		// 줄의 처음부터 끝까지 탐색
		for (int i = 0; i < N; i++) {
			// 사람일 경우
			if (line[i] == 'P') {
				// +-K 범위 탐색
				for (int j = -K; j <= K; j++) {
					// 인덱스 범위 보다 앞이므로 continue;
					if (i + j < 0)
						continue;

					// 탐색 종료이므로 break;
					if (i + j >= N)
						break;

					// 햄버거를 만났고 아직 먹지 않았다면
					if (line[i + j] == 'H' && !visited[i + j]) {
						visited[i + j] = true;
						max++;
						break;
					}
				}
			}
		}

		System.out.println(max);
	}

}
