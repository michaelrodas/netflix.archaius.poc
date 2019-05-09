package com.ntflxarch.pocna.controller;

import com.ntflxarch.pocna.bean.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/configprops")
public class ConfigPropertiesController {

    @Autowired
    private ApplicationConfig applicationConfig;

    @RequestMapping(path = {"/{pname}/{pdval}", "/{pname}"}, method = RequestMethod.GET)
    public ResponseEntity<String> getPropertyValue(@PathVariable String pname,
                                                   @PathVariable(name = "pdval", required = false) Optional<String> pdval) {
         String result = applicationConfig.getStringProperty(pname, pdval.orElse("Property not found."));
        if(result.equals("Property not found.")){
            return new ResponseEntity<>(pname.concat(": ").concat(result), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}