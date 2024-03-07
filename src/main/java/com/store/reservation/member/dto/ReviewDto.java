package com.store.reservation.member.dto;

import com.store.reservation.admin.entity.StoreEntity;
import com.store.reservation.member.entity.ReviewEntity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewDto {

    @Min(value = 1, message = "별점은 최소 1 이상이어야 합니다.")
    private int starRate;

    @NotBlank
    private String memo;
    
    private StoreEntity store; // 매장과의 관계

    public ReviewEntity toEntity(ReviewDto reviewDto) {
        return ReviewEntity.builder().
                starRate(reviewDto.getStarRate()).
                memo(reviewDto.getMemo()).
                store(reviewDto.getStore()).
                build();
    }


}
