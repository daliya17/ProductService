package dev.daliya.productService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseModel{
    private String name;

    @OneToMany
    private List<Product> products;
}
