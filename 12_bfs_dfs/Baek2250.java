package algo240814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2250 {

	static class Node {
		int left;
		int right;
		int number;
		int depth;

		Node(int left, int right) {
			this.left = left;
			this.right = right;
			this.number = 0;
			this.depth = 0;

		}
	}

	static Node[] tree;
	static boolean[] isRoot;
	static int root;
	static int cnt = 1;
	static int depthMax = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());

		// 초기화, 0안씀
		tree = new Node[N + 1];

		isRoot = new boolean[N + 1];

		Arrays.fill(isRoot, true);

		int widthMax = Integer.MIN_VALUE;
		int depthAns = 0;

		// 트리 정보 입력받기
		StringTokenizer stz;

		for (int i = 1; i <= N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			int parent = Integer.parseInt(stz.nextToken());
			int templ = Integer.parseInt(stz.nextToken());
			int tempr = Integer.parseInt(stz.nextToken());
			tree[parent] = new Node(templ, tempr);

			if (templ != -1)
				isRoot[templ] = false;

			if (tempr != -1)
				isRoot[tempr] = false;

		}

		// 루트노드 찾기
		for (int i = 1; i <= N; i++) {
			if (isRoot[i]) {
				root = i;
				break;
			}
		}

		tree[root].depth = 1;

		// 중위순회 시작
		cal(root);

		// 해당 깊이의 번호 최대최소 저장
		int[] min = new int[depthMax + 1];
		int[] max = new int[depthMax + 1];

		Arrays.fill(min, Integer.MAX_VALUE);
		Arrays.fill(max, Integer.MIN_VALUE);

		// 최고 최저 번호 계산
		for (int i = 1; i <= N; i++) {
			Node now = tree[i];

			// 해당깊이에서 최저값 갱신되면
			min[now.depth] = Math.min(min[now.depth], now.number);

			// 해당깊이에서 최고값 갱신되면
			max[now.depth] = Math.max(max[now.depth], now.number);
		}

		// 최고 너비 계산
		for (int i = 1; i <= depthMax; i++) {
			int width = max[i] - min[i] + 1;

			if (widthMax < width) {
				depthAns = i;
				widthMax = width;
			}
		}

		System.out.println(depthAns + " " + widthMax);

	}

	// 중위순회 돌면서 노드에 번호 부여, 최고최저 갱신
	static void cal(int num) {
		Node now = tree[num];
		// 왼자식 있으면
		if (now.left != -1) {
			tree[now.left].depth = now.depth + 1;
			cal(now.left);
		}

		// 번호 부여하고 카운트 증가, 최고 깊이 갱신
		now.number = cnt++;
		depthMax = Math.max(depthMax, now.depth);

		// 오른자식 있으면
		if (now.right != -1) {
			tree[now.right].depth = now.depth + 1;
			cal(now.right);
		}
	}

}
