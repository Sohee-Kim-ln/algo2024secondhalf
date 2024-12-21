package algo241221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1138 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int[] arr = new int[N];

		// 키 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		for (int i = 0; i < N; i++) {
			int taller = Integer.parseInt(stz.nextToken());

			// 현재 인덱스에서 i보다 큰 사람 수
			int cnt = 0;

			// 앞부터 탐색해서 넣을수 있는 칸 찾기
			for (int j = 0; j < N; j++) {
				// 큰사람수 안채워졌으면
				if (cnt != taller) {
					// 앞으로 채워질 큰사람이거나 이미 큰사람일때 cnt++
					if (arr[j] == 0 || arr[j] > i + 1)
						cnt++;
					continue;
				}
				// 큰사람 수 채워졌으면
				else {
					// 빈칸 찾아서 넣기
					if (arr[j] == 0) {
						arr[j] = i + 1;
						break;
					}
				}
			}

		}

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++)
			sb.append(arr[i]).append(' ');
		sb.setLength(sb.length() - 1);

		System.out.println(sb);

	}
}
