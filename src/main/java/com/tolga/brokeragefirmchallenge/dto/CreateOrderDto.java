package com.tolga.brokeragefirmchallenge.dto;

import com.tolga.brokeragefirmchallenge.constants.SideEnum;
import com.tolga.brokeragefirmchallenge.constants.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderDto implements Serializable {
	
	private Long customerId;
	private String assetName;
	
	@Enumerated(EnumType.STRING)
	private SideEnum orderSide;
	
	private BigDecimal size;
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private Status status;
}
