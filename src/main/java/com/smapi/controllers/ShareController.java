package com.smapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smapi.models.Share;
import com.smapi.services.ShareService;

@RestController
@RequestMapping("/shares")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @GetMapping
    public Iterable<Share> getAllShares() {
        return shareService.getAllShares();
    }

    @PostMapping
    public Share createShare(@RequestBody Share share) {
        return shareService.createShare(share);
    }

    @GetMapping("/{shareId}")
    public ResponseEntity<Share> getShareById(@PathVariable Long shareId) {
        Share share = shareService.getShareById(shareId);
        if (share != null) {
            return ResponseEntity.ok(share);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{shareId}")
    public ResponseEntity<Share> updateShare(@PathVariable Long shareId, @RequestBody Share share) {
        Share updatedShare = shareService.updateShare(shareId, share);
        if (updatedShare != null) {
            return ResponseEntity.ok(updatedShare);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{shareId}")
    public ResponseEntity<Void> deleteShare(@PathVariable Long shareId) {
        boolean deleted = shareService.deleteShare(shareId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
