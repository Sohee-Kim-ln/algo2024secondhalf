package algo241002;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Arrays;

// 강의 정렬시 Arrays.sort
// 메모리 76768KB, 시간 656ms
public class Baek11000_2 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bfr.readLine());

		int[][] lecture = new int[N][2];

		// 최종 종료시간 오름차순 정렬
		PriorityQueue<Integer> pqRoom = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));

		// 강의 정보 받기
		StringTokenizer stz;
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			lecture[i][0] = Integer.parseInt(stz.nextToken());
			lecture[i][1] = Integer.parseInt(stz.nextToken());
		}

		// 시작시간 오름차순, 종료시간 오름차순 정렬
		Arrays.sort(lecture,
				(o1, o2) -> o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));

		// 첫강의
		pqRoom.add(lecture[0][1]);

		for (int i = 1; i < N; i++) {
			// 시작시간이 가장 빨리 강의실보다 크다면 갱신을 위해 제거
			if (lecture[i][0] >= pqRoom.peek())
				pqRoom.poll();

			// 현재 강의 종료시점으로 방 갱신 혹은 추가
			pqRoom.add(lecture[i][1]);
		}

		System.out.println(pqRoom.size());

	}
}
