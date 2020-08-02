package com.example.demo.controler;

import com.example.demo.Validation.UrlValidation;
import com.example.demo.domain.JsonResponse.JsonResponse;
import com.example.demo.domain.JsonResponse.JsonResponseError;
import com.example.demo.util.EmailUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/data/query")
public class MostCitedControler {

    @GetMapping("/most_cited")
    public ResponseEntity getMostCited(@RequestParam ("data") Optional<String> optionalData,
                                       @RequestParam("url") Optional<String> optionalUrl,
                                       @RequestHeader("user-email") Optional<String> optionalEmail){
        if( optionalEmail.isPresent() == false || EmailUtil.validation( optionalEmail.get() ) == false ){ return ErrorEmail(); }

        if( optionalUrl.isPresent() == false || UrlValidation.urlMostCited(optionalUrl.get() ) == false ){ return ErrorUrl(); }




        return ErrorEmail();
    }

    private ResponseEntity ErrorEmail(){
        JsonResponse jsonResponse = new JsonResponse();
        JsonResponseError jsonResponseError = new JsonResponseError();
        jsonResponseError.setCode("01");
        jsonResponseError.setDescription("Email invalido no header user-email");
        jsonResponse.setJsonResponseError( jsonResponseError );
        return ResponseEntity.badRequest().body(jsonResponseError);
    }

    private ResponseEntity ErrorUrl(){
        JsonResponse jsonResponse = new JsonResponse();
        JsonResponseError jsonResponseError = new JsonResponseError();
        jsonResponseError.setCode("02");
        jsonResponseError.setDescription("Url invalido no parametro");
        jsonResponse.setJsonResponseError( jsonResponseError );
        return ResponseEntity.badRequest().body(jsonResponseError);
    }

}
