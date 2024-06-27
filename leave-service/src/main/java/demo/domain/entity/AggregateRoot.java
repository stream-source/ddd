package demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 要在聚合根的代码和数据表里增加一个版本（version）字段，类型可以是长整型。由于多数聚合都要考虑加锁，所以我们为聚合根写一个父类
 */
//@AllArgsConstructor
@NoArgsConstructor
public  class AggregateRoot extends AuditTableEntity {
    protected Long version;
    public AggregateRoot(LocalDateTime createdAt, Long createdBy) {
        super(createdBy, createdAt);
    }
    public Long getVersion() {
        return version;
    }
}
