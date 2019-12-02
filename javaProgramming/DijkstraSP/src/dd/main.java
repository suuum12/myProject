package dd;

import java.util.*;
public class main {
	public static void main(String[] args) {
		int[][] weight = { // [�׸� 9-5-2](a)�� �Է±׷���
				
			{ 0, 10, 0, 5, 0},
			{ 0, 0, 1 , 2, 1}, 
			{ 0, 4, 0, 0, 4}, 
			{ 0, 3, 9, 0, 2}, 
			{ 7, 0, 6, 0, 0}
		};
		int N = weight.length;
		List<Edge>[] adjList = new List[N];
		for (int i = 0; i < N; i++) {  // ��������Ʈ �����
			adjList[i] = new LinkedList<>();
			for (int j = 0; j < N; j++) {
				if (weight[i][j] != 0) {
					Edge e = new Edge(i,j, weight[i][j]);
					adjList[i].add(e);
				}
			}
		}
		DijkstraSP d = new DijkstraSP(adjList);

		System.out.println("���� 0���κ����� �ִܰŸ�");
		int[] distance = d.shortestPath(0);	 
		
		for (int i = 0; i < distance.length; i++) {
			if (distance[i] == Integer.MAX_VALUE)
				System.out.println("0�� " + i +" ���̿� ��� ����.");
			else
				System.out.println("[0, " + i + "] = " +distance[i]);
			
		}
		
		System.out.printf("\n���� 0���κ����� �ִ� ���\n");
		for (int i = 1; i < d.N; i++){
			int back = i;
			System.out.print(back);
			while (back!= 0) {
				System.out.print("<-"+d.previous[back]);
				back = d.previous[back];
				
			}
			System.out.println();
				
		} 
		for (int k = 0; k < d.N; k++){
			
			System.out.print(d.previous[k]+" ");
				
				}
	}
}