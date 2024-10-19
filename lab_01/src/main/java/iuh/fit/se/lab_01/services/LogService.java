package iuh.fit.se.lab_01.services;

import iuh.fit.se.lab_01.models.Log;
import iuh.fit.se.lab_01.repositories.LogRepository;

import java.util.List;

public class LogService {
    private LogRepository logRepository;
    public LogService() {
        this.logRepository = new LogRepository();
    }
    public void addLog(Log log) {
        logRepository.addLog(log);
    }
    public List<Log> getAllLogs() {
        return logRepository.getAllLogs();
    }
}
