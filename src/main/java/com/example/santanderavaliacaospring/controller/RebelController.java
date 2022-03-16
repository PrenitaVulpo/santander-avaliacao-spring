package com.example.santanderavaliacaospring.controller;

import com.example.santanderavaliacaospring.DTO.*;
import com.example.santanderavaliacaospring.SantanderAvaliacaoSpringApplication;
import com.example.santanderavaliacaospring.models.Rebel;
import com.example.santanderavaliacaospring.service.RebelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rebel")
public class RebelController {

    @Autowired
    private RebelService rebelService;

    @GetMapping
    public List<ResponseRebel> rebels(){
        return ResponseRebel.toResponse(rebelService.getAllRebels());
    }

    @GetMapping("/reports/traitors")
    public ResponsePercentage getTraitorPercentage() throws Exception {
        return rebelService.getTraitorPercentage();
    }

    @GetMapping("/reports/members")
    public ResponsePercentage getNonTraitorPercentage() {
        return rebelService.getNonTraitorPercentage();
    }

    @GetMapping("/reports/lost-points")
    public ResponseLostPoints getLostPoints() throws Exception {
        return rebelService.getLostPoints();
    }

    @GetMapping("/reports/items-report")
    public ResponseItemsReport getItemsReport() throws Exception {
        return rebelService.getItemsReport();
    }

    @PostMapping
    public ResponseEntity<ResponseRebel> signNewRebel(
            @RequestBody @Valid RequestRebel requestRebel,
            UriComponentsBuilder uriComponentsBuilder
    ){
        Rebel rebel = rebelService.addRebel(requestRebel);
        URI uri = uriComponentsBuilder.path("/rebel/id").buildAndExpand(rebel.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseRebel(rebel));
    }

    @PostMapping("/{id}/report")
    public ResponseEntity<ResponseRebel> reportRebel(
            @PathVariable UUID id,
            @RequestBody RequestReport requestReport
    ) throws Exception {
        Rebel rebel = rebelService.reportRebel(id, requestReport);
        return ResponseEntity.ok(new ResponseRebel(rebel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRebel> rebelDetails(@PathVariable UUID id)
            throws Exception{
        return ResponseEntity.ok(new ResponseRebel(rebelService.rebelDetails(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseRebel> updateRebel(
            @PathVariable UUID id,
            @RequestBody RequestRebel requestRebel
    ) throws Exception {
        Rebel rebel = SantanderAvaliacaoSpringApplication.resistance.updateRebelData(id, requestRebel);
        return ResponseEntity.ok(new ResponseRebel(rebel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRebel(@PathVariable UUID id) throws Exception {
        SantanderAvaliacaoSpringApplication.resistance.deleteRebel(id);
        return ResponseEntity.ok().build();
    }
}

