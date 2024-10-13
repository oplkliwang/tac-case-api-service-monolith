package com.beaconstrategists.clientcaseapi.repositories;

import com.beaconstrategists.clientcaseapi.model.entities.RmaCaseAttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RmaCaseAttachmentRepository extends JpaRepository<RmaCaseAttachmentEntity, Long> {
    Optional<RmaCaseAttachmentEntity> findByIdAndRmaCaseId(Long id, Long tacCaseId);
    List<RmaCaseAttachmentEntity> findAllByRmaCaseId(Long tacCaseId);
}
