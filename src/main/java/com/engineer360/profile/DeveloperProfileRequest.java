package com.engineer360.profile;

public class DeveloperProfileRequest {

    private String githubUsername;
    private String leetcodeUsername;
    private String codeforcesUsername;
    private String codechefUsername;
    private String atcoderUsername;

    public DeveloperProfileRequest() {
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