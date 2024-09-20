package algo240920;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek1700 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());

		// 상태 저장용 변수
		boolean[] pluged = new boolean[K + 1];
		int use = 0;
		int cnt = 0;
		int[] order = new int[K];
		int[] tab = new int[N];

		// 등장위치 저장용 큐
		ArrayDeque<Integer>[] adq = new ArrayDeque[K + 1];

		for (int i = 1; i <= K; i++) {
			adq[i] = new ArrayDeque<>();
		}

		// 사용 순서 받기
		stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(stz.nextToken());
			adq[order[i]].add(i);
		}

		// 사용 순서에 따라 판별 시작
		for (int i = 0; i < K; i++) {
			int now = order[i];

			// 이미 꽂힌 기기면 continue
			if (pluged[now]) {
				adq[now].poll();
				continue;
			}

			// 빈 플러그 있으면 꽂기
			if (use != N) {
				pluged[now] = true;
				tab[use++] = now;
				adq[now].poll();
				continue;
			}

			// 빈 플러그 없으면
			int maxTime = 0;
			int latest = 0;

			// 재등장이 가장 늦은 기기 찾기
			for (int idx = 0; idx < N; idx++) {
				// 등장하지 않으면 최대시간 이후로 설정
				int thisTime = K;

				// 등장하면 저장
				if (!adq[tab[idx]].isEmpty())
					thisTime = adq[tab[idx]].peek();

				// 최대 갱신
				if (maxTime < thisTime) {
					maxTime = thisTime;
					latest = idx;
				}
			}

			// 교체
			pluged[tab[latest]] = false;
			tab[latest] = now;
			pluged[now] = true;
			adq[now].poll();
			cnt++;

		}
		
		// 출력
		System.out.println(cnt);

	}
}
