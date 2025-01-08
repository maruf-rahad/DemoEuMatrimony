package com.eu.demomatrimony.controllers;

import com.eu.demomatrimony.interfaces.ProfileService;
import com.eu.demomatrimony.models.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfileController {

    private final ProfileService profileService;

    ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    private List<Profile> getAllProfiles() {
        List<Profile> profiles = new ArrayList<Profile>();
        profiles = profileService.getAllProfiles();
        return profiles;
    }

    @GetMapping("/profile/{id}")
    private Profile getProfileById(@PathVariable Long id) {

        return profileService.getProfileById(id);
    }

    @PostMapping("/profile")
    private Profile addProfile(@RequestBody Profile profile) {
        return profileService.createProfile(profile);
    }

    @PutMapping("/profile")
    private Profile updateProfile(@RequestBody Profile profile) {
        return profileService.updateProfile(profile);
    }

    @DeleteMapping("/profile/{id}")
    private HttpStatus deleteProfile(@PathVariable Long id) {
        profileService.deleteProfileById(id);

        return HttpStatus.OK;
    }
}
