package org.jboss.perf.context;

import org.jboss.perf.handler.*;

public class UnrollContextImpl extends AbstractContext{
    public UnrollContextImpl(RestHandler[] handlerChain) {
        super(handlerChain);
    }

    @Override
    public void handleSomething() throws Exception {
        for( int chainPos = 0 ; chainPos < handlerChain.length ; chainPos++ ){
            var handler = handlerChain[chainPos];
            if (handler instanceof BlockingHandler) {
                handler.handle(this);
            } else if (handler instanceof ExceptionHandler) {
                handler.handle(this);
            } else if (handler instanceof InputHandler) {
                handler.handle(this);
            } else if (handler instanceof RandomHandler) {
                handler.handle(this);
            } else if (handler instanceof RoutingHandler) {
                handler.handle(this);
            } else {
                // megamorphic call for other handlers
                handler.handle(this);
            }
            handlerChain[chainPos].handle(this);
        }

    }
}
