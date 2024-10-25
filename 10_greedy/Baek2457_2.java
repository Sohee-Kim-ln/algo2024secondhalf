package algo241025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2457_2 {

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		int[][] flower = new int[N][2];

		// 입력 받기용 임시변수
		StringTokenizer stz;
		int month, start, end;

		// 꽃 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			// 시작일 계산
			month = Integer.parseInt(stz.nextToken());
			start = Integer.parseInt(stz.nextToken()) + month * 100;

			// 종료일 계산
			month = Integer.parseInt(stz.nextToken());
			end = Integer.parseInt(stz.nextToken()) + month * 100;

			flower[i][0] = start;
			flower[i][1] = end;
		}

		// 시작일 오름차순 후 종료일 내림차순 정렬
		Arrays.sort(flower, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);

		// 초기값 설정
		int last = 301; // 2월 28일 종료상태와 마찬가지
		int target = 1201; // 12월 1일
		int max = Integer.MIN_VALUE;
		int idx = 0;
		int cnt = 0;

		// 목표일 도달하기 전까지 반복
		while (last < target) {
			// 갱신여부 플래그 변수
			boolean isFound = false;

			// 선택된 꽃 이후의 꽃부터 탐색
			for (int i = idx; i < N; i++) {
				// 시작일이 마지막 종료일보다 길어서 연속되지 않으면 종료
				if (last < flower[i][0])
					break;

				if (max < flower[i][1]) {
					isFound = true;
					max = flower[i][1];
					idx = i + 1;
				}

			}

			// 갱신 못했으면 while 종료
			if (!isFound)
				break;

			// 갱신했으면 해당 정보 저장
			cnt++;
			last = max;
		}

		// 출력
		// 목표일까지 도달 불가시 0, 도달시 cnt
		System.out.println(last < target ? 0 : cnt);
	}
}
