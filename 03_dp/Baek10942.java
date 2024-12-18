package algo241218;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek10942 {
	static int N;
	static int[] arr;
	static boolean[][] palin, visited;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(bfr.readLine());
		arr = new int[N + 1];
		palin = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		// 수열 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(stz.nextToken());

		// 질문 정보 받아서 대답
		int M = Integer.parseInt(bfr.readLine());

		for (int q = 0; q < M; q++) {
			stz = new StringTokenizer(bfr.readLine());
			int S = Integer.parseInt(stz.nextToken());
			int E = Integer.parseInt(stz.nextToken());

			sb.append(isPal(S, E) ? 1 : 0).append('\n');
		}

		// 출력
		System.out.print(sb);

	}

	static boolean isPal(int start, int end) {
		// 시작 끝 바꼈으면 진입 전까지 펠린드롬이므로 true
		if (start > end)
			return true;

		// 이미 방문됐으면 이전 결과 리턴
		if (visited[start][end])
			return palin[start][end];

		// 현재글자 다르면 false
		if (arr[start] != arr[end]) {
			palin[start][end] = false;
			visited[start][end] = true;
			return false;
		}

		// 현재글자 같으면 한글자씩 줄여서
		palin[start][end] = isPal(start + 1, end - 1);
		visited[start][end] = true;
		return palin[start][end];

	}
}
