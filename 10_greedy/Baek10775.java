package algo240921;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek10775 {

	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int G = Integer.parseInt(bfr.readLine());
		int P = Integer.parseInt(bfr.readLine());
		int cnt = 0;
		parent = new int[G + 1];

		for (int i = 1; i <= G; i++)
			parent[i] = i;

		// 도킹된 비행기 그룹 맨앞-1에 주차
		for (int i = 0; i < P; i++) {

			// 전부 도킹됐으면 종료
			if (cnt == G)
				break;

			int now = Integer.parseInt(bfr.readLine());

			int docked = find(now);

			// 도킹할 수 없으면 폐쇄
			if (docked == 0)
				break;

			// 도킹하고 앞자리 그룹과 유니온
			cnt++;
			union(docked, docked - 1);
		}

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

	static int find(int idx) {
		if (idx == parent[idx])
			return idx;
		return parent[idx] = find(parent[idx]);
	}
}
