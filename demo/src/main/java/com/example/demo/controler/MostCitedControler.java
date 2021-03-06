package com.example.demo.controler;

import com.example.demo.Validation.DataValidation;
import com.example.demo.Validation.UrlValidation;
import com.example.demo.domain.JsonResponse.JsonResponse;
import com.example.demo.domain.JsonResponse.JsonResponseError;
import com.example.demo.service.MostCitedService;
import com.example.demo.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("/data/query")
public class MostCitedControler {

    @Autowired
    MostCitedService mostCitedService;

    @GetMapping("/most_cited_by")
    public ResponseEntity getMostCitedBy(@RequestParam ("data") Optional<CharSequence> optionalData,
                                       @RequestParam("url") Optional<CharSequence> optionalUrl){
        if( optionalUrl.isPresent() == true && UrlValidation.urlMostCited(optionalUrl.get().toString() ) == false ){ return ErrorUrl(); }

        else if( optionalData.isPresent() == true && DataValidation.mostCited( optionalData.get().toString() ) == false){return ErrorDate(); }

        return ResponseEntity.ok( mostCitedService.get( optionalUrl , optionalData ) );
    }

    @GetMapping("/most_cited_keys")
    public ResponseEntity getMostCitedKey(@RequestParam ("url") Optional<Boolean> optionalUrl , @RequestParam ("data") Optional<Boolean> optionalData ){
        if( optionalUrl.orElse(false) && optionalData.orElse(false) ) {
            return ResponseEntity.ok(mostCitedService.getAllUrlsAndData());
        }else if( optionalUrl.orElse(false) ){
            return ResponseEntity.ok(mostCitedService.getAllUrls());
        }else if( optionalData.orElse(false) ){
            return ResponseEntity.ok(mostCitedService.getAllDates());
        }
        return ResponseEntity.ok(mostCitedService.getAllUrlsAndData());
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

    private ResponseEntity ErrorDate(){
        JsonResponse jsonResponse = new JsonResponse();
        JsonResponseError jsonResponseError = new JsonResponseError();
        jsonResponseError.setCode("03");
        jsonResponseError.setDescription("Data invalido no parametro, esperamos no padrão DateTimeFormatter.ISO_LOCAL_DATE_TIME");
        jsonResponse.setJsonResponseError( jsonResponseError );
        return ResponseEntity.badRequest().body(jsonResponseError);
    }

}
