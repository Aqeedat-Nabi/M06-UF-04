package com.dataaccess.store.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subcategory")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subcategory_id;

    @Column
    private String name;

    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "created_at")
    private LocalDateTime creationDate;
    
    @Column(name = "updated_at")
    private LocalDateTime updateDate;
}
