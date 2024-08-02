package com.shelter_project.CustomerService;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoticeMapper {

	NoticeDTO checkId(String id);

	int registPro(NoticeDTO member);


}
