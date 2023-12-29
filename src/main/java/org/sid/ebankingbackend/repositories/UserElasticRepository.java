package org.sid.ebankingbackend.repositories;

import org.sid.ebankingbackend.entities.User;
import org.sid.ebankingbackend.entities.UserElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserElasticRepository extends ElasticsearchRepository<UserElastic, Long> {
}
