package algo240728;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;

public class Baek17143 {
	// 0상하좌우
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };
	static int[] rev = { 0, 2, 1, 4, 3 };

	static Shark[] sharks;
	static int[][] sea;
	static int R, C;
	static int man;
	static int closer = 0;
	static int minr = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String[] tempS = bfr.readLine().split(" ");

		R = Integer.parseInt(tempS[0]);
		C = Integer.parseInt(tempS[1]);
		int M = Integer.parseInt(tempS[2]);

		// 상어 정보 배열. 0안씀
		sharks = new Shark[M + 1];
		sea = new int[R + 1][C + 1];

		// 낚시왕 위치
		man = 0;

		// 다음줄 중 제일 땅에 가까운 상어 번호
		closer = 0;
		minr = Integer.MAX_VALUE;

		// 잡은 상어크기 합
		int sum = 0;

		// 상어정보 입력받기
		for (int i = 0; i < M; i++) {
			tempS = bfr.readLine().split(" ");
			int nr = Integer.parseInt(tempS[0]);
			int nc = Integer.parseInt(tempS[1]);
			int ns = Integer.parseInt(tempS[2]);
			int nd = Integer.parseInt(tempS[3]);
			int nz = Integer.parseInt(tempS[4]);

			sharks[i + 1] = new Shark(nr, nc, ns, nd, nz);
			sea[nr][nc] = i + 1;

			if (nc == 1 && minr > nr) {
				closer = i + 1;
				minr = nr;
			}

		}

		// 1초단위 행동들 시작
		while (man <= C) {

			// 낚시왕 한칸 이동
			man++;

			// 상어 잡기
			if (closer != 0) {
				sharks[closer].r = 0;
				sum += sharks[closer].z;
//				System.out.println(closer + " 잡힘");
			}
			// 다음줄 중 제일 땅에 가까운 상어 정보 초기화
			closer = 0;
			minr = Integer.MAX_VALUE;

			// 바다의 상어위치 리셋
			sea = new int[R + 1][C + 1];

			// 상어이동 시작
			for (int i = 1; i <= M; i++) {
				// 상어 r ==0 이면 이미 잡히거나 죽은 상어이므로 스킵
				if (sharks[i].r == 0)
					continue;

				move(i);


			}


		}
		System.out.println(sum);
	}

	static void move(int number) {
		Shark target = sharks[number];
		int td = target.d;
		int step = td <= 2 ? dr[td] : dc[td];
		// 상하, 좌우
		int now = td <= 2 ? target.r : target.c;
		int limit = td <= 2 ? R : C;
		int reverse = 1;


		for (int i = 0; i < target.s; i++) {
			int next = now + step * reverse;
			// 경계조건
			if (next < 1 || next > limit)
				reverse *= -1;
			now = now + step * reverse;
		}

		int nr = td <= 2 ? now : sharks[number].r;
		int nc = td <= 2 ? sharks[number].c : now;

		// 상어정보에 결과 저장
		sharks[number].r = nr;
		sharks[number].c = nc;

		if (reverse == -1)
			sharks[number].d = rev[sharks[number].d];
		

		int bigger = number;
		
		// 같은 칸에 상어가 있으면
		if (sea[nr][nc] != 0) {
			// 크기 비교 후
			if (sharks[sea[nr][nc]].z > sharks[number].z) {
				bigger = sea[nr][nc];
				sharks[number].r = 0;
			}
			else {
				bigger=number;
				sharks[sea[nr][nc]].r=0;
			}
		}

		// 큰 상어 번호를 바다배열에 저장
		sea[nr][nc] = bigger;



		// 다음에 낚시왕이 잡을 줄이면 가까운 상어 저장
		if (nr != 0 && nc == man + 1 && minr >= nr) {
			closer = bigger;
			minr = nr;
		}

	}

	static class Shark implements Comparable<Shark> {
		int r, c, s, d, z;

		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			if (this.r > o.r)
				return 1;
			else if (this.r == o.r)
				return 0;
			else // if (this.r < o.r)
				return -1;
		}

		public String toString() {
			return MessageFormat.format("상어 ({0},{1}) 무게 {2}", r, c, z);
		}
	}
}
