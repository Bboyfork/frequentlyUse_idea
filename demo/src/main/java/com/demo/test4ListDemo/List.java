package com.demo.test4ListDemo;



import com.sun.istack.internal.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.UnaryOperator;

public interface List<E> extends Collection<E> {
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    @NotNull Iterator<E> iterator();
    @NotNull Object[] toArray();
    @NotNull <T> T[] toArray(@NotNull T[] a);


}
