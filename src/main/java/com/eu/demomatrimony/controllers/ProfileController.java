package com.eu.demomatrimony.controllers;

import com.eu.demomatrimony.dto.ProfileDto;
import com.eu.demomatrimony.interfaces.ProfileService;
import com.eu.demomatrimony.models.Profile;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Profile Apis", description = "create, get, update, delete")
public class ProfileController {

    private final ProfileService profileService;
    private final ModelMapper modelMapper;

    public ProfileController(ProfileService profileService, ModelMapper modelMapper) {
        this.profileService = profileService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/profile")
    private List<ProfileDto> getAllProfiles() {
        List<Profile> profiles = new ArrayList<Profile>();
        profiles = profileService.getAllProfiles();
        return profiles.stream()
                .map(
                        profile -> modelMapper.map(profile, ProfileDto.class)
                ).toList();
    }

    @GetMapping("/profile/{id}")
    private ProfileDto getProfileById(@PathVariable Long id) {

        return modelMapper.map(
                profileService.getProfileById(id), ProfileDto.class
        );
    }

    @PostMapping("/profile")
    private ProfileDto addProfile(@RequestBody ProfileDto profileDto) {
        Profile profile = modelMapper.map(profileDto, Profile.class);

        return modelMapper.map(
                profileService.createProfile(profile), ProfileDto.class
        );
    }

    @PutMapping("/profile")
    private ProfileDto updateProfile(@RequestBody ProfileDto profileDto) {
        Profile profile = modelMapper.map(profileDto, Profile.class);
        return modelMapper.map(
                profileService.updateProfile(profile), ProfileDto.class
        );
    }

    @DeleteMapping("/profile/{id}")
    private HttpStatus deleteProfile(@PathVariable Long id) {
        profileService.deleteProfileById(id);

        return HttpStatus.OK;
    }
}
