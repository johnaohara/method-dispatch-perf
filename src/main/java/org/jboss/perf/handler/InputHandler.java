package org.jboss.perf.handler;

import org.jboss.perf.context.AbstractContext;

public class InputHandler implements RestHandler{

    private static final String name = InputHandler.class.getName();

    @Override
    public void handle(AbstractContext requestContext) throws Exception {
        requestContext.incCount();
    }
}
