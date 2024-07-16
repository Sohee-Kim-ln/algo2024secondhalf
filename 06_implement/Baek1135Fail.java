package algo240716;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;


// 실패

public class Baek1135Fail {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		String[] tempS = bfr.readLine().split(" ");

		// 노드들 배열 만들기
		Node[] company = new Node[N];
		for (int i = 0; i < N; i++) {
			company[i] = new Node();
		}

		// 부모 정보 입력
		for (int i = 0; i < N; i++) {
			int parentNew = Integer.parseInt(tempS[i]);
			company[i].parent = parentNew;
			if (parentNew != -1)
				company[parentNew].children++;
		}

		// 계산할 노드 후보 번호큐
		Queue<Integer> quu = new LinkedList<>();

		// 큐에 리프노드 담기
		for (int i = 0; i < N; i++) {
			if (company[i].children == 0)
				quu.add(i);
		}

		// 큐가 빌때까지 계산
		while (!quu.isEmpty()) {
			// 현재 고려노드 꺼내기
			int now = quu.poll();
			Node curr = company[now];

			// 현재 고려노드의 소요시간 계산
			if (curr.maxleaf != 1)
				curr.time = Math.max(curr.maxleaf + 1 + curr.maxcnt, curr.children);
			else
				curr.time = curr.children;

			// 부모노드가 있으면
			if (curr.parent != -1) {
				// 부모 노드의 리프중 최대값 갱신
				if (company[curr.parent].maxleaf < curr.time) {
					company[curr.parent].maxleaf = curr.time;
					company[curr.parent].maxcnt = 0;
				}

				// 최대값이 겹치면 ++
				else if (company[curr.parent].maxleaf == curr.time && curr.time != 0) {
					company[curr.parent].maxcnt++;
				}

				// 부모 노드의 계산된 자식노드 수++
				company[curr.parent].cnted++;

				// 부모 노드의 자식노드 들이 전부 계산됐으면 후보큐에 부모노드 넣기
				if (company[curr.parent].cnted == company[curr.parent].children)
					quu.add(curr.parent);
			}
		}

		// 테스트용 출력

		for (int i = 0; i < N; i++) {
			System.out.println(i + " " + company[i].parent + " " + company[i].time + " " + company[i].maxleaf + " "
					+ company[i].maxcnt);
			;
		}
		System.out.println();

		System.out.println(company[0].time);

	}

	static class Node {
		int parent = -1;
		int time;
		int children;
		int cnted;
		int maxleaf = -1;
		int maxcnt;

		Node() {
		}

		Node(int parent) {
			this.parent = parent;
		}
	}

}
