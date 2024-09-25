package com.shelter_project.likes;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class LikeService {

	@Autowired private HttpSession session;
	@Autowired LikeMapper mapper;

	public void likeActive(LikeDTO likeDTO) {
		mapper.likeActive(likeDTO);
	}

	public void likeInActive(LikeDTO likeDTO) {
		mapper.likeInActive(likeDTO);
	}

	public List<LikeDTO> LikeBoards(String sessionID, String post_type, int start, int limit) {
	    return mapper.LikeBoards(sessionID, post_type, start, limit);
	}


}
