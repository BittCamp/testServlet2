package com.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter <== 게터
//@Setter <== 새터
@Data ///<== 기본생성자 해쉬코드 이퀄 세터 게터 등등 다만든다.
public class DBTestDTO {
	private String name;
	private String age;
	private String height;
	private String logtime;

}
