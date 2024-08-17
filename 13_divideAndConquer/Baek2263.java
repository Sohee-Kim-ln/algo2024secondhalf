package algo240817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2263 {
	static int N;
	static int[] inorder, postorder;
	static int[] preorder;
	static int pointer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());
		inorder = new int[N];
		postorder = new int[N];
		preorder = new int[N];
		
		// 인오더 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		for (int i = 0; i < N; i++)
			inorder[i] = Integer.parseInt(stz.nextToken());

		// 포스트오더 받기
		stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 0; i < N; i++)
			postorder[i] = Integer.parseInt(stz.nextToken());

		explore(0, N - 1, 0, N - 1);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(preorder[i]);
			if (i != N - 1)
				sb.append(" ");
		}
		
		System.out.println(sb);
		// 인오더 왼부오
		// 포스트오더 왼오부
		// 프레오더 부왼오
	}

	static void explore(int startp, int endp, int starti, int endi) {
		// 탐색범위 벗어나면 종료
		if (startp > endp)
			return;

		// 포스트 맨 오른쪽이 부모
		int parent = postorder[endp];
		preorder[pointer] = parent;
		pointer++;

		if (startp == endp)
			return;

		// 인오더에서 부모 찾기, 이분탐색으로 시간단축
		int location = -1;

		for (int i = starti; i <= endi; i++) {
			if (inorder[i] == parent)
				location = i;
		}

		// 인오더에서 왼자식 오른자식 길이 파악
		int lengthLeft = location - starti;
		int lengthRight = endi - location;

		// 찾은 부모 기준으로 왼 오 나눠서 탐색 재개
		explore(startp, startp + lengthLeft - 1, starti, location - 1);
		explore(endp - lengthRight, endp - 1, location + 1, endi);

		return;

	}
}
