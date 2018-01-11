package com.mocoplex.webservice.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.mocoplex.webservice.domain.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
/*
 * @Entity 
 * 테이블과 링크될 클래스임을 나타냅니다.
 * 언더스코어 네이밍(_)으로 이름을 매칭합니다.
 * ex) SalesManager.java -> sales_manager table
 */
public class Posts extends BaseTimeEntity {

	@Id // @Id 해당 테이블의 PK 필드를 나타냅니다.
	@GeneratedValue
	/*
	 * @GeneratedValue
	 * PK의 생성 규칙을 나타냅니다.
	 * 기본값은 AUTO 로, MySQL의 auto_increment와 같이 자동증가하는 정수형 값이 됩니다
	 */
	private Long id;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	private String author;
	
	/*
	 * @Column
	 * 테이블의 컬럼을 나타내면, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됩니다.
	 * 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있을경우 사용합니다.
	 * 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex: title), 타입을 TEXT로 변경하고 싶거나(ex: content) 등의 경우에 사용됩니다.
	 */	
	
	@Builder
	public Posts(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
	
	/*
	 * Tip) 
		웬만하면 Entity의 PK는 Long 타입의 Auto_increment를 추천합니다. 
		(MySQL 기준으로 이렇게 하면 bigint 타입이 됩니다.) 
		주민등록번호와 같은 비지니스상 유니크키나, 여러키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 종종 발생합니다. 
		(1) FK를 맺을때 다른 테이블에서 복합키 전부를 갖고 있거나, 중간 테이블을 하나더 둬야하는 상황이 발생합니다. 
		(2) 인덱스에 좋은 영향을 끼치지 못합니다. 
		(3) 유니크한 조건이 변경될 경우 PK 전체를 수정해야하는 일이 발생합니다. 
		주민등록번호, 복합키 등은 유니크키로 별도로 추가하시는것을 추천드립니다.
	 */
	
	/*
	 * Lombok 라이브러리의 어노테이션들입니다. 
		어노테이션 이름만 봐도 대략 기능을 예측할수 있습니다.
		
		@NoArgsConstructor : 기본 생성자 자동 추가
		access = AccessLevel.PROTECTED : 기본생성자의 접근 권한을 protected로 제한
		생성자로 protected Posts() {}와 같은 효과
		Entity 클래스를 프로젝트 코드상에서 기본생성자로 생성하는 것은 막되, JPA에서 Entity 클래스를 생성하는것은 허용하기 위해 추가
		@Getter : 클래스내 모든 필드의 Getter 메소드를 자동생성
		@Builder : 해당 클래스의 빌더패턴 클래스를 생성
		생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
	 */
}
