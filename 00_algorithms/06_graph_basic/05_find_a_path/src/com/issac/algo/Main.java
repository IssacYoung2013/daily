package com.issac.algo;


/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        String filename = "/Users/Issac/workspace/java/daily/00_algorithms/06_graph_basic/05_find_a_path/testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        Path path = new Path(g,0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);
    }
}
