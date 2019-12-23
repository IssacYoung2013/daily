package com.issac.algo;

import java.util.Stack;
import java.util.Vector;

/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
public class Path {

    private Graph g;

    public boolean[] visited;

    private int s;

    private int[] from;

    public Path(Graph g, int s) {
        this.g = g;
        assert s >= 0 && s < g.V();
        this.s = s;
        visited = new boolean[g.V()];
        from = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }

        dfs(s);
    }

    private void dfs(int v) {
        visited[v] = true;
        for (int i : g.adj(v)) {
            if (!visited[i]) {
                from[i] = v;
                dfs(i);
            }
        }
    }

    boolean hasPath(int w) {
        assert w >= 0 && w < g.V();
        return visited[w];
    }

    void showPath(int w) {
        assert hasPath(w);
        Vector<Integer> path = path(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.elementAt(i));
            if (i == path.size() - 1) {
                System.out.println();
            } else {
                System.out.print("->");
            }
        }
    }

    Vector<Integer> path(int w) {
        assert hasPath(w);
        // 通过from数组查找s到w的路径
        Stack<Integer> s = new Stack<>();
        int p = w;
        while (p != -1) {
            s.push(p);
            p = from[p];
        }

        // 从栈中依次提取路径
        Vector res = new Vector();
        while (!s.empty()) {
            res.add(s.pop());
        }
        return res;
    }

}
