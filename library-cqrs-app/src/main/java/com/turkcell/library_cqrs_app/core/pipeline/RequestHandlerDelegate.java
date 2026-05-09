package com.turkcell.library_cqrs_app.core.pipeline;

@FunctionalInterface
public interface RequestHandlerDelegate<R> {
    R invoke();
}

// Amacı şu: "Bir sonraki adımı çağır" demek. Yani bir işlemi temsil ediyor ama o işlemi hemen çalıştırmıyor, sadece sarıyor. İstediğiniz zaman invoke() diyerek çalıştırıyorsunuz.