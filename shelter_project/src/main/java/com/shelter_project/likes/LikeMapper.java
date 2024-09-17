package com.shelter_project.likes;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

	void likeActive(LikeDTO likeDTO);

	void likeInActive(LikeDTO likeDTO);


}
