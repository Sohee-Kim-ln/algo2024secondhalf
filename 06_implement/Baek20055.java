package algo241203;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek20055 {

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		int N2 = (N << 1);

		int[] belt = new int[N2];
		boolean[] hasRobot = new boolean[N2];

		int zero = 0;
		int term = 0;

		int up = 0;
		int down = N - 1;

		Queue<Integer> robot = new LinkedList<>();

		// 내구도 정보 받기
		stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < N2; i++)
			belt[i] = Integer.parseInt(stz.nextToken());

		// 단계 진행
		while (true) {
			term++;

			// 벨트 회전
			up = up == 0 ? N2 - 1 : up - 1;
			down = (up + N - 1) % N2;

			// 로봇 내리기
			if (hasRobot[down])
				hasRobot[down] = false;

			// 로봇 이동
			int length = robot.size();

			for (int i = 0; i < length; i++) {
				int now = robot.poll();

				// 현재 단계에서 내린 로봇이면 continue;
				if (now == down)
					continue;

				int next = (now + 1) % N2;

				// 다음칸에 로봇이 없고 내구도가 있으면 로봇 이동
				if (!hasRobot[next] && belt[next] != 0) {
					if (next == down)
						hasRobot[next] = false;
					else {
						hasRobot[next] = true;
						robot.add(next);
					}
					hasRobot[now] = false;
					belt[next]--;
					if (belt[next] == 0)
						zero++;
				}
				// 로봇 이동 못하면
				else
					robot.add(now);

			}

			// 로봇 올림
			if (belt[up] != 0) {
				robot.add(up);
				hasRobot[up] = true;
				belt[up]--;
				if (belt[up] == 0)
					zero++;
			}

			// 내구도0 개수 확인
			if (zero >= K)
				break;
		}
		System.out.println(term);
	}
}
