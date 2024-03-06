package com.store.reservation.admin.dto;

import com.store.reservation.admin.entity.PartnerEntity;
import com.store.reservation.admin.entity.StoreEntity;
import java.util.List;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDto {

    @NotBlank
    private String partnerName;

    @Email
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    @Column(unique = true)
    private String phoneNumber;

    private List<StoreEntity> stores;

    public PartnerEntity toEntity(PartnerDto dto) {
        return PartnerEntity.builder()
                .partnerName(dto.getPartnerName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }
}
