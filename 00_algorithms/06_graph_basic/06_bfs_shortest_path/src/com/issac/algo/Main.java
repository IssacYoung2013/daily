package com.issac.algo;


/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        String filename = "/Users/Issac/workspace/java/daily/00_algorithms/06_graph_basic/06_bfs_shortest_path/testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        Path dfs = new Path(g,0);
        System.out.print("DFS : ");
        dfs.showPath(6);

        ShortestPath bfs = new ShortestPath(g,0);
        System.out.print("BFS : ");
        bfs.showPath(6);

        System.out.println();

        filename = "/Users/Issac/workspace/java/daily/00_algorithms/06_graph_basic/06_bfs_shortest_path/testG1.txt";
        SparseGraph g2 = new SparseGraph(13, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename);
        g2.show();

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        Path dfs2 = new Path(g2,0);
        System.out.print("DFS : ");
        dfs2.showPath(3);

        ShortestPath bfs2 = new ShortestPath(g,0);
        System.out.print("BFS : ");
        bfs.showPath(3);
    }
}
