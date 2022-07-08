package com.example.university.controller;

import com.example.university.model.Result;
import com.example.university.payload.MarkPayload;
import com.example.university.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;

    @PostMapping("/mark/")
    public ResponseEntity<?> putMark(@RequestBody MarkPayload markPayload){

        if(markPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The mark coming is error!"));
        }
        return journalService.putTotalMarkToStudent(markPayload);
    }

    @PostMapping("/marks/")
    public ResponseEntity<?> putMarks(@RequestBody List<MarkPayload> markPayloads){
        if(markPayloads==null){
            return ResponseEntity.badRequest().body(Result.error("The marks coming is error!"));
        }
        return journalService.putTotalMarksToStudents(markPayloads);
    }
    @PutMapping("/mark/")
    public ResponseEntity<?> editMark(@RequestBody MarkPayload markPayload){
        if(markPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The mark coming is error!"));
        }
        return journalService.editPutTotalMarkToStudent(markPayload);
    }
    @PutMapping("/marks/")
    public ResponseEntity<?> editMark(@RequestBody List<MarkPayload> markPayloads){
        if(markPayloads==null){
            return ResponseEntity.badRequest().body(Result.error("The mark coming is error!"));
        }
        return journalService.editPutTotalMarksToStudents(markPayloads);
    }
    @GetMapping("/{id}/")
    public ResponseEntity<?> findById(@PathVariable Long id){
        if(id == null){
            return  ResponseEntity.badRequest().body(Result.error("id is null !"));
        }
        return journalService.findJournalByGroupId(id);
    }
}
