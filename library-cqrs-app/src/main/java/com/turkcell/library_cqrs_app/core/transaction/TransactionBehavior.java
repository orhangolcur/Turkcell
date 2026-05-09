package com.turkcell.library_cqrs_app.core.transaction;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import com.turkcell.library_cqrs_app.core.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs_app.core.pipeline.RequestHandlerDelegate;

@Component
@Order(40)
public class TransactionBehavior implements PipelineBehavior{
    // Spring'in transaction yönetim interface'i.
    private final PlatformTransactionManager transactionManager;

    public TransactionBehavior(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    // Sadece Command olan request'lerde çalışıyor. Query'ler buradan geçmiyor.
    @Override
    public boolean supports(Object request) {
        return request instanceof Command<?>;
    }

     @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition()); // transaction başlar

        try {
            System.out.println("[TRANSACTION] Başlatıldı: " + request.getClass().getSimpleName());

            R result = next.invoke(); // Handler çalışıyor. Başarılı olursa commit.

            transactionManager.commit(status);
            System.out.println("[TRANSACTION] Commit edildi: " + request.getClass().getSimpleName());

            return result;

        } catch (Exception e) {
            transactionManager.rollback(status); // exception fırlarsa rollback yapılıyor.
            System.out.println("[TRANSACTION] Rollback yapıldı: " + request.getClass().getSimpleName()
                + " -> Hata: " + e.getMessage());
            throw e;
        }
    }

}
