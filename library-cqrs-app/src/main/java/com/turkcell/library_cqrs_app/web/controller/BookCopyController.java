package com.turkcell.library_cqrs_app.web.controller;

import com.turkcell.library_cqrs_app.application.features.bookcopy.command.create.CreateBookCopyCommand;
import com.turkcell.library_cqrs_app.application.features.bookcopy.command.create.CreateBookCopyResponse;
import com.turkcell.library_cqrs_app.application.features.bookcopy.command.delete.DeleteBookCopyCommand;
import com.turkcell.library_cqrs_app.application.features.bookcopy.command.delete.DeleteBookCopyResponse;
import com.turkcell.library_cqrs_app.application.features.bookcopy.command.update.UpdateBookCopyCommand;
import com.turkcell.library_cqrs_app.application.features.bookcopy.command.update.UpdateBookCopyResponse;
import com.turkcell.library_cqrs_app.application.features.bookcopy.query.getall.GetAllBookCopyQuery;
import com.turkcell.library_cqrs_app.application.features.bookcopy.query.getall.GetAllBookCopyResponse;
import com.turkcell.library_cqrs_app.application.features.bookcopy.query.getbyid.GetByIdBookCopyQuery;
import com.turkcell.library_cqrs_app.application.features.bookcopy.query.getbyid.GetByIdBookCopyResponse;
import com.turkcell.library_cqrs_app.core.mediator.Mediator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book-copies")
public class BookCopyController {

    private final Mediator mediator;

    public BookCopyController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public CreateBookCopyResponse create(@RequestBody @Valid CreateBookCopyCommand command) {
        return mediator.send(command);
    }

    @PutMapping("/{id}")
    public UpdateBookCopyResponse update(@PathVariable UUID id, @RequestBody @Valid UpdateBookCopyCommand command) {
        return mediator.send(new UpdateBookCopyCommand(id, command.barcode(), command.status(), command.bookId(), command.branchId()));
    }

    @DeleteMapping("/{id}")
    public DeleteBookCopyResponse delete(@PathVariable UUID id) {
        return mediator.send(new DeleteBookCopyCommand(id));
    }

    @GetMapping
    public List<GetAllBookCopyResponse> getAll() {
        return mediator.send(new GetAllBookCopyQuery());
    }

    @GetMapping("/{id}")
    public GetByIdBookCopyResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetByIdBookCopyQuery(id));
    }
}
