package com.pjmike.design.adapter;

/**
 * @description:
 * @author: 13572
 * @create: 2019/06/02 17:25
 */
public class Main {
    public static void main(String[] args) {
        WildTurkey turkey = new WildTurkey();
        Duck duck = new TurkeyAdapter(turkey);
        duck.quack();
    }
}
