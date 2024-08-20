package com.shelter_project.infoBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Mapper
public interface InfoBoardMapper {

	void insertBoard(InfoBoardDTO board);

	ArrayList<InfoBoardDTO> getInfoBoards(Map<String, Integer> pagingParams);

	InfoBoardDTO getContent(@Param("postNo") int postNo);

	int ViewCount(@Param("postNo") int postNo);

	void deleteBoard(@Param("postNo") int postNo);

	void updateBoard(InfoBoardDTO board);

	int boardCount();

}
