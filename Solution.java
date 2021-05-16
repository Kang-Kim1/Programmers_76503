import java.util.*;
class Solution {
	long answer = 0;
	ArrayList<Integer>[] adjList;
	int len;
	boolean[] visited;
	long[] longAns;

	public long solution(int[] a, int[][] edges) {
		this.len = a.length;
		this.adjList = new ArrayList[this.len];
		this.visited = new boolean[this.len];
		this.longAns = new long[this.len];

        long s = 0;
        
		for (int i = 0; i < this.len; i++) {
			this.longAns[i] = a[i];
            s += a[i];
            this.adjList[i] = new ArrayList<Integer>();
		}

        // input 유효성 확인 - 전체 합이 0이 아닐 경우 불가능
		if (s != 0) {
			return -1;
		}

        // 간선 정보 adjList 에 입력
		for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            this.adjList[node1].add(node2);
            this.adjList[node2].add(node1);
		}

		dfs(0);
        
		return answer;
	}

    
	public long dfs(int index) {
		this.visited[index] = true;

        // 간선 노드를 확인하며 가중치 업데이트
		for (int i = 0; i < this.adjList[index].size(); i++) {
			int curr = this.adjList[index].get(i);
			if (visited[curr] == false) {
				this.longAns[index] += dfs(curr);
			}
		}
		this.answer += Math.abs(longAns[index]);
		return longAns[index];
	}
}
