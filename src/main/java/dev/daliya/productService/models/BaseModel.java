package dev.daliya.productService.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(generator = "UUIdGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid4")
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private Long id;
}
