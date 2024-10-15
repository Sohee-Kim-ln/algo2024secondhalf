package algo241015;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek11003TimeOver {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int L = Integer.parseInt(stz.nextToken());

		int[] arr = new int[N];
		int[] D = new int[N];
		
		// {크기, 위치}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));

		// 배열 정보 받기
		stz = new StringTokenizer(bfr.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stz.nextToken());
			pq.add(new int[] { arr[i], i });
		}

		// 수가 적은 것부터 뽑아서 뒤 3칸 채우기
		while (!pq.isEmpty()) {
			int[] temp = pq.poll();

			for (int i = 0; i < L; i++) {
				int idx = temp[1] + i;

				// 경계조건
				if (idx >=N)
					continue;

				// 아직 채워지지 않았으면 입력
				if (D[idx] == 0)
					D[idx] = temp[0];
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) 
			sb.append(D[i]).append(' ');
		
		sb.setLength(sb.length()-1);
		System.out.println(sb);

	}
}
