package algo240930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek13164 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());

		int[] arr = new int[N];

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

		int sum = 0;
		int diff;

		// 유치원생 정보 받기
		stz = new StringTokenizer(bfr.readLine(), " ");
		arr[0] = Integer.parseInt(stz.nextToken());

		// 차이 계산
		for (int i = 1; i < N; i++) {
			arr[i] = Integer.parseInt(stz.nextToken());
			diff = arr[i] - arr[i - 1];
			sum += diff;
			pq.add(diff);
		}

		// 차이가 큰 부분 조 갯수-1만큼 제거
		for (int i = 0; i < K - 1; i++)
			sum -= pq.poll();

		// 출력
		System.out.println(sum);

	}
}
