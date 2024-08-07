package algo240807;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Baek18352 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String[] tempS = bfr.readLine().split(" ");
		int N = Integer.parseInt(tempS[0]);
		int M = Integer.parseInt(tempS[1]);
		int K = Integer.parseInt(tempS[2]);
		int X = Integer.parseInt(tempS[3]);

		// 도로 정보 저장용
		LinkedList<Integer>[] list = new LinkedList[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new LinkedList<Integer>();
		}

		// 거리 정보 저장용
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		// 도로 정보 받기
		for (int i = 0; i < M; i++) {
			tempS = bfr.readLine().split(" ");
			int start = Integer.parseInt(tempS[0]);
			int end = Integer.parseInt(tempS[1]);

			list[start].add(end);
		}

		// 거리 K인 도시 저장용 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		Queue<Integer> quu = new LinkedList<>();

		// 시작점 넣기
		dist[X] = 0;
		quu.add(X);

		// 탐색
		while (!quu.isEmpty()) {
			int curr = quu.poll();
			for (int i = 0; i < list[curr].size(); i++) {
				int temp = list[curr].get(i);
				if (dist[temp] > dist[curr] + 1) {
					dist[temp] = dist[curr] + 1;
					quu.add(temp);
				}
			}

		}
		
		for(int i=1;i<=N;i++) {
			if(dist[i]==K)
				pq.add(i);
		}
		


		if (pq.isEmpty())
			System.out.println("-1");
		else {
			while (!pq.isEmpty()) {
				System.out.println(pq.poll());
			}
		}

	}
}
