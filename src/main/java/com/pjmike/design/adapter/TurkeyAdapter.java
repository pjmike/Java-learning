package com.pjmike.design.adapter;

/**
 * @description:
 * @author: 13572
 * @create: 2019/06/02 17:24
 */
public class TurkeyAdapter implements Duck{
    private final Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }
}
