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

	int getCount(int postNo);

	int getMaxOrderNumber(int postNo);

	int getOrderNumber(int parentNo);

	void deleteComment(int commentNo);

	void updateComment(int commentNo, String content);

}
