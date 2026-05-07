package com.turkcell.library_cqrs_app.persistence.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.turkcell.library_cqrs_app.domain.entity.BookCopy;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID> {
    boolean existsByBarcode(String barcode);
    Optional<BookCopy> findByBookId(UUID bookId);

}
