import java.util.*;

//***************************
//파일명: TopologicalSort
//작성자: 201814011 김은우
//작성일: 2020-06-08
//내용: [hw10_2] topological sort
//***************************

class DirectedGraph {
	private Node[] list; // 인접 리스트
	private int numberOfVertices; // 정점 수

	// 리스트의 노드 구조를 정의한 클래스
	private class Node {
		int vertex;
		Node link;
	}

	// 정점 수가 n인 그래프를 생성
	public DirectedGraph(int n) {
		list = new Node[n];
		numberOfVertices = n;
	}

	// 간선 <v1, v2>를 삽입
	public void addEdge(int v1, int v2) {
		// v1, v2가 올바른 정점 번호이면 list[v1]에 v2를 삽입
		if (v1 >= numberOfVertices || v2 >= numberOfVertices) {
			System.out.println("간선 삽입 오류 - 잘못된 정점 번호입니다. <" + v1 + "," + v2 + ">");
		} else {
			// 삽입하는 간선은 항상 리스트의 첫번째 노드로 삽입하도록 함
			Node n = new Node();
			n.vertex = v2;
			n.link = list[v1];
			list[v1] = n;
		}
	}

	// 구현을 확인하기 위해 인접 리스트를 모두 출력
	public void printAdjacencyList() {
		// 반복문을 이용하여 list[0], ... list[numberOfVertices-1]의 모든 정점 번호를 출력
		Node nn = new Node();
		System.out.println();
		for (int i = 0; i < numberOfVertices; i++) {
			System.out.print("정점 " + i + "에 인접한 정점 = ");
			nn = list[i];
			while (nn != null) {
				System.out.print(nn.vertex + " ");
				nn = nn.link;
			}
			System.out.println();
		}
	}

}

public class TopologicalSort {
	int n; // 그래프의 정점 수
	boolean[] visited; // 방문여부 체크 용
	List<Integer>[] adjList; // 인접리스트 형태의 입력 그래프
	List<Integer> sequence; // 위상 정렬 순서를 담을 리스트

	public TopologicalSort(List<Integer>[] graph) { // 생성자
		n = graph.length;
		visited = new boolean[n];
		adjList = graph;
		sequence = new ArrayList<>();
	}

	public List<Integer> tsort() { // 위상정렬을 위한 DFS 수행
		for (int i = 0; i < n; i++)
			if (!visited[i])
				dfs(i);
		Collections.reverse(sequence); // sequence를 역순으로 만들기
		return sequence;
	}

	public void dfs(int u) { // DFS 수행
		visited[u] = true;
		for (int v : adjList[u]) { // u의 방문이 끝나고 앞으로 방문해야하는 각 정점 v에 대해
			if (!visited[v])
				dfs(v);
		}
		sequence.add(u); // u에서 진출하는 간선이 더 이상 없으므로 u를 sequence에 추가
	}

	public static void main(String[] args) {
		System.out.println("hw10_2: 김은우");
		System.out.println();

		// 1) 정점 수 n을 입력 받음
		System.out.println("위상 정렬을 수행합니다. DAG를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		System.out.print("정점 수 입력: ");
		int n = scan.nextInt();

		// 2) 정점 수가 n인 방향 그래프를 생성
		DirectedGraph graph = new DirectedGraph(n);
		List<Integer>[] adjList = new List[n]; 
		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 3) 간선 수 e를 입력 받음
		System.out.print("간선 수 입력: ");
		int e = scan.nextInt();
		System.out.println();

		// 4) e 개의 간선 입력받아 그래프에 삽입
		System.out.println(e + "개의 간선 입력(각 간선은 정점 번호 2개를 whitespace로 구분하여 입력):");
		for (int i = 1; i <= e; i++) {
			System.out.print("간선" + i + " 입력: ");
			int v1 = scan.nextInt();
			int v2 = scan.nextInt();
			graph.addEdge(v1, v2);
			adjList[v1].add(v2);
		}
		
		TopologicalSort t = new TopologicalSort(adjList);
		List<Integer> sortedSeq = t.tsort();

		// 5) 그래프 구현을 확인
		graph.printAdjacencyList();
		System.out.print("위상정렬 결과: "+sortedSeq);
		scan.close();

	}

}
