package algo241007;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1976 {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int M = Integer.parseInt(bfr.readLine());

		// 그룹 초기 설정
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		// 연결 정보 받기
		for (int i = 1; i <= N; i++) {
			String temp = bfr.readLine();

			for (int j = 1; j <= N; j++) {
				// 문자열 인덱스로 char 지정해서 검사
				if (temp.charAt((j - 1) * 2) == '1')
					union(i, j);
			}
		}

		// 여행 정보 받고 초기그룹 저장
		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int now = Integer.parseInt(stz.nextToken());
		int group = find(now);

		// 트리거 변수
		boolean success = true;

		// 2번째 여행지부터 그룹 일치여부 판별
		for (int i = 1; i < M; i++) {
			now = Integer.parseInt(stz.nextToken());

			// 같그룹 아니면 표시 후 종료
			if (find(now) != group) {
				success = false;
				break;
			}
		}

		// 출력
		System.out.println(success ? "YES" : "NO");
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a < b)
			parent[a] = b;
		else
			parent[b] = a;
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = find(parent[a]);
	}
}
