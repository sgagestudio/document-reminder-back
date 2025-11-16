package com.sgagestudio.demo.document_reminder.repository;

import com.sgagestudio.demo.document_reminder.data.dto.response.TemplateCellDataResponse;
import com.sgagestudio.demo.document_reminder.data.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {

    @Query("""
            select new com.sgagestudio.demo.document_reminder.data.dto.response.TemplateCellDataResponse(
                t.id, t.name, t.subject. t.body, t.userId
            ) from TemplateEntity t where t.userId = :userId and t.type = :type
            """)
    List<TemplateCellDataResponse> findAllByUserIdAndType(UUID userId, String type);

}
