package algo240911;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek7662 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());

		PriorityQueue<Integer> pqMin, pqMax;
		Map<Integer, Integer> map;

		StringTokenizer stz;

		StringBuilder sb = new StringBuilder();

		// 각 테케에 대해
		for (int tc = 0; tc < T; tc++) {
			// 초기화
			pqMin = new PriorityQueue<>();
			pqMax = new PriorityQueue<>(Comparator.reverseOrder());
			map = new HashMap<>();

			int K = Integer.parseInt(bfr.readLine());

			for (int i = 0; i < K; i++) {
				stz = new StringTokenizer(bfr.readLine(), " ");

				// 입력 명령어
				if (stz.nextToken().equals("I")) {
					int num = Integer.parseInt(stz.nextToken());
					pqMax.add(num);
					pqMin.add(num);

					// 없으면 0, 있으면 해당값에 +1;
					map.put(num, map.getOrDefault(num, 0) + 1);
				}
				// 삭제 명령어
				else {
					// 최대최소 결정 플래그
					boolean deleteMin = false;
					PriorityQueue<Integer> pq;

					// 최솟값 명령어는 플래그 on
					if (stz.nextToken().equals("-1"))
						deleteMin = true;

					pq = deleteMin ? pqMin : pqMax;

					while (!pq.isEmpty()) {
						int target = pq.poll();

						int cnt = map.getOrDefault(target, 0);

						if (cnt == 0)
							continue;

						if (cnt == 1)
							map.remove(target);
						else
							map.replace(target, cnt - 1);

						break;
					}
				}
			}

			int max = Integer.MAX_VALUE;
			int min = Integer.MIN_VALUE;
			
			// 출력
			if (map.isEmpty())
				sb.append("EMPTY\n");
			else {

				// 이미 삭제된 수가 제일 위에 있다면 제거
				while (!pqMin.isEmpty()) {
					min = pqMin.poll();
					if (map.get(min) != null)
						break;
				}
				while (!pqMax.isEmpty()) {
					max = pqMax.poll();
					if (map.get(max) != null)
						break;
				}

				sb.append(max).append(" ").append(min).append("\n");
			}
		}
		System.out.print(sb);
	}
}
