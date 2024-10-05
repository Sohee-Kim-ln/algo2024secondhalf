package algo241005;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek4779 {
	static boolean[] line;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		// 입력 있는동안 반복
		while (true) {
			String temp = bfr.readLine();

			// 입력 없으면 종료
			if (temp == null || temp.equals(""))
				break;

			int N = Integer.parseInt(temp);
			int length = (int) Math.pow(3, N);
			line = new boolean[length];
			line[0] = true;

			// 선 복제 반복
			for (int size = 1; size < length; size *= 3) {
				// 3분할해서 1번째를 3번째에 복사
				for (int idx = 0; idx < size; idx++) {
					line[idx + size * 2] = line[idx];
				}
			}

			// 출력 저장
			for (int i = 0; i < length; i++) {
				if (line[i])
					sb.append('-');
				else
					sb.append(' ');
			}

			sb.append("\n");

		}

		// 출력
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static void draw(int length) {

	}
}
