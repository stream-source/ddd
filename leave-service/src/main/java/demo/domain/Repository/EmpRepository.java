package demo.domain.Repository;

import demo.domain.entity.Emp;

import java.util.Optional;

public interface EmpRepository {
    void save(Emp emp);

    Optional<Emp> findById(Long tenantId, Long empId);
}
