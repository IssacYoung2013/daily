package com.issac.jvm.charpter3;

/**
 * @author: ywy
 * @date: 2019-11-22
 * @desc: 1. 对象可以在被GC时自我拯救
 * 2. 这种自救的机会只有一次，一个对象的 finalize() 方法最多只会执行一次
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对象第一个成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为 finalize 方法优先级低，暂停0.5秒等待
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }

        // 第二次自救失败
        SAVE_HOOK = null;
        System.gc();
        // 因为 finalize 方法优先级低，暂停0.5秒等待
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }

    }
}
