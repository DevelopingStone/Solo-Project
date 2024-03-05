package com.store.reservation.admin.dto;

import com.store.reservation.admin.entity.PartnerEntity;
import com.store.reservation.admin.entity.StoreEntity;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StoreDto {

    @NotBlank
    private String storeName;

    @NotBlank
    private String location;

    @NotBlank
    private String description;

    private PartnerEntity partnerEntity;

    public StoreEntity toEntity(StoreDto dto) {
        return StoreEntity.builder().
                storeName(dto.getStoreName()).
                location(dto.getLocation()).
                description(dto.getDescription()).
                partnerEntity(dto.getPartnerEntity()).
                build();
    }
}
