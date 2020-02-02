package com.ts.codemetrics.controller.v1;


import com.ts.codemetrics.model.v1.ReleaseItemModel;
import com.ts.codemetrics.service.v1.ReleaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("v1/release-items")
public class ReleaseItemController {

@Autowired
@Qualifier("ReleaseServiceV1")
private ReleaseItemService releaseItemService;

@PostMapping
public ResponseEntity<ReleaseItemModel> create(@RequestBody ReleaseItemModel releaseItemModel){
     Optional<ReleaseItemModel> releaseItemModelResult = this.releaseItemService.create(releaseItemModel);
     if(releaseItemModelResult.isPresent()){
         return new ResponseEntity<>(releaseItemModelResult.get(), HttpStatus.CREATED);
     }
     else{
       return  ResponseEntity.ok(releaseItemModel);
     }
}

    @PutMapping("/{id}")
    public ResponseEntity<ReleaseItemModel> update(@PathVariable("id")Long id, @RequestBody ReleaseItemModel releaseItemModel){
        Optional<ReleaseItemModel> releaseItemModelResult = this.releaseItemService.update(id, releaseItemModel);
        if(releaseItemModelResult.isPresent()){
            return ResponseEntity.ok(releaseItemModelResult.get());
        }
        else{
            return  ResponseEntity.ok(releaseItemModel);
        }
    }

}
