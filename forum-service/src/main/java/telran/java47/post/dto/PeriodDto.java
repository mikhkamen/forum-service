                                       package telran.java47.post.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Getter;



@Getter
public class PeriodDto {
	@JsonFormat(pattern = "yyy-MM-dd")
	LocalDate dateFrom;
	@JsonFormat(pattern = "yyy-MM-dd")
	LocalDate dateTo;
}
