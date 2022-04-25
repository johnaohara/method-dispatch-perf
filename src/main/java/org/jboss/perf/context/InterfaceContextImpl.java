package org.jboss.perf.context;

import org.jboss.perf.handler.RestHandler;

public class InterfaceContextImpl extends AbstractContext{
    public InterfaceContextImpl(RestHandler[] handlerChain) {
        super(handlerChain);
    }

    @Override
    public void handleSomething() throws Exception {
        for( int chainPos = 0 ; chainPos < handlerChain.length ; chainPos++ ){
            handlerChain[chainPos].handle(this);
        }

    }
}
