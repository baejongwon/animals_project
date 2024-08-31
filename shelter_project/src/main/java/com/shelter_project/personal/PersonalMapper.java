package com.shelter_project.personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.shelter_project.center.CenterDTO;

@Mapper
public interface PersonalMapper {

	int boardCount();

	ArrayList<PersonalDTO> PagingList(Map<String, Integer> pagingParams);

}
