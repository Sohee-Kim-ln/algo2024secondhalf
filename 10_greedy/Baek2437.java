package algo240809;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2437 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int[] nums = new int[N];

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		// 저울추 받기
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(stz.nextToken());
		}

		Arrays.sort(nums);
		// 가장 작은 숫자가 숫자가 1이 아니라면
		if (nums[0] != 1) {
			System.out.println(1);
		}
		// 가장 작은 숫자가 1 이라면
		else {
			int sum = nums[0];
			for (int i = 1; i < N; i++) {
				// sum+1 보다 새 수가 작아서 연속이 가능하면 더해서 연장
				// 새 수 1개만 쓰는 케이스 포함을 위해 sum에 +1
				if (sum + 1 >= nums[i])
					sum += nums[i];

				// 새 수가 더 크면 연속 불가능이므로 나와서 출력
				else
					break;

			}
			System.out.println(sum + 1);
		}
	}
}
