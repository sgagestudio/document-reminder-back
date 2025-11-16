package com.sgagestudio.demo.document_reminder.controller;

import com.sgagestudio.demo.document_reminder.data.DataRequestTemporality;
import com.sgagestudio.demo.document_reminder.service.DataRequestService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/automation")
@CrossOrigin(origins = "*")
public class AutomationController {

    private final DataRequestService dataRequestService;

    public AutomationController(DataRequestService dataRequestService) {
        this.dataRequestService = dataRequestService;
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void dispatchDaily() {
        dataRequestService.processTemporality(DataRequestTemporality.DAILY);
    }

    @Scheduled(cron = "0 15 8 * * MON")
    public void dispatchWeekly() {
        dataRequestService.processTemporality(DataRequestTemporality.WEEKLY);
    }

    @Scheduled(cron = "0 30 8 1 * *")
    public void dispatchMonthly() {
        dataRequestService.processTemporality(DataRequestTemporality.MONTHLY);
    }
}
