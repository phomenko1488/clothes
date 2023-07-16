package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.consts.Mails;
import iam.phomenko.clothes.domain.users.ActivationLink;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.exception.LinkNotFoundException;
import iam.phomenko.clothes.exception.UserAlreadyActivatedException;
import iam.phomenko.clothes.repository.ActivationRepository;
import iam.phomenko.clothes.service.ActivationService;
import iam.phomenko.clothes.service.MailService;
import iam.phomenko.clothes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActivationServiceImpl implements ActivationService {
    private final ActivationRepository activationRepository;
    private final UserService userService;
    private final MailService mailService;
    private final Mails mails;

    @Autowired
    public ActivationServiceImpl(ActivationRepository activationRepository, UserService userService, MailService mailService, Mails mails) {
        this.activationRepository = activationRepository;
        this.userService = userService;
        this.mailService = mailService;
        this.mails = mails;
    }

    @Override
    public boolean activate(String activationLink) throws LinkNotFoundException, UserAlreadyActivatedException {
        ActivationLink link = activationRepository.getActivationLinkById(activationLink);
        if (link == null)
            throw new LinkNotFoundException();
        User user = link.getUser();
        if (user.isActivated())
            throw new UserAlreadyActivatedException();
        user.setActivated(true);
        userService.save(user);
        activationRepository.delete(link);
        String activated = "Activated";
        mailService.sendMail(user.getEmail(), activated, String.format(mails.getMailTemplateByName(activated), user.getUsername()));
        return true;
    }

}
