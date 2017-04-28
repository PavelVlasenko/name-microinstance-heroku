package com.heroku.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stanford-ner/ner")
public class HomeController {


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String home() {
        return "get okkk";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertData2(@RequestParam String input, @RequestParam String outputFormat,
                             @RequestParam String preserveSpacing) {

        String result =  Application.asc.classifyToString(input, outputFormat,
                !"slashTags".equals(outputFormat));
        return result;
    }
}
