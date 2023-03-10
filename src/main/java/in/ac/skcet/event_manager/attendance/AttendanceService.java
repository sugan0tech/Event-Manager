package in.ac.skcet.event_manager.attendance;

import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.time_table.TimeTableHoursService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceService {
    AttendanceRepository attendanceRepository;
    StudentService studentService;

    public void updateAttendance(String rollNo, Attendance attendance, Boolean status) throws StudentNotFoundException {
        int periodNumber = TimeTableHoursService.getPeriodNumber(LocalTime.now());
        if(periodNumber == 0){
            // error will be thrown
            log.warn("------ Invalid period time " + periodNumber + "-----------");
            return;
        }
        Student student = studentService.findByID(rollNo);
        BitSet bitSet = new BitSet();
        bitSet.set(periodNumber, status);
        student.addAttendance(attendance, bitSet);

        studentService.save(student);
    }

    public Map<String, Double> getAttendancePercentageDaily(String classCode, Date startDate, Date endDate){
        Map<String, Double> studentPercentage = new TreeMap<>();
        long totalDays = countDays(startDate, endDate);
        log.info("total days " + totalDays);
        studentService.findByClassCode(classCode).forEach(student -> studentPercentage.put(student.getRollNo(), ((double)noOfDaysPresent(student, startDate, endDate)/totalDays)*100));
        return studentPercentage;
    }

    public List<Map<String, Map<String, Boolean>>> getAttendancePerStudentAtRange(String rollNo, Date startDate, Date endDate){
        List<Map<String, Map<String, Boolean>>> perStudentPercentageList = new LinkedList<>();
        List<Attendance> attendanceList = attendanceRepository.findAll().stream().filter(attendance -> attendance.getDate().after(startDate) && attendance.getDate().before(endDate)).collect(Collectors.toList());
        attendanceList.forEach(attendance -> {
            try {
                perStudentPercentageList.add(getAttendancePerDayPerStudent(rollNo, attendance));
            } catch (StudentNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return perStudentPercentageList;
    }
    public Map<String, Map<String, Boolean>> getAttendancePerDayPerStudent(String rollNo, Attendance attendance) throws StudentNotFoundException {
        Map<String, Map<String, Boolean>> periodMap = new TreeMap<>();
        Map<String, Boolean> periods = new TreeMap<>();
        BitSet bitSet = studentService.findByID(rollNo).getAttendanceBitSetMap().get(attendance);
        if(bitSet == null){
            return periodMap;
        }
        log.info(bitSet.toString());
        for(int period = 1; period <= 8; period++){
            periods.put("Day " + attendance.getId() + " P -> " + period + "", bitSet.get(period));
        }
        periodMap.put(rollNo, periods);
        return periodMap;
    }

    public Map<String, Double> getAttendancePercentageHourly(String classCode, Date startDate, Date endDate){
        Map<String, Double> studentPercentage = new TreeMap<>();
        long totalHours = countDays(startDate, endDate)*7;
        log.info("total hours " + totalHours);
        studentService.findByClassCode(classCode).forEach(student -> studentPercentage.put(student.getRollNo(), ((double)noOfHoursPresent(student, startDate, endDate)/totalHours)*100));
        return studentPercentage;
    }


    public Long noOfDaysPresent(Student student, Date startDate, Date endDate){
        return student.getAttendanceBitSetMap().keySet().stream().filter(attendance -> attendance.getDate().after(startDate) && attendance.getDate().before(endDate)).count();
    }

    public Long noOfHoursPresent(Student student, Date startDate, Date endDate){
        List<Attendance> attendanceList = student.getAttendanceBitSetMap().keySet().stream().filter(attendance -> attendance.getDate().after(startDate) && attendance.getDate().before(endDate)).collect(Collectors.toList());
        long count = 0;

        for (Attendance attendance : attendanceList) {
            count += student.getAttendanceBitSetMap().get(attendance).length();
        }
        return count;
    }

    public Long countDays(Date startDate, Date endDate){
        attendanceRepository.findAll().forEach(attendance -> log.info(attendance.toString()));
        log.info(startDate.toString());
        log.info(endDate.toString());
        return attendanceRepository.findAll().stream().filter(attendance ->
                attendance.getDate().after(startDate) && attendance.getDate().before(endDate)).count();
    }

    public Attendance findByDate(String date){
        Attendance attendance = attendanceRepository.findByDate(java.sql.Date.valueOf(date)).orElse(null);
        if(attendance == null){
            attendance = Attendance.builder()
                    .date(java.sql.Date.valueOf(date))
                    .build();
            attendanceRepository.save(attendance);
        }
        return attendance;
    }

    public Boolean isPresent(String rollNo, String date) throws StudentNotFoundException {
        Student student = studentService.findByID(rollNo);
        Attendance attendance = findByDate(date);
        if(student.getAttendanceBitSetMap().get(attendance) == null){
            student.addAttendance(attendance, new BitSet());
        }
        studentService.save(student);
        return !studentService.findByID(rollNo).getAttendanceBitSetMap().get(findByDate(date)).isEmpty();
    }

}
