package com.engineer360.profile;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class DeveloperProfileController {

    private final DeveloperProfileService profileService;

    public DeveloperProfileController(
            DeveloperProfileService profileService
    ) {
        this.profileService = profileService;
    }

    @PutMapping
    public DeveloperProfile saveProfile(
            @RequestBody DeveloperProfileRequest request,
            Authentication authentication
    ) {

        return profileService.saveProfile(
                request,
                authentication.getName()
        );
    }

    @GetMapping
    public DeveloperProfile getProfile(
            Authentication authentication
    ) {

        return profileService.getProfile(
                authentication.getName()
        );
    }
}