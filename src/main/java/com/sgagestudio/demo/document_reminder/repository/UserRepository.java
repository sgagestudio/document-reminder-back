package com.sgagestudio.demo.document_reminder.repository;

import com.sgagestudio.demo.document_reminder.data.dto.response.EmployeeCellDataResponse;
import com.sgagestudio.demo.document_reminder.data.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Slice<EmployeeCellDataResponse> findAllByOrganizationName(String s, Pageable page);
}
