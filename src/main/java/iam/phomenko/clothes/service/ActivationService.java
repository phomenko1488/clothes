package iam.phomenko.clothes.service;

import iam.phomenko.clothes.exception.LinkNotFoundException;
import iam.phomenko.clothes.exception.UserAlreadyActivatedException;

public interface ActivationService {
    boolean activate(String activationLink) throws LinkNotFoundException, UserAlreadyActivatedException;
}
