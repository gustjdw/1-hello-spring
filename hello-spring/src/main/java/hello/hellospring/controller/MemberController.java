package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private MemberService memberService;
    // 생성자를 통한 DI, 권장됨
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    // 필드를 통한 DI, 여기선 별로 권장되지 않음
//    @Autowired private MemberService memberService;
//
//    // Setter를 통한 DI, public으로 열려있어 중간(한 번 세팅된 후)에 바뀔 위험이 있음
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//        memberService.setMemberRepository(); <- 아무한테나 열려있게 됨
//    }
    @GetMapping("members/new")
    public String createFrom() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
