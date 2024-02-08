package com.dividend.entity;


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
public class CompanyEntity {

    @Id
    @Column(unique = true)
    private Long id;

    private String name;

    @Column(unique = true)
    private String ticker;

}
