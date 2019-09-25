package idwall.desafio.string.controller;

import idwall.desafio.string.domain.TextDTO;
import idwall.desafio.string.service.StringService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strings")
public class StringController {

    private final StringService stringService;

    public StringController(StringService stringService) {
        this.stringService = stringService;
    }

    @PostMapping(path = "/formatter", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> format(@RequestBody TextDTO dto, @RequestParam(name = "width") int width,
                                         @RequestParam(name = "justify", required = false) boolean justify) {

        return ResponseEntity.ok(stringService.textFormat(dto.getText(), width, justify));
    }
}
