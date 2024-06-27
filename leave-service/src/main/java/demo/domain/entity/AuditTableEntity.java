package demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static demo.domain.entity.ChangingStatus.*;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class AuditTableEntity {

    protected Long createdBy;

    protected LocalDateTime createdAt;

    protected Long lastUpdatedBy;

    protected LocalDateTime lastUpdatedAt;


    /**
     * 处理聚合中的数据是否发生改变
     */
    protected ChangingStatus changingStatus = NEW;

    public AuditTableEntity(Long createdBy, LocalDateTime createdAt) {
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }


    public void toUpdate() {
        this.changingStatus = UPDATED;
    }
    public void toDelete() {
        this.changingStatus = DELETED;
    }

    public void toUnchanged() {
        this.changingStatus = UNCHANGED;
    }
}
