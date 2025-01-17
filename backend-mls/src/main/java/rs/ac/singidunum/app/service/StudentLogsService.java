package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.StudentLog;
import rs.ac.singidunum.app.repository.StudentLogsRepository;

import java.util.List;

@Service
public class StudentLogsService {
    @Autowired
    private StudentLogsRepository studentLogsRepository;

    public List<StudentLog> getAll(){
        return this.studentLogsRepository.findAll();
    }

    public StudentLog save(StudentLog studentLog){ return this.studentLogsRepository.insert(studentLog); }
}

