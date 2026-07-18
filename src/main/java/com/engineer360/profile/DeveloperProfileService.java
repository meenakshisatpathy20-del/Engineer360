package com.engineer360.profile;

import com.engineer360.user.User;
import com.engineer360.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeveloperProfileService {

    private final DeveloperProfileRepository profileRepository;
    private final UserRepository userRepository;

    public DeveloperProfileService(
            DeveloperProfileRepository profileRepository,
            UserRepository userRepository
    ) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );
    }

    public DeveloperProfile saveProfile(
            DeveloperProfileRequest request,
            String email
    ) {

        User user = getUser(email);

        DeveloperProfile profile =
                profileRepository.findByUser(user)
                        .orElseGet(() ->
                                new DeveloperProfile(
                                        request.getGithubUsername(),
                                        request.getLeetcodeUsername(),
                                        request.getCodeforcesUsername(),
                                        request.getCodechefUsername(),
                                        request.getAtcoderUsername(),
                                        user
                                )
                        );

        profile.setGithubUsername(request.getGithubUsername());
        profile.setLeetcodeUsername(request.getLeetcodeUsername());
        profile.setCodeforcesUsername(request.getCodeforcesUsername());
        profile.setCodechefUsername(request.getCodechefUsername());
        profile.setAtcoderUsername(request.getAtcoderUsername());

        return profileRepository.save(profile);
    }

    public DeveloperProfile getProfile(String email) {

        User user = getUser(email);

        return profileRepository.findByUser(user)
                .orElseThrow(() ->
                        new IllegalArgumentException("Profile not found")
                );
    }
}