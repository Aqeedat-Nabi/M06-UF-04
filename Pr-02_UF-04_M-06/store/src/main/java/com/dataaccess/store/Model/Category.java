package com.dataaccess.store.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;

    @Column
    private String name;

    @Column(name = "created_at")
    private LocalDateTime creationDate;
    
    @Column(name = "updated_at")
    private LocalDateTime updateDate;
}
