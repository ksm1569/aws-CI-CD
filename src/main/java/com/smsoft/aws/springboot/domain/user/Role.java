package com.smsoft.aws.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "�մ�"),
    USER("ROLE_USER", "�Ϲ� ����");

    private final String key;
    private final String title;
}
