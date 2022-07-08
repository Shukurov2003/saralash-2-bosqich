package com.example.university.service;

import com.example.university.payload.GroupPayload;
import org.springframework.http.ResponseEntity;


public interface GroupService {

    ResponseEntity<?> createGroup(GroupPayload groupPayload);
    ResponseEntity<?> editGroup(GroupPayload groupPayload);
    void  deleteGroup(Long id);
    ResponseEntity<?>  findAllGroups();
    ResponseEntity<?> findGroupById(Long id);
}
