package me.tangpoo.troller.member.common;

import javax.print.DocFlavor.STRING;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.entity.RefreshToken;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster.Refresh;

public interface MemberTest {


    String ANOTHER_PREFIX = "another";
    Long TEST_USER_ID = 1L;
    Long TEST_ANOTHER_USER_ID = 2L;
    String TEST_USER_NAME = "username";
    String TEST_USER_PASSWORD = "password";
    String TEST_USER_EMAIL = "email@email.com";
    String TEST_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    String TEST_REFRESH_TOKEN_VALUE = "adfsadfdsa";
    String TEST_FAIL_TOKEN = "failToken";
    Member TEST_MEMBER = Member.builder()
        .memberId(TEST_USER_ID)
        .username(TEST_USER_NAME)
        .email(TEST_USER_EMAIL)
        .password(TEST_USER_PASSWORD)
        .build();

    Member TEST_ANOTHER_MEMBER = Member.builder()
        .memberId(TEST_ANOTHER_USER_ID)
        .username(ANOTHER_PREFIX + TEST_USER_NAME)
        .email(ANOTHER_PREFIX + TEST_USER_EMAIL)
        .password(ANOTHER_PREFIX + TEST_USER_PASSWORD)
        .build();

    RefreshToken TEST_REFRESH_TOKEN = RefreshToken.builder()
        .refreshToken(TEST_REFRESH_TOKEN_VALUE)
        .member(TEST_MEMBER)
        .build();
}