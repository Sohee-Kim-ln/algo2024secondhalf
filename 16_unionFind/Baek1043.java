package algo240922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek1043 {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int cnt = 0;

		// 유니온 파인드 그룹 저장
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		// 진실 아는 사람
		stz = new StringTokenizer(bfr.readLine(), " ");
		int T = Integer.parseInt(stz.nextToken());

		for (int i = 0; i < T; i++) {
			parent[Integer.parseInt(stz.nextToken())] = 0;
		}

		// 파티 첫 사람
		ArrayDeque<Integer> firstMan = new ArrayDeque<>();

		// 파티 정보 받기
		for (int party = 0; party < M; party++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			// 파티의 사람 수
			int P = Integer.parseInt(stz.nextToken());

			// 파티 첫 사람 저장
			int first = Integer.parseInt(stz.nextToken());
			firstMan.add(first);

			// 파티 참여 정보 받아서 합치기
			for (int p = 1; p < P; p++) {
				int temp = Integer.parseInt(stz.nextToken());
				union(first, temp);
			}
		}

		// 파티 첫 사람들의 그룹이 0이 아니면 거짓말 가능
		while (!firstMan.isEmpty()) {
			int now = firstMan.poll();
			if (find(now) != 0)
				cnt++;
		}

		// 출력
		System.out.println(cnt);

	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);

	}
}
