package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    //id는 저장하면 발급이 되는 것임 (데이터베이스에서 얻어오는 것과 같은 여기서는 메모리 사용)
    private Long id;
    private String username;
    private int age;

    public Member() {
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
