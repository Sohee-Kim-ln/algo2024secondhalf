package algo240908;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek14567 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz;
		stz = new StringTokenizer(bfr.readLine(), " ");

		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int[] degreeIn = new int[N + 1];
		int[] term = new int[N + 1];
		
		StringBuilder sb = new StringBuilder();

		// 선수 과목 저장용
		ArrayList<Integer>[] subject = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			subject[i] = new ArrayList<>();
		}

		// 선수 과목 정보 받기
		int from, to;
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			from = Integer.parseInt(stz.nextToken());
			to = Integer.parseInt(stz.nextToken());

			subject[from].add(to);
			degreeIn[to]++;
		}

		// 위상정렬용 큐
		Queue<Integer> quu = new LinkedList<>();

		// 초기 노드 넣기
		for (int i = 1; i <= N; i++)
			if (degreeIn[i] == 0) {
				quu.add(i);
				term[i] = 1;
			}

		// 위상정렬
		while (!quu.isEmpty()) {
			int cur = quu.poll();
			for (int next : subject[cur]) {
				if (--degreeIn[next] == 0) {
					quu.add(next);
					term[next] = term[cur] + 1;
				}
			}
		}
		
		// 출력
		for(int i=1;i<=N;i++) {
			sb.append(term[i]).append(" ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);

	}
}
