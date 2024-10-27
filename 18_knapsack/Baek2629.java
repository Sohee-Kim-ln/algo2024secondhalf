package algo241027;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baek2629 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		StringTokenizer stz;

		int[] weight = new int[N];
		boolean[] check = new boolean[40001];

		StringBuilder sb = new StringBuilder();

		Set<Integer> checked = new HashSet<>();
		Set<Integer> candidate = new HashSet<>();

		// 추 무게 받기
		stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(stz.nextToken());

			weight[i] = now;
			check[now] = true;

			candidate.add(now);

			// 이미 확인 가능한 수들에 대해
			for (int w : checked) {
				// 현재 추를 더하거나 뺐을 때 확인 가능한 수 계산
				int prev = Math.abs(w - now);
				int next = w + now;

				// 뺀 수 표시
				if (!check[prev]) {
					check[prev] = true;
					candidate.add(prev);
				}

				// 더한 수 표시
				if (!check[next]) {
					check[next] = true;
					candidate.add(next);
				}
			}

			// 추가된 수 저장
			checked.addAll(candidate);
		}

		// 구슬 갯수
		int K = Integer.parseInt(bfr.readLine());

		// 구슬 정보 받아서 확인
		stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 0; i < K; i++) {
			int now = Integer.parseInt(stz.nextToken());

			sb.append(check[now] ? 'Y' : 'N').append(' ');
		}

		// 출력
		sb.setLength(sb.length() - 1);
		System.out.println(sb);

//		System.out.println(Arrays.toString(check));
	}
}
