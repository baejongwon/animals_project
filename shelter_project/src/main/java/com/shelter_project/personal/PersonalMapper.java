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
import com.shelter_project.infoBoard.InfoBoardDTO;
import com.shelter_project.likes.LikeDTO;

@Mapper
public interface PersonalMapper {

	int boardCount(String type);

	ArrayList<PersonalDTO> PagingList(Map<String, Object> pagingParams);

	void personalWriteProc(PersonalDTO personalDTO);

	void insertPersonalImages(PersonalImagesDTO imagesDTO);

	PersonalDTO personalContent(int animal_no);

	List<String> getImg(int no);

	void modifyPersonalImages(PersonalImagesDTO imagesDTO);

	void personalModifyProc(PersonalDTO personalDTO);

	List<Integer> getImageNo(int animal_no);

	void deleteImage(String getImg, int animal_no);

	void personalDelete(int animal_no);

	List<PersonalDTO> getMainContent();

	int getSearchCount(String searchColumn, String keyword);

	List<PersonalDTO> perSearch(Map<String, Object> pagingParams);

	Integer like_check(LikeDTO likeDTO);

	List<PersonalDTO> perBoards(String sessionID, int start, int limit);

	PersonalDTO getPerPost(int postNo);


}
