package algo241016;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Baek4195 {
	static Map<String, Integer> id;
	static int[] parent;
	static int[] cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());

		StringBuilder sb = new StringBuilder();
		StringTokenizer stz;

		// 각 테스트 케이스에 대해
		for (int tc = 0; tc < T; tc++) {
			int F = Integer.parseInt(bfr.readLine());

			// 초기화
			id = new HashMap<>();
			int F2 = F << 1;

			parent = new int[F2];
			cnt = new int[F2];

			Arrays.fill(cnt, 1);

			for (int i = 0; i < F2; i++)
				parent[i] = i;

			int order = 0;

			// 친구관계 정보 받기
			for (int f = 0; f < F; f++) {
				stz = new StringTokenizer(bfr.readLine(), " ");

				String name1 = stz.nextToken();
				String name2 = stz.nextToken();

				// 없으면 Map에 삽입
				if (!id.containsKey(name1))
					id.put(name1, order++);

				if (!id.containsKey(name2))
					id.put(name2, order++);

				// 그룹 병합 후 결과 저장
				sb.append(union(id.get(name1), id.get(name2))).append("\n");
			}
		}

		// 출력
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static int union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b) {
			cnt[b] += cnt[a];
			parent[a] = b;
		}
		
		return cnt[b];
	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}
}
