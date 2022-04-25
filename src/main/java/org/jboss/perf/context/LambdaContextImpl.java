package org.jboss.perf.context;

import org.jboss.perf.handler.*;

import java.lang.invoke.*;
import java.util.function.Consumer;

public class LambdaContextImpl extends AbstractContext{

    private final Invoker<RestHandler, AbstractContext>[] handlerConsumers;

    public LambdaContextImpl(RestHandler[] handlerChain) {
        super(handlerChain);
        handlerConsumers = new Invoker[this.handlerChain.length];

        for (int pos = 0; pos < this.handlerChain.length; pos++) {
            try {
                Invoker<RestHandler, AbstractContext> restInvoker = getInvoker(this.handlerChain[pos].getClass(), "handle", AbstractContext.class);
                handlerConsumers[pos] = restInvoker;
            } catch (Throwable e) {
                System.out.println("Exception occurred. can not create lambda, will fall back to slow path for this handler type!");

                handlerConsumers[pos] = null;
            }
        }
    }

    @Override
    public void handleSomething() throws Exception {
        for( int chainPos = 0 ; chainPos < handlerChain.length ; chainPos++ ){
            if (handlerConsumers[chainPos] != null) {
                handlerConsumers[chainPos].invoke(handlerChain[chainPos], this);
            } else { //fallback to slow path
                handlerChain[chainPos].handle(this);
            }
        }

    }


    @FunctionalInterface
    public interface Invoker<T, R> {
        void invoke(T object, R value);
    }

    public static <T extends RestHandler, R> Invoker<T, R> getInvoker(Class<? extends RestHandler> clazz, String fieldName,
                                                                      Class<R> fieldType) throws Throwable {

        MethodHandles.Lookup caller = MethodHandles.lookup();
        MethodType setter = MethodType.methodType(void.class, fieldType);
        MethodHandle target = caller.findVirtual(clazz, fieldName, setter);
        MethodType func = target.type();

        CallSite site = LambdaMetafactory.metafactory(
                caller,
                "invoke",
                MethodType.methodType(Invoker.class),
                func.erase(),
                target,
                func
        );

        MethodHandle factory = site.getTarget();
        Invoker<T, R> r = (Invoker<T, R>) factory.invokeExact();

        return r;
    }

}
