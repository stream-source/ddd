package demo.adapter.driveing;

import demo.application.CreateEmpRequest;
import demo.application.EmpService;
import demo.application.UpdateEmpRequest;
import demo.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {

    @Autowired
    EmpService empService;

    public void addEmp(CreateEmpRequest request) {
        empService.addEmp(request, new User());
    }

    public void updateEmp(Long empId, UpdateEmpRequest request) {
        empService.updateEmp(empId, request, new User());
    }


}
