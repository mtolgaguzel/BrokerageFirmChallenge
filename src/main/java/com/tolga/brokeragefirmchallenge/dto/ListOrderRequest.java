package com.tolga.brokeragefirmchallenge.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListOrderRequest implements Serializable {
	
	private Long customerId;
	private Date date1;
	private Date date2;
}
