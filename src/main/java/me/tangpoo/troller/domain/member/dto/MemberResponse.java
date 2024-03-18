package me.tangpoo.troller.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {
    private String userId;
    private String username;

    public MemberResponse(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
