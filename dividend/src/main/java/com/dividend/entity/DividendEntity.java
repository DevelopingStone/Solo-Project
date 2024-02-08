package com.dividend.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DividendEntity {

    @Id
    @Column(unique = true)
    private Long id;

    //    @OneToMany
    private Long companyId;

    private LocalDateTime date;

    private String dividend;


}
