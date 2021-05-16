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

		if (s != 0) {
			return -1;
		}

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
        ArrayList<Integer> adjs = this.adjList[index];

		for (int i = 0; i < adjs.size(); i++) {
			int curr = adjs.get(i);
			if (visited[curr] == false) {
				this.longAns[index] += dfs(curr);
			}
		}
		this.answer += Math.abs(longAns[index]);
		return longAns[index];
	}
}
