package spring.aop.implementations;

import spring.aop.interfaces.MaxMethod;
import spring.aop.interfaces.MinMethod;

public class MinMethodImpl implements MinMethod {

    @Override
    public void min() {
        System.out.println("MaxMethodImpl.min()");
    }

}
