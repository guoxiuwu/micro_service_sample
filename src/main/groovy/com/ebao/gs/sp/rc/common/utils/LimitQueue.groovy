package com.ebao.gs.sp.rc.common.utils

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class LimitQueue<E> extends LinkedList<E> {

    private transient int limitSize = Integer.MAX_VALUE

    public LimitQueue() {
        super()
    }

    public int limitSize(){
        return limitSize
    }

    public LimitQueue(int limitSize) {
        super()
        this.limitSize = limitSize
    }

    @Override
    boolean add(E e) {
        while (size() >= limitSize) {
            poll() //Retrieves and removes the head (first element) of this list.
        }
        return super.add(e)
    }
}
