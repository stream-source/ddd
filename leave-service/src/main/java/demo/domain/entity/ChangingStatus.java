package demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChangingStatus {
    NEW, // 新增
    UNCHANGED, // 不变
    UPDATED, // 更改
    DELETED // 删除
}
