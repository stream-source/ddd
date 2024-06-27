package demo.application;

import demo.domain.Repository.EmpRepository;
import demo.domain.domainservice.EmpUpdator;
import demo.domain.entity.Emp;
import demo.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public  class EmpService {
    private final EmpRepository empRepository;
    private final EmpAssembler assembler;

    private final EmpUpdator updator; //用于修改Emp聚合

    @Autowired
    public EmpService(EmpRepository empRepository, EmpAssembler assembler, EmpUpdator updator) {
        this.empRepository = empRepository;
        this.assembler = assembler;
        this.updator = updator;
    }
    @Transactional
    public EmpResponse addEmp(CreateEmpRequest request, User user) {
        Emp emp = assembler.fromCreateRequest(request, user);
        empRepository.save(emp);
        return assembler.toResponse(emp);
    }

    @Transactional
    public EmpResponse updateEmp(Long empId, UpdateEmpRequest request, User user) {
        Emp emp = empRepository.findById(request.getTenantId(), empId).orElseThrow(() -> new RuntimeException("员工不存在!"));
        updator.update(emp, request, user);
        empRepository.save(emp);
        return assembler.toResponse(emp);
    }
}
