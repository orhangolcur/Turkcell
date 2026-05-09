package com.turkcell.library_cqrs_app.core.performance;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.core.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs_app.core.pipeline.RequestHandlerDelegate;

@Component
@Order(10)
public class PerformanceMonitoringBehavior implements PipelineBehavior{

    private static final long WARNING_THRESHOLD_MS = 3000; // eşik değeri

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        long startTime = System.currentTimeMillis();

        R result = next.invoke();

        long elapsed = System.currentTimeMillis() - startTime;

        if (elapsed > WARNING_THRESHOLD_MS) {
            System.out.println("[UYARI] " + request.getClass().getSimpleName()
                + " isteği " + elapsed + "ms sürdü! (Eşik: " + WARNING_THRESHOLD_MS + "ms)");
        }

        return result;
    }

}
