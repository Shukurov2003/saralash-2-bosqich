package com.example.university.Repository;

import com.example.university.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends JpaRepository<Journal,Long> {

    Journal findJournalByGroupId(Long id);
}
