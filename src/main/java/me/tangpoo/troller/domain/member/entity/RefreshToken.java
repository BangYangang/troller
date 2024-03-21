package me.tangpoo.troller.domain.member.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@RedisHash(value = "refresh_token", timeToLive = (60 * 60 * 1000 * 24L))
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public RefreshToken(String refreshToken, Member member) {
        this.refreshToken = refreshToken;
        this.member = member;
    }

    public void updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
