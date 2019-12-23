package com.issac.algo;

import java.util.Vector;

/**
 * @author: ywy
 * @date: 2019-12-20
 * @desc: 稠密图 邻接矩阵 Adjacency Matrix
 */
public class DenseGraph {

    /**
     * 节点数
     */
    private int n;
    /**
     * 边数
     */
    private int m;

    boolean directed;

    /**
     * 图的具体数据
     */
    private boolean[][] g;

    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new boolean[n][n];
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

        if (hasEdge(v, w)) {
            return;
        }
        g[v][w] = true;
        if (!directed) {
            g[w][v] = true;
        }
        m++;
    }

    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return g[v][w];
    }

    public Iterable adj(int v) {
        assert v >= 0 && v < n;
        Vector<Integer> adjG = new Vector<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i]) {
                adjG.add(i);
            }
        }
        return adjG;
    }
}
