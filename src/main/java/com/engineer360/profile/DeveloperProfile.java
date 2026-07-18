package com.engineer360.profile;

import com.engineer360.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "developer_profiles")
public class DeveloperProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String githubUsername;
    private String leetcodeUsername;
    private String codeforcesUsername;
    private String codechefUsername;
    private String atcoderUsername;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public DeveloperProfile() {
    }

    public DeveloperProfile(
            String githubUsername,
            String leetcodeUsername,
            String codeforcesUsername,
            String codechefUsername,
            String atcoderUsername,
            User user
    ) {
        this.githubUsername = githubUsername;
        this.leetcodeUsername = leetcodeUsername;
        this.codeforcesUsername = codeforcesUsername;
        this.codechefUsername = codechefUsername;
        this.atcoderUsername = atcoderUsername;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getGithubUsername() {
        return githubUsername;
    }

    public String getLeetcodeUsername() {
        return leetcodeUsername;
    }

    public String getCodeforcesUsername() {
        return codeforcesUsername;
    }

    public String getCodechefUsername() {
        return codechefUsername;
    }

    public String getAtcoderUsername() {
        return atcoderUsername;
    }

    public User getUser() {
        return user;
    }

    public void setGithubUsername(String githubUsername) {
        this.githubUsername = githubUsername;
    }

    public void setLeetcodeUsername(String leetcodeUsername) {
        this.leetcodeUsername = leetcodeUsername;
    }

    public void setCodeforcesUsername(String codeforcesUsername) {
        this.codeforcesUsername = codeforcesUsername;
    }

    public void setCodechefUsername(String codechefUsername) {
        this.codechefUsername = codechefUsername;
    }

    public void setAtcoderUsername(String atcoderUsername) {
        this.atcoderUsername = atcoderUsername;
    }
}