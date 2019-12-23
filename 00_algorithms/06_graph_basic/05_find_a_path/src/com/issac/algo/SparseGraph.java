package com.issac.algo;

import java.util.Vector;

/**
 * @author: ywy
 * @date: 2019-12-20
 * @desc: 稀疏图 邻接表
 */
public class SparseGraph implements Graph {
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

    @Override
    public int V() {
        return n;
    }

    @Override
    public int E() {
        return m;
    }

    @Override
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

    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].elementAt(i) == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable adj(int v) {
        assert v >= 0 && v < n;
        return g[v];
    }

    // 显示图的信息
    @Override
    public void show() {

        for (int i = 0; i < n; i++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0; j < g[i].size(); j++) {
                System.out.print(g[i].elementAt(j) + "\t");
            }
            System.out.println();
        }
    }
}
