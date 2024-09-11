package com.shelter_project.center;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface CenterMapper {

	void insertData(CenterDTO centerDTO);

	CenterDTO checkData(int animal_no);

	void updateData(CenterDTO centerDTO);

	List<String> getImg(int animal_no);

	CenterImgDTO checkImgData(int animal_no, int photoNo);

	void insertImgData(CenterImgDTO centerImgDTO);

	void updateImgData(CenterImgDTO centerImgDTO);

	ArrayList<CenterDTO> PagingList(Map<String, Object> pagingParams);
	
	int boardCount(@Param("type") String type);


	List<CenterDTO> centerSearch(Map<String, Object> pagingParams);

	int getSearchCount(String searchColumn, String keyword);

	

	

	

}
