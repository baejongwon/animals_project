package com.shelter_project.infoBoard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Mapper
public interface CommentMapper {

	void addComment(CommentDTO comment);

	List<CommentDTO> getComments(int postNo);

	int getCount(int no, String type);

	int getMaxOrderNumber(int no, String type);

	int getOrderNumber(int parentNo);

	void deleteComment(int commentNo);

	void updateComment(int commentNo, String content);

	List<CommentDTO> getPerComments(int animal_no);

	void addPerComment(CommentDTO comment);

	int getPerOrderNumber(int parentNo);

	int getPerMaxOrderNumber(Integer animal_no);

}
