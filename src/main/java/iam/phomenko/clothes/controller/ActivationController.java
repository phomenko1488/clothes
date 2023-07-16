package iam.phomenko.clothes.controller;

import iam.phomenko.clothes.consts.Mails;
import iam.phomenko.clothes.dto.pojo.ErrorDTO;
import iam.phomenko.clothes.dto.pojo.SuccessDTO;
import iam.phomenko.clothes.exception.LinkNotFoundException;
import iam.phomenko.clothes.exception.UserAlreadyActivatedException;
import iam.phomenko.clothes.service.ActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activation")
public class ActivationController {
    private final ActivationService activationService;

    @Autowired
    public ActivationController(ActivationService activationService) {
        this.activationService = activationService;
    }

    @GetMapping
    public ResponseEntity<Object> getActivation(@RequestParam("link") String activationLink) {
        try {
            return ResponseEntity.ok().body(new SuccessDTO(activationService.activate(activationLink)));
        } catch (LinkNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO("Link not found"));
        } catch (UserAlreadyActivatedException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO("User already activated"));
        }
    }
}
