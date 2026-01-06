package algo260106;

import java.io.*;
import java.util.*;

// 29분 54초
public class Baek1002 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 테케 갯수
		int T = Integer.parseInt(bfr.readLine());

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < T; i++) {
			StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

			float x1 = Float.parseFloat(stz.nextToken());
			float y1 = Float.parseFloat(stz.nextToken());
			float r1 = Float.parseFloat(stz.nextToken());
			float x2 = Float.parseFloat(stz.nextToken());
			float y2 = Float.parseFloat(stz.nextToken());
			float r2 = Float.parseFloat(stz.nextToken());

			float dist = (float) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

			// 원이 일치할 경우
			if(x1==x2 && y1==y2 && r1==r2){
				sb.append(-1).append('\n');
				continue;
			}
			
			// 원이 겹치지 않을 경우
			if (dist > r1 + r2) {
				sb.append(0).append('\n');
				continue;
			}

			// 원이 외접할 경우
			if (dist == r1 + r2) {
				sb.append(1).append('\n');
				continue;
			}

			// 한 원안에 다른 원 중심이 들어갈 경우
			if (dist <= r1 || dist <= r2) {
				float min = Math.min(r1, r2);
				float max = Math.max(r1, r2);

				// 원 안에 전부 들어가면
				if (dist + min < max) {
					sb.append(0).append('\n');
					continue;
				}

				// 원 안에서 내접하면
				else if (dist + min == max) {
					sb.append(1).append('\n');
					continue;
				}
				// 원 안에 안들어가서 2점 접하면

				else {
					sb.append(2).append('\n');
					continue;
				}
			}

			// 원이 2지점에서 겹칠 경우
			if (dist < r1 + r2) {
				sb.append(2).append('\n');
				continue;
			}

		}

		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}
}
