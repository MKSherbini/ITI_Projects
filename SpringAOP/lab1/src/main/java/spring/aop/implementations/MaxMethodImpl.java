package spring.aop.implementations;

import spring.aop.interfaces.MaxMethod;

public class MaxMethodImpl implements MaxMethod {

    @Override
    public void max() {
        System.out.println("MaxMethodImpl.max()");
    }

}
