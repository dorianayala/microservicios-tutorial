package com.usuario.service.feignclients;

import com.usuario.service.modelos.Auto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;


import org.springframework.web.bind.annotation.*;


@FeignClient(name="auto-service",url="http://localhost:8002", path="/auto")
public interface AutoFeignClient {

    @PostMapping
    public Auto save(@RequestBody Auto auto);

   @GetMapping("/usuario/{usuarioId}")
    public List<Auto> getAutos(@PathVariable("usuarioId") Long usuarioId);
}
