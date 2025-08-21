package com.eu.demomatrimony.serviceImpl;

import com.eu.demomatrimony.interfaces.ProfileService;
import com.eu.demomatrimony.models.Profile;
import com.eu.demomatrimony.repositories.ProfileRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getAllProfiles() {
        System.out.println("getAllProfiles");
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfileById(Long id) {
        return getById(id);
    }

    @Override
    public Profile createProfile(Profile profile) {
        System.out.println(profile);
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public Profile updateProfile(Profile newProfile) {
        Profile existingProfile = getById(newProfile.getId());
        BeanUtils.copyProperties(newProfile, existingProfile, "id");
        profileRepository.save(existingProfile);
        return existingProfile;
    }

    @Override
    public void deleteProfileById(Long id) {
        Profile profile = getById(id);

        profileRepository.deleteById(profile.getId());
    }

    private Profile getById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Profile not found with id: " + id));
    }
}
