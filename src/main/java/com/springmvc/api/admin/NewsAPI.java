package com.springmvc.api.admin;

import com.springmvc.dto.NewsDTO;
import com.springmvc.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController(value = "NewAPIOfAdmin")
public class NewsAPI {

    @Autowired
    private INewService newService;

    @PostMapping(value = "/api/new")
    public NewsDTO createNew(@RequestBody NewsDTO createDTO, @RequestParam(value = "thumbnail",required = false) MultipartFile thumbnail){
        return newService.save(createDTO);
    }


    @PutMapping(value = "/api/new")
    public NewsDTO updateNew(@RequestBody NewsDTO updateDTO){
        return newService.save(updateDTO);
    }

    //@DeleteMapping(value = "/api/new")
    @RequestMapping(value = "/api/new", method = RequestMethod.DELETE)
    public void deleteNew(@RequestBody long[] ids){
        newService.delete(ids);
    }


}
