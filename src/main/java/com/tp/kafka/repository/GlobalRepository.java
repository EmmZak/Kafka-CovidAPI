/*
 * ===================================================================
 * TP-Kafka
 * Authors: Delphin Rukundo
 *        & Emmanuel Zakaryan
 *        & Elyes Boufdil
 *        & Lucas Lernoud
 * ===================================================================
 */

package com.tp.kafka.repository;

import com.tp.kafka.model.Global;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalRepository extends JpaRepository<Global, Long> {
	
    @Query("SELECT g.TotalDeaths FROM Global g")
    int getTotalDeaths();
    
    @Query("SELECT g.TotalConfirmed FROM Global g")
    int getTotalConfirmed();

    // there is only one entry in the table, with id=1
    @Query("SELECT g FROM Global g WHERE g.id = 1")
    Global findById();
}
