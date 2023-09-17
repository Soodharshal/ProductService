package dev.harshal.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {

//    @Id
//    @GeneratedValue(generator = "uuidgenerator")
//    @GenericGenerator(name = "uuidgenerator",strategy = "uuid4")
//    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
@Id
@GeneratedValue(generator = "UUID")
@GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
)
@Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")

private UUID uuid;
}
