package demo.adapter.driven;

import demo.domain.entity.Org;
import demo.domain.Repository.OrgRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrgRepositoryImpl implements OrgRepository {
    //仓库是聚合的对应持久化关系，那么，这里就需要分发分别持久化实体的操作；
    @Override
    public Org save(Org org) {
        return null;
    }
}
