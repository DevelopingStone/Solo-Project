package com.store.reservation.admin.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String storeName;

    @NotBlank
    private String location;

    @NotBlank
    private String description;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY로 변경하여 지연 로딩 설정
    @JoinColumn(name = "partner_id")
    private PartnerEntity partnerEntity;

}
