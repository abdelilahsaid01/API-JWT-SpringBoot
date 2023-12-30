package org.sid.ebankingbackend.repositories;

import org.sid.ebankingbackend.entities.BankDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankDetailsRepository extends MongoRepository<BankDetails, String> {

}
