package com;



import com.dao.CommonRepository;
import com.dto.request.CommonRequestVO;
import com.dto.response.GenericResponse;
import com.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Common")
public  class CommonController {

@Autowired
private CommonRepository repository;

@Autowired
private CommonService commonService;

    @GetMapping("/home")
    public String home()
    {

        return "home";
    }








}
