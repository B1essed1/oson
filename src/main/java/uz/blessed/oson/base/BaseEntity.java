package uz.blessed.oson.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
/*
@Where(clause = "is_deleted = false")
*/
public class BaseEntity implements Serializable {
    @Transient
    private static final String GENERAL_SEQUENCE_NAME= "general_sequence_name";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERAL_SEQUENCE_NAME)
    @SequenceGenerator(name = GENERAL_SEQUENCE_NAME, sequenceName = GENERAL_SEQUENCE_NAME, allocationSize = 1)

    @Column(unique = true)
    protected Long id;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN default false")
    private Boolean isDeleted = Boolean.FALSE;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private LocalDateTime lastModifiedDate = LocalDateTime.now();
}
