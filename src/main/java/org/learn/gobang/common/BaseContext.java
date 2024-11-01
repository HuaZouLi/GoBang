package org.learn.gobang.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.learn.gobang.pojo.User;

public class BaseContext {
    @Getter
    @Setter
    private static User user;
}
