package com.store.reservation.admin.dto;

import com.store.reservation.admin.entity.PartnerEntity;
import com.store.reservation.admin.entity.StoreEntity;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @Column(unique = true)
    private String storeName;

    @NotBlank
    private String location;

    @NotBlank
    private String description;

    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private String phoneNumber;

    public StoreEntity toEntity(StoreDto dto, String phoneNumber) {
        return StoreEntity.builder().
                storeName(dto.getStoreName()).
                location(dto.getLocation()).
                description(dto.getDescription()).
                phoneNumber(phoneNumber).
                build();
    }
}
