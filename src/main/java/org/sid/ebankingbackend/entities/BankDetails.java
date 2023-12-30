package org.sid.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ebankingbackend.enums.AccountStatus;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Document(collection = "bankDetails")
@Data @NoArgsConstructor @AllArgsConstructor
public class BankDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    private String id;
    private String bankName;
    private String location;
}
