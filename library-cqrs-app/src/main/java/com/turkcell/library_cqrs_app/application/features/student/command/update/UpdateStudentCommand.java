package com.turkcell.library_cqrs_app.application.features.student.command.update;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record UpdateStudentCommand(
    UUID id,

    @NotBlank(message = "Ad boş olamaz")
    @Size(max = 100, message = "Ad en fazla 100 karakter olabilir")
    String firstName,

    @NotBlank(message = "Soyad boş olamaz")
    @Size(max = 100, message = "Soyad en fazla 100 karakter olabilir")
    String lastName,

    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli bir email adresi giriniz")
    @Size(max = 150, message = "Email en fazla 150 karakter olabilir")
    String email,

    @Size(max = 20, message = "Telefon en fazla 20 karakter olabilir")
    String phone
) implements Command<UpdateStudentResponse> { }