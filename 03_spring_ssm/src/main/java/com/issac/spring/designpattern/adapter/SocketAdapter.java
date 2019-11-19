package com.issac.spring.designpattern.adapter;

/**
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
public class SocketAdapter implements GJSocket {

    private Object gbSocket;

    public SocketAdapter(Object gbSocket) {
        this.gbSocket = gbSocket;
    }

    @Override
    public void charge() {
        if (gbSocket instanceof DBSocket) {
            ((DBSocket) gbSocket).charge();
        } else if (gbSocket instanceof GBSocket) {
            ((GBSocket) gbSocket).charge();
        } else {
            ((GJSocket) gbSocket).charge();
        }
    }
}
