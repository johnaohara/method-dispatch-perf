package org.jboss.perf.context;

import org.jboss.perf.handler.RestHandler;

import java.lang.invoke.*;

public class MethodHandleImpl extends AbstractContext{

    private final MethodHandle[] handlerConsumers;

    public MethodHandleImpl(RestHandler[] handlerChain) {
        super(handlerChain);
        handlerConsumers = new MethodHandle[this.handlerChain.length];

        for (int pos = 0; pos < this.handlerChain.length; pos++) {
            try {
                MethodHandle methodHandle = getMethodHandle(this.handlerChain[pos].getClass(), "handle", AbstractContext.class);
                handlerConsumers[pos] = methodHandle;
            } catch (Throwable e) {
                System.out.println("Exception occurred. can not create method handle, will fall back to slow path for this handler type!");

                handlerConsumers[pos] = null;
            }
        }
    }

    @Override
    public void handleSomething() throws Throwable {
        for( int chainPos = 0 ; chainPos < handlerChain.length ; chainPos++ ){
            if (handlerConsumers[chainPos] != null) {
                handlerConsumers[chainPos].invokeExact((RestHandler) handlerChain[chainPos], (AbstractContext) this);
            } else { //fallback to slow path
                handlerChain[chainPos].handle(this);
            }
        }

    }

    public static MethodHandle getMethodHandle(Class<? extends RestHandler> clazz, String fieldName,
                                                                      Class<?> fieldType) throws Throwable {

        MethodHandles.Lookup publicLookup = MethodHandles.lookup();
        MethodType mt = MethodType.methodType(void.class, fieldType);
//        MethodType mt = MethodType.methodType(void.class);
        MethodHandle lookupMH = publicLookup.findVirtual(RestHandler.class, fieldName, mt);

        return lookupMH;

    }

}
