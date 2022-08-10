package com.usuario.service.feignclients;

import com.usuario.service.modelos.Auto;


import org.springframework.web.bind.annotation.*;


//@FeignClient(name="auto-service",url="http://localhost:8002")
//@RequestMapping("/auto")
public interface AutoFeignClient {

    @PostMapping
    public Auto save(@RequestBody Auto auto);

   
}
