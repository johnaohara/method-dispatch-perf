package org.jboss.perf.context;

import org.jboss.perf.handler.RestHandler;

public abstract class AbstractContext {

    protected final RestHandler[] handlerChain;

    private int count = 0;

    protected AbstractContext(RestHandler[] handlerChain) {
        this.handlerChain = handlerChain;
    }

    public void incCount(){
        this.count++;
    }

    public int getCount(){
        return this.count;
    }


    public abstract void handleSomething() throws Throwable;
}
