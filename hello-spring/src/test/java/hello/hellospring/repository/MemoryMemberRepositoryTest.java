package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// Test는 순서대로 되지 않음, 랜덤
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  // Optional에서 꺼낼 때 get(), but 좋은 방법은 아님
        // System.out.println("result = " + (result == member));  // soutv
        // Assertions.assertEquals(member, result);  // 매번 sysout으로 확인할 수 없으니 Assert 기능을 사용
        assertThat(member).isEqualTo(result); // 요즘에는 이걸 많이 씀, Alt+Enter로 import해서 간단하게 사용 가능
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  // Shift+F6으로 일괄 변경
        member2.setName("spring2");
        repository.save(member2);

        // Optional<Member> result = repository.findByName("spring1");  // 아래와 동일
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
