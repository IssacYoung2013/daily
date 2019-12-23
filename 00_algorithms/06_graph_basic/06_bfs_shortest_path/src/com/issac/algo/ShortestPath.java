package com.issac.algo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
public class ShortestPath {

    private Graph g;

    public boolean[] visited;

    private int s;

    /**
     * 记录节点路径
     */
    private int[] from;

    /**
     * 记录节点次序
     */
    private int[] ord;

    public ShortestPath(Graph g, int s) {
        this.g = g;
        assert s >= 0 && s < g.V();
        this.s = s;
        visited = new boolean[g.V()];
        from = new int[g.V()];
        ord = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        ord[s] = 0;
        while (!q.isEmpty()) {
            int v = q.remove();
            for (Integer i : g.adj(v)) {
                if(!visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] +1;
                }
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
