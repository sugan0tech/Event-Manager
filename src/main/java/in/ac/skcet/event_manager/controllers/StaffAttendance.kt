package `in`.ac.skcet.event_manager.controllers

import `in`.ac.skcet.event_manager.teacher.Teacher
import `in`.ac.skcet.event_manager.teacher.TeacherService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList

//@RestController
//@RequestMapping("/staff/attendance")
//@AllArgsConstructor
class StaffAttendanceK (private val teacherService: TeacherService){


    @PostMapping("/put/{staffId}/{status}")
    fun markAttendance(@PathVariable staffId: String, @PathVariable status : Boolean) : Teacher {
        var teacher : Teacher = teacherService.findById(staffId)
        teacher.setPresentKt(status)
        return teacherService.save(teacher)
    };

    @PostMapping("/get/list")
    fun getAttendanceList() : List<Teacher> {
        return teacherService.findAll().stream().filter { teacher: Teacher -> teacher.getPresentKt() }.toList()
    }
}