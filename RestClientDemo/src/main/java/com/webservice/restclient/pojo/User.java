package com.webservice.restclient.pojo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "user")
public class User {
	private Integer userId;
	private String userName;
	private String email;
}
