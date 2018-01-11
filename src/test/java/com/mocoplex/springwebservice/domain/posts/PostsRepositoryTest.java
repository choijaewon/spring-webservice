package com.mocoplex.springwebservice.domain.posts;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mocoplex.webservice.domain.posts.Posts;
import com.mocoplex.webservice.domain.posts.PostsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
	
	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		/**
		 이후 테스트 코드에 영향을 끼치지 않기 위해 
		 테스트 메소드가 끝날때 마다 repository 전체 비우는 코
		 **/
		postsRepository.deleteAll();		
	}
	
	@Test
	public void 게시글저장_불러오기() {
		
		// given
		postsRepository.save(Posts.builder()
				.title("TEST Title")
				.content("Test Content")
				.author("test@test.com")
				.build());
		
		// when
		List<Posts> postsList = postsRepository.findAll();
		
		// then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle(), is("TEST Title"));
		assertThat(posts.getContent(), is("Test Content"));
		
		/**
		 * given
			테스트 기반 환경을 구축하는 단계
			여기선
			@builder의 사용법도 같이 확인
		   when
			테스트 하고자 하는 행위 선언
			여기선 Posts가 DB에 insert 되는것을 확인하기 위함
		   then
			테스트 결과 검증
			실제로 DB에 insert 되었는지 확인하기 위해 조회후, 입력된 값 확인
		 */
	}
	
	
	@Test
	public void BaseTimeEntity_등록() {
		
		// given
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(Posts.builder()
				.title("Test Title")
				.content("Test Content")
				.author("TEst author")
				.build());
		
		// when
		List<Posts> postsList = postsRepository.findAll();
		
		// then
		Posts posts = postsList.get(0);
		assertTrue(posts.getCreatedDate().isAfter(now));
		assertTrue(posts.getModifiedDate().isAfter(now));
		
	}
	
}
