package org.samydevup.mysqlcruddockerdemo.payload;

import lombok.Data;

@Data
public class ObjetDto {

	private Long id;
	private String name;
	private String description;
	private String dateAchat;

}
