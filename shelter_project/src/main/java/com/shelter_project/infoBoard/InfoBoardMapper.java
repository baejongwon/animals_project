package com.shelter_project.infoBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Mapper
public interface InfoBoardMapper {

	void insertBoard(InfoBoardDTO board);

	ArrayList<InfoBoardDTO> getInfoBoard(Map<String, Integer> pagingParams);


}
