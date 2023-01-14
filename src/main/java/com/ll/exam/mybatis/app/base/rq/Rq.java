package com.ll.exam.mybatis.app.base.rq;

import com.ll.exam.mybatis.app.member.dto.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final HttpSession session;
    @Getter
    private String alertMsg;

    public String getCurrentUrl() {
        String url = request.getRequestURL().toString();
        String queryString = request.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            url += "?" + queryString;
        }

        return url;
    }

    public boolean isLogined() {
        return getLoginedMemberId() > 0;
    }

    public boolean isLogout() {
        return isLogined() == false;
    }

    public long getLoginedMemberId() {
        Long loginedMemberId = (Long) session.getAttribute("loginedMemberId");

        if (loginedMemberId == null) return 0;

        return loginedMemberId;
    }

    public String getLoginedMemberUsername() {
        return (String) session.getAttribute("loginedMemberUsername");
    }

    public String getLoginedMemberName() {
        return (String) session.getAttribute("loginedMemberName");
    }

    public String getLoginedMemberEmail() {
        return (String) session.getAttribute("loginedMemberEmail");
    }

    public Member getLoginedMember() {
        long id = getLoginedMemberId();
        String username = getLoginedMemberUsername();
        String name = getLoginedMemberName();
        String email = getLoginedMemberEmail();

        return Member
                .builder()
                .id(id)
                .username(username)
                .name(name)
                .email(email)
                .build();
    }

    public void setLoginDone(Member member) {
        session.setAttribute("loginedMemberId", member.getId());
        session.setAttribute("loginedMemberUsername", member.getUsername());
        session.setAttribute("loginedMemberName", member.getName());
        session.setAttribute("loginedMemberEmail", member.getEmail());
    }

    public void setLogoutDone() {
        session.removeAttribute("loginedMemberId");
        session.removeAttribute("loginedMemberUsername");
        session.removeAttribute("loginedMemberName");
        session.removeAttribute("loginedMemberEmail");
    }

    public String historyBackTemplate(String msg) {
        alertMsg = msg;

        return "common/js";
    }
}

/**
 * rq 를 리퀘스트 스코프로 변경, 해당 객체는 하나의 요청 안에서 인터셉터, 컨트롤러, 템플릿끼리 공유할 데이터를 담을 때 좋다.
 *
 * 세션에 회원번호 뿐 아니라 나머지 정보도 저장, rq를 활용하여 그것을 다양한 곳에서 접근, rq 를 이용해서 소스코드 개선
 */