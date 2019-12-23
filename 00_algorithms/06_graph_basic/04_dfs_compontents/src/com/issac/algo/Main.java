package com.issac.algo;


/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        // TestG1.txt
        String filename1 = "/Users/Issac/workspace/java/daily/00_algorithms/06_graph_basic/04_dfs_compontents/testG1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename1);
        Component component1 = new Component(g1);
        System.out.println("TestG1.txt, Component Count: " + component1.count());
        System.out.println();

        // TestG2.txt
        String filename2 = "/Users/Issac/workspace/java/daily/00_algorithms/06_graph_basic/04_dfs_compontents/testG2.txt";
        SparseGraph g2 = new SparseGraph(6, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename2);
        Component component2 = new Component(g2);
        System.out.println("TestG2.txt, Component Count: " + component2.count());
    }
}
