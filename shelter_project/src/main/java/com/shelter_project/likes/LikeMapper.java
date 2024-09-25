package com.shelter_project.likes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

	void likeActive(LikeDTO likeDTO);

	void likeInActive(LikeDTO likeDTO);

	List<LikeDTO> LikeBoards(String sessionID, String post_type, int start, int limit);

}
