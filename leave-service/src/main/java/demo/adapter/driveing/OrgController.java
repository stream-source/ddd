package demo.adapter.driveing;

import demo.application.OrgCreateDTO;
import demo.application.OrgDTO;
import demo.application.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrgController {

    @Autowired
    OrgService orgService;

    public OrgDTO addOrg(@RequestBody OrgCreateDTO orgDTO) {
        return orgService.addOrg(orgDTO);
    }


}
