package algo241130;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek22945 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int[] abil = new int[N];

		// 개발자 능력치 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		for (int i = 0; i < N; i++)
			abil[i] = Integer.parseInt(stz.nextToken());

		// 투포인터 용 인덱스
		int left = 0;
		int right = N - 1;
		boolean isMinLeft = true;
		int max = Integer.MIN_VALUE;

		// 투포인터
		while (left < right) {

			// 왼쪽이 더 작으면
			if (abil[left] < abil[right])
				isMinLeft = true;
			// 오른쪽이 더 작으면
			else if (abil[left] > abil[right])
				isMinLeft = false;
			// 양끝이 같을 때 다음순서 고려해서 움직일 것 정하기
			else {
				if (abil[left + 1] < abil[right - 1])
					isMinLeft = false;
				else
					isMinLeft = true;
			}

			// 양끝 중 최소값 지정
			int min = isMinLeft ? abil[left] : abil[right];

			// 최대값 갱신
			max = Math.max(max, (right - left - 1) * min);

			if (isMinLeft)
				left++;
			else
				right--;
		}
		
		System.out.println(max);
	}
}
