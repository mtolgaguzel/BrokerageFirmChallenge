package com.tolga.brokeragefirmchallenge.entity;

import com.tolga.brokeragefirmchallenge.constants.SideEnum;
import com.tolga.brokeragefirmchallenge.constants.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	private String assetName;
	
	@Enumerated(EnumType.STRING)
	private SideEnum orderSide;
	
	private BigDecimal size;
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Date createDate;
}
