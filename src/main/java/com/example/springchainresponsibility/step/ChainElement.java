package com.example.springchainresponsibility.step;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface Chain element.
 * Базовый интерфейс одного шага обработки
 *
 * @param <T> the type parameter
 */
public interface ChainElement<T> {
    /**
     * Метод для инициализации цепочки вызовов
     *
     * @param <T>      the type parameter
     * @param steps    the steps - упорядоченный список обработчиков
     * @param lastStep the last step
     * @return the t
     */
    static <T extends ChainElement<T>> T buildChain(List<T> steps, T lastStep) {
        if (steps.isEmpty()) {
            return lastStep;
        }

        List<T> ts = new ArrayList<T>();

        T reduce = steps.stream().reduce((e1, e2) -> {
            e1.setNext(e2);
            ts.add(e1);
            return e2;
        }).get();

        reduce.setNext(lastStep);

        ts.add(reduce);

        return ts.get(0);
    }

    void setNext(T nextStep);
}
