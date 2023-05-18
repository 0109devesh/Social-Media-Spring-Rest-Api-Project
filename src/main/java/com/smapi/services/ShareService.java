package com.smapi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smapi.models.Share;
import com.smapi.repositories.ShareRepository;

@Service
public class ShareService {

    @Autowired
    private ShareRepository shareRepository;

    public Iterable<Share> getAllShares() {
        return shareRepository.findAll();
    }

    public Share createShare(Share share) {
        share.setCreatedAt(LocalDateTime.now());
        return shareRepository.save(share);
    }

    public Share getShareById(Long shareId) {
        return shareRepository.findById(shareId).orElse(null);
    }

    public Share updateShare(Long shareId, Share updatedShare) {
        Optional<Share> existingShare = shareRepository.findById(shareId);
        if (existingShare.isPresent()) {
            Share share = existingShare.get();
            share.setUpdatedAt(LocalDateTime.now());
            return shareRepository.save(share);
        } else {
            return null;
        }
    }

    public boolean deleteShare(Long shareId) {
        Optional<Share> existingShare = shareRepository.findById(shareId);
        if (existingShare.isPresent()) {
            shareRepository.deleteById(shareId);
            return true;
        } else {
            return false;
        }
    }
}

