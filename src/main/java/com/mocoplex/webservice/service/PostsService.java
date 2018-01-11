package com.mocoplex.webservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mocoplex.webservice.domain.posts.PostsRepository;
import com.mocoplex.webservice.domain.posts.PostsSaveRequestDto;
import com.mocoplex.webservice.domain.posts.PostsMainResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {

	private PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto dto) {
		
		return postsRepository.save(dto.toEntity()).getId();
	}
	
	/*
	 * 옵션(readOnly = true)을 주면 트랜잭션 범위는 유지하되, 
	 * 조회 기능만 남겨두어 조회 속도가 개선되기 때문에 특별히 등록/수정/삭제 기능이 없는 메소드에선 사용하시는걸 추천
	 */
	@Transactional(readOnly = true)
	public List<PostsMainResponseDto> findAllDesc() {
		
		return postsRepository.findAllDesc()
				.map(PostsMainResponseDto::new)
				.collect(Collectors.toList());
	}
	
}
