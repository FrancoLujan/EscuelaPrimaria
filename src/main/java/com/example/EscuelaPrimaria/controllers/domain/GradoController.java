package com.example.EscuelaPrimaria.controllers.domain;

import com.example.EscuelaPrimaria.dtos.salida.GradoDtoS;
import com.example.EscuelaPrimaria.services.implementations.domain.GradoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Grado")
@AllArgsConstructor
@PreAuthorize("denyAll()")
public class GradoController {
   private final GradoServiceImpl gradoService;


   @GetMapping("/todos")
   public ResponseEntity<List<GradoDtoS>> todos(){
       try{
           List<GradoDtoS> grado = gradoService.todos();
           return new ResponseEntity<>(grado, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }


}
