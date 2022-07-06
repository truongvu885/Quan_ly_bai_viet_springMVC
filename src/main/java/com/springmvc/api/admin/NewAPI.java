package com.springmvc.api.admin;

import com.springmvc.dto.NewDTO;
import org.springframework.web.bind.annotation.*;

@RestController(value = "NewAPIOfAdmin")
public class NewAPI {

    @PostMapping(value = "/api/new")
    public NewDTO createNew(@RequestBody NewDTO newDTO){
        return newDTO;
    }

    @PutMapping(value = "/api/new")
    public NewDTO updateNew(@RequestBody NewDTO newDTO){

        return newDTO;
    }

    @DeleteMapping(value = "/api/new")
    public void deleteNew(@RequestBody long[] ids){

    }


}
