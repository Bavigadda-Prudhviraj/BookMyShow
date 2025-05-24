package org.prudhviraj.bookmyshow.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
/**
  This annotation marks the class as a base class for JPA entities.

  ✅ What it does:
  - This class **will not be mapped to a database table** by itself.
  - All fields in this class are **inherited and mapped** into the database table of any subclass (entity) that extends it.

  📌 Why use it:
  - Common for defining shared attributes like `id`, `createdOn`, `updatedOn`, etc.
  - Helps reduce boilerplate and ensures consistent audit fields across all models.

 ❗Important:
  - Unlike @Entity, this class won't exist as a table in the DB.
  - It must be extended by an @Entity class to take effect.
 */
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private String createdBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdOn;

    @Column(insertable = false)
    private String updatedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private Date updatedOn;
}
