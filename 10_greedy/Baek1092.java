package algo241104;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1092 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 크레인
		int N = Integer.parseInt(bfr.readLine());
		int[] crane = new int[N];

		// 각 크레인이 사용한 최소 시간
		int[] time = new int[N];

		// 크레인 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < N; i++)
			crane[i] = Integer.parseInt(stz.nextToken());

		// 크레인 오름차순 정렬. 뒤부터 탐색 예정
		Arrays.sort(crane);

		// 박스
		int M = Integer.parseInt(bfr.readLine());
		int[] box = new int[M];

		// 박스 정보 받기
		stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < M; i++)
			box[i] = Integer.parseInt(stz.nextToken());

		// 오름차순 정렬. 뒤부터 탐색예정
		Arrays.sort(box);

		// 탐색용 임시변수
		int idxB = M - 1;
		int maxTime = Integer.MIN_VALUE;

		// 큰 상자부터 내림차순 탐색
		while (idxB >= 0) {

			// 현재 박스를 옮길수 있는 크레인과 그 시간
			int nowCrane = -1;
			int nowTime = Integer.MAX_VALUE;

			// 박스 옮길 수 있는 크레인 중 예정된 시간이 짧은 크레인 선택
			for (int i = N - 1; i >= 0; i--) {
				if (crane[i] >= box[idxB]) {
					if (nowTime > time[i]) {
						nowCrane = i;
						nowTime = time[i];
					}
				}
			}

			// 박스를 옮길 수 있는 크레인이 없으면 탐색 종료
			if (nowCrane < 0) {
				maxTime = -1;
				break;
			}

			// 해당 크레인 시간++, 최대 시간 갱신
			time[nowCrane]++;
			maxTime = Math.max(maxTime, time[nowCrane]);

			idxB--;
		}

		System.out.println(maxTime);

	}
}
