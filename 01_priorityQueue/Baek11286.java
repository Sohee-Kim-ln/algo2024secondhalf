package algo240709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Comparator;

// 40분 31초 / 우선순위큐 및 Comparator 복습 겸해서 풀은 시간

public class Baek11286 {
	public static void main(String[] args) throws Exception {
		// 입력받아서 저장하기
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 숫자 정렬용 우선순위큐
		PriorityQueue<Node> quu = new PriorityQueue<>(new NodeComparator());

		// 명령 횟수
		int N = -1;
		N = Integer.parseInt(bfr.readLine());

		// 명령 실행
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(bfr.readLine());

			// 0이면 출력 실행
			if (temp == 0) {
				// 큐가 비어있지 않으면 값 출력 후 제거
				if (quu.size() != 0) {
					Node poped = quu.poll();
					int popedNum = poped.isMinus == false ? poped.value : poped.value * -1;
					System.out.println(popedNum);
				}
				// 큐가 비어있으면 0 출력
				else
					System.out.println(0);

			}
			// 0이 아니면 큐에 넣기
			else
				quu.add(new Node(temp));

		}

	}

}

// 절댓값을 분리저장하는 노드
class Node implements Comparator<Node> {
	boolean isMinus;
	int value;

	Node() {
		this.isMinus = false;
		this.value = 0;
	}

	Node(int number) {
		this.isMinus = number < 0 ? true : false;
		this.value = Math.abs(number);
	}

	public int compare(Node o1, Node o2) {
		return o1.value - o2.value;
	}
}

// 노드 비교연산용
class NodeComparator implements Comparator<Node> {
	@Override
	public int compare(Node o1, Node o2) {
		if (o1.value > o2.value)
			return 1;
		else if (o1.value < o2.value)
			return -1;
		// 절댓값이 같을 때
		else {
			// o1>0이면 o2값 플마 상관없이 o1가 더 크다고 판단
			if (o1.isMinus == false)
				return 1;
			// o1<0이면 o2값 플마 상관없이 o2가 더 크다고 판단
			else
				return -1;
		}
	}

}