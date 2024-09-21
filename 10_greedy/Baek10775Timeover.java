package algo240921;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Baek10775Timeover {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int G = Integer.parseInt(bfr.readLine());
		int P = Integer.parseInt(bfr.readLine());

		boolean[] used = new boolean[G + 1];
		int cnt = 0;

		// 도킹 정보 받아서 비행기 주차
		for (int i = 0; i < P; i++) {
			int now = Integer.parseInt(bfr.readLine());
			boolean docked = false;

			// 넣을 수 있는 가장 먼 칸에 넣기
			for (int j = now; j > 0; j--) {
				if (!used[j]) {
					used[j] = true;
					cnt++;
					docked = true;
					break;
				}
			}

			// 도킹되지 않았으면 폐쇄
			if (!docked)
				break;
		}

		System.out.println(cnt);

	}
}
