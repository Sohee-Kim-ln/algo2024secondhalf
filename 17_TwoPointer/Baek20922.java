package algo241231;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek20922 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());

		int[] arr = new int[N];

		// i등장 수 카운터 배열
		int[] cnt = new int[100001];

		// 수열 정보 받기
		stz = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(stz.nextToken());

		// 투포인터용 변수
		int start = 0;
		int end = 0;
		int max = Integer.MIN_VALUE;

		// 투포인터 시작
		while (start < N && end < N) {
			// 연장 가능하면 연장 후 갱신
			if (cnt[arr[end]] < K) {
				cnt[arr[end]]++;
				end++;
				max = Math.max(max, end - start);
				continue;
			}

			// 연장 불가능하면 앞을 줄이기
			cnt[arr[start]]--;
			start++;
		}

		// 출력
		System.out.println(max);
	}
}
