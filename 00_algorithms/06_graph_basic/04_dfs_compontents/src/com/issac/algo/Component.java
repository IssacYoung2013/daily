package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
public class Component {

    private Graph g;

    public boolean[] visited;

    private int ccount;

    private int[] id;

    public Component(Graph g) {
        this.g = g;
        id = new int[g.V()];
        visited = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }

        for (int i = 0; i < g.V(); i++) {
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
        }
    }

    private void dfs(int v) {
        visited[v] = true;

        id[v] = ccount;
        for (Integer integer : g.adj(v)) {
            if (!visited[integer]) {
                dfs(integer);
            }
        }
    }

    public int count() {
        return ccount;
    }

    boolean isConnected(int v, int w) {
        assert v >= 0 && v < g.V();
        assert w >= 0 && w < g.V();
        return id[v] == id[w];
    }
}
