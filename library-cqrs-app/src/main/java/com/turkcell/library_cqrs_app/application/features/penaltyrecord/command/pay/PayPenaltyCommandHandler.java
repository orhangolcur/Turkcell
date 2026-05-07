package com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.pay;

import com.turkcell.library_cqrs_app.application.features.penaltyrecord.rule.PenaltyRecordBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.PenaltyRecord;
import com.turkcell.library_cqrs_app.persistence.repository.PenaltyRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class PayPenaltyCommandHandler implements CommandHandler<PayPenaltyCommand, PayPenaltyResponse> {

    private final PenaltyRecordRepository penaltyRecordRepository;
    private final PenaltyRecordBusinessRules penaltyRecordBusinessRules;

    public PayPenaltyCommandHandler(
        PenaltyRecordRepository penaltyRecordRepository,                            
        PenaltyRecordBusinessRules penaltyRecordBusinessRules
    ) {
        this.penaltyRecordRepository = penaltyRecordRepository;
        this.penaltyRecordBusinessRules = penaltyRecordBusinessRules;
    }

    @Override
    public PayPenaltyResponse handle(PayPenaltyCommand command) {
        PenaltyRecord penaltyRecord = penaltyRecordBusinessRules.getByIdOrThrow(command.id());
        penaltyRecordBusinessRules.penaltyMustBeUnpaid(penaltyRecord.getPaymentStatus());

        penaltyRecord.setPaymentStatus("paid");
        penaltyRecordRepository.save(penaltyRecord);

        return new PayPenaltyResponse(
            command.id(),
            "Ceza başarıyla ödendi."
        );
    }
}
