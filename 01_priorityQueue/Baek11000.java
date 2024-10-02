package algo241002;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Comparator;

// 강의 정렬시 PriorityQueue<int[]>
// 메모리 79152KB, 시간 628ms
public class Baek11000 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bfr.readLine());

		// 시작시간 오름차순, 종료시간 오름차순 정렬
		PriorityQueue<int[]> pqClass = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] != o2[0])
					return Integer.compare(o1[0], o2[0]);
				else
					return Integer.compare(o1[1], o2[1]);
			}
		});

		// 최종 종료시간 오름차순 정렬
		PriorityQueue<Integer> pqRoom = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));

		// 강의 정보 받기
		StringTokenizer stz;
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			int S = Integer.parseInt(stz.nextToken());
			int T = Integer.parseInt(stz.nextToken());
			pqClass.add(new int[] { S, T });
		}

		// 첫강의
		pqRoom.add(pqClass.poll()[1]);

		while (!pqClass.isEmpty()) {
			int[] now = pqClass.poll();

			// 시작시간이 가장 빨리 강의실보다 크다면 갱신을 위해 제거
			if (now[0] >= pqRoom.peek())
				pqRoom.poll();

			// 현재 강의 종료시점으로 방 갱신 혹은 추가
			pqRoom.add(now[1]);
		}

		System.out.println(pqRoom.size());

	}
}
