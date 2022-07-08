package com.example.university.controller;

import com.example.university.model.Result;
import com.example.university.payload.GroupPayload;
import com.example.university.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/multiple/save/")
    public ResponseEntity<?> createMultiple(@RequestBody List<GroupPayload> groupsPayload){
        if (groupsPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the group is null ! "));
        }
        return groupService.createMultipleGroups(groupsPayload);
    }
    @PostMapping("/save/")
    public ResponseEntity<?> create(@RequestBody GroupPayload groupPayload){
        if (groupPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the group is null ! "));
        }
        return groupService.createGroup(groupPayload);
    }
    @PutMapping("/edit/")
    public ResponseEntity<?> edit(@RequestBody GroupPayload groupPayload){
        if (groupPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the group is null ! "));
        }
        return groupService.editGroup(groupPayload);
    }
    @DeleteMapping("/delete/{id}/")
    public void delete(@PathVariable Long id){
        if (id == null){
            System.out.println("Id is null");
        }
        groupService.deleteGroup(id);
    }
    @GetMapping("/all/")
    public ResponseEntity<?> findAll(){
        return groupService.findAllGroups();
    }

    @GetMapping("/{id}/")
    public ResponseEntity<?> findById(@PathVariable Long id){
        if (id==null){
            return ResponseEntity.badRequest().body(Result.error("Id is null ! "));
        }
        return groupService.findGroupById(id);
    }

}
