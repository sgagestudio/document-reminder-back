package com.sgagestudio.demo.document_reminder.repository;

import com.sgagestudio.demo.document_reminder.data.dto.response.ClientCellDataResponse;
import com.sgagestudio.demo.document_reminder.data.entity.ClientEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

    @Query("""
        select new com.sgagestudio.demo.document_reminder.data.dto.response.ClientCellDataResponse(
        c.id, c.name, c.email, c.phone, c.organizationName, count(d.id) )
        from ClientEntity c joint DataRequestEntity d on c.id = d.clientId
        where c.organizationName = :orgName
    """)
    Slice<ClientCellDataResponse> findAllByOrganizationName(String orgName, Pageable pageable);



    /*@Query("select " +
            "new com.sgagestudio.demo.document_reminder.data.dto.GetClientCellDataResponse(" +
            "c.name," +
            "c.email," +
            "c.phone," +
            "c.organizationName," +
            "count(t.id) )" +
            "from Cliente c joint Template t on c.id = t.clientId" +
            "where c.organizationName = ?1" +
            "order by c.id")
    List<GetClientCellDataResponse> findAllByOrganizationName(String organizationName, Pageable pageable);*/


}
