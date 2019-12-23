package com.issac.algo;

import java.util.Vector;

/**
 * @author: ywy
 * @date: 2019-12-20
 * @desc: 稀疏图 邻接表
 */
public class SparseGraph {
    /**
     * 节点数
     */
    private int n;
    /**
     * 边数
     */
    private int m;

    boolean directed;

    Vector<Integer>[] g;

    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        this.m = 0;
        g = new Vector[n];
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<>();
        }
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

//        if (hasEdge(v, w)) {
//            return;
//        }
        g[v].add(w);
        if (v != w && !directed) {
            g[w].add(v);
        }
        m++;
    }

    private boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].elementAt(i) == w) {
                return true;
            }
        }
        return false;
    }
}
