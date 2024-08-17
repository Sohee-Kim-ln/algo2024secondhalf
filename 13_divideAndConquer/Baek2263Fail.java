package algo240817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리제한 128MB, 초과
public class Baek2263Fail {
	static int N;
	static int[] inorder, postorder;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());
		inorder = new int[N];
		postorder = new int[N];

		// 인오더 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		for (int i = 0; i < N; i++)
			inorder[i] = Integer.parseInt(stz.nextToken());

		// 포스트오더 받기
		stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 0; i < N; i++)
			postorder[i] = Integer.parseInt(stz.nextToken());

		String ans = explore(0, N - 1, 0, N - 1);

		System.out.println(ans);
		// 인오더 왼부오
		// 포스트오더 왼오부
		// 프레오더 부왼오
	}

	static String explore(int startp, int endp, int starti, int endi) {
		if (startp > endp)
			return "";

		if (startp == endp)
			return Integer.toString(postorder[endp]);

		// 포스트 맨 오른쪽이 부모
		int parent = postorder[endp];

		// 인오더에서 부모 찾기, 이분탐색으로 시간단축
		int location = searchi(starti, endi, parent);
		int lengthLeft = location - starti;
		int lengthRight = endi - location;

		// 찾은 부모 기준으로 왼 오 나눠서 탐색 재개
		String left = explore(startp, startp + lengthLeft - 1, starti, location - 1);
		String right = explore(endp - lengthRight, endp - 1, location + 1, endi);
		StringBuilder sb = new StringBuilder();

		sb.append(parent);
		if (left.length() != 0)
			sb.append(" ").append(left);
		if (right.length() != 0)
			sb.append(" ").append(right);

		return sb.toString();

	}

	static int searchi(int start, int end, int target) {
		for (int i = start; i <= end; i++) {
			if (inorder[i] == target)
				return i;
		}
		return -1;
	}
}
