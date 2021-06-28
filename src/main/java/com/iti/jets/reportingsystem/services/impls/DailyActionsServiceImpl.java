package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.LabMeasurementResponse;
import com.iti.jets.openapi.model.WellDailyActionsRequest;
import com.iti.jets.openapi.model.WellDailyActionsResponse;
import com.iti.jets.reportingsystem.entities.DailyActions;
import com.iti.jets.reportingsystem.entities.ShutinTypeLevel4;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.DailyActionsRepository;
import com.iti.jets.reportingsystem.repos.WellRepo;
import com.iti.jets.reportingsystem.services.DailyActionsService;
import com.iti.jets.reportingsystem.utils.mappers.DailyActionsMapper;
import com.iti.jets.reportingsystem.utils.mappers.helpers.OffsetDateTimeHelper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DailyActionsServiceImpl implements DailyActionsService {

    private DailyActionsRepository dailyActionsRepository;

    private WellRepo wellRepo;

    private ModelMapper modelMapper;

    private DailyActionsMapper dailyActionsMapper;

    @Autowired
    public DailyActionsServiceImpl(DailyActionsRepository dailyActionsRepository, WellRepo wellRepo, ModelMapper modelMapper, DailyActionsMapper dailyActionsMapper) {
        this.dailyActionsRepository = dailyActionsRepository;
        this.wellRepo = wellRepo;
        this.modelMapper = modelMapper;
        this.dailyActionsMapper = dailyActionsMapper;
    }


    @Override
    public void insert(Integer wellId, WellDailyActionsRequest wellDailyActionsRequest) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with id");
        }
        Well well = wellRepo.findById(wellId).isPresent() ?
                wellRepo.findById(wellId).get() : null;
        if (well == null) {
            System.out.println("no well with given id");
            return;
        }
        DailyActions dailyActions = dailyActionsMapper.dailyActionsMap(wellDailyActionsRequest);
        ShutinTypeLevel4 shutinTypeLevel4 = new ShutinTypeLevel4();
        shutinTypeLevel4.setId(Math.toIntExact(wellDailyActionsRequest.getSiLVL4()));
        dailyActions.setShutinTypeLevel4(shutinTypeLevel4);
        dailyActions.setWell(well);
        dailyActionsRepository.saveAndFlush(dailyActions);

    }

    @Override
    public void update(Integer wellId, Integer labId, WellDailyActionsRequest wellDailyActionsRequest) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with id");
        }
        if(!dailyActionsRepository.findById(labId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record found with this id");
        }
        DailyActions dailyAction = dailyActionsRepository.findById(labId).get();
        modelMapper.map(wellDailyActionsRequest, dailyAction);
        dailyActionsRepository.saveAndFlush(dailyAction);
    }

    @Override
    public List<WellDailyActionsResponse> getAllDailyActions() {
        System.out.println("lolHRE");
        Type listType = new TypeToken<List<WellDailyActionsResponse>>() {
        }.getType();
        List<WellDailyActionsResponse> list;
        list = modelMapper.map(dailyActionsRepository.findAll(), listType);
        List<DailyActions> list1 = dailyActionsRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
//            list.get(i).setDate(OffsetDateTime.from(list1.get(i).getDate().toInstant()));
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(list1.get(i).getDate()));
            list.get(i).setWellId(Long.valueOf(list1.get(i).getWell().getWellId()));
            list.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
        }
        return list;
    }

    @Override
    public List<WellDailyActionsResponse> getAllDailyActionsFromWell(Integer wellId) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with id");
        }
        Type listType = new TypeToken<List<WellDailyActionsResponse>>() {
        }.getType();
        List<WellDailyActionsResponse> list;
        list = modelMapper.map(dailyActionsRepository.findAllByWell_WellIdEquals(wellId), listType);
        List<DailyActions> list1 = dailyActionsRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(list1.get(i).getDate()));
            list.get(i).setWellId(Long.valueOf(list1.get(i).getWell().getWellId()));
            list.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
        }
        return list;
    }

    @Override
    public List<WellDailyActionsResponse> getAllDailyActions(Date beginDate, Date endDate) {
        Type listType = new TypeToken<List<WellDailyActionsResponse>>() {
        }.getType();
        List<WellDailyActionsResponse> resultList;
        if(dailyActionsRepository.findAllByDateGreaterThanEqualAndDateLessThanEqual(beginDate, endDate) == null)
        {
            throw new ResourceNotFoundException("there is no record with this data");
        }
        resultList = modelMapper.map(dailyActionsRepository.findAllByDateGreaterThanEqualAndDateLessThanEqual(beginDate, endDate), listType);
        List<DailyActions> list1 = dailyActionsRepository.findAll();
        for (int i = 0; i < resultList.size(); i++) {
            resultList.get(i).setDate(OffsetDateTimeHelper.dateHelper(list1.get(i).getDate()));
            resultList.get(i).setWellId(Long.valueOf(list1.get(i).getWell().getWellId()));
            resultList.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
        }
        return resultList;
    }

    @Override
    public List<WellDailyActionsResponse> getAllDailyActionsFromWell(Integer wellId, Date beginDate, Date endDate) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with id");
        }
        List<DailyActions> returnedList = dailyActionsRepository.findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(wellId, beginDate, endDate);
        if (wellRepo.findById(wellId).isPresent() && !returnedList.isEmpty()) {
            Type listType = new TypeToken<List<LabMeasurementResponse>>() {
            }.getType();
            List<WellDailyActionsResponse> resultList;
            if(dailyActionsRepository.findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(wellId, beginDate, endDate) == null)
            {
                throw new ResourceNotFoundException("there is no record woth this data");
            }
            resultList = modelMapper.map(dailyActionsRepository.findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(wellId, beginDate, endDate), listType);
            List<DailyActions> list1 = dailyActionsRepository.findAll();
            for (int i = 0; i < resultList.size(); i++) {
                resultList.get(i).setDate(OffsetDateTimeHelper.dateHelper(list1.get(i).getDate()));
                resultList.get(i).setWellId(Long.valueOf(list1.get(i).getWell().getWellId()));
//            list.get(i).setDate(OffsetDateTime.from(list1.get(i).getDate().toInstant()));
                resultList.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
            }
            return resultList;
        } else {
            return null;
        }
    }

    //
    @Override
    public WellDailyActionsResponse getAdailyActionFromAwell(Integer wellId, Integer reportId) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with id");
        }
        if(!dailyActionsRepository.findById(reportId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
        List<WellDailyActionsResponse> dailyActionsList = new ArrayList<>();
        dailyActionsList = getAllDailyActionsFromWell(wellId);
        if(dailyActionsList == null)
        {
            throw new ResourceNotFoundException("There is no record with this data ");
        }
        WellDailyActionsResponse wellDailyActionsResponse = new WellDailyActionsResponse();
        wellDailyActionsResponse = dailyActionsList.get(reportId - 1);
        return wellDailyActionsResponse;
    }
    @Override
    public boolean delete(Integer wellId, Integer reportId) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with id");
        }
        if(!dailyActionsRepository.findById(reportId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
        System.out.println(reportId + " ///");
        if (dailyActionsRepository.findById(reportId).isPresent()) {
            dailyActionsRepository.deleteById(reportId);
            return true;
        } else {
            System.out.println("lll");
            return false;
        }

    }

    public List<WellDailyActionsResponse> getAllDailyActionsByShLvl4(Long sLvl4) {
        Type listType = new TypeToken<List<WellDailyActionsResponse>>() {
        }.getType();
        List<WellDailyActionsResponse> list;
        if(dailyActionsRepository.findByShLvl4(Math.toIntExact(sLvl4)) == null)
        {
            throw new ResourceNotFoundException("There is no record found in the table");
        }
        list = modelMapper.map(dailyActionsRepository.findByShLvl4(Math.toIntExact(sLvl4)), listType);
        List<DailyActions> list1 = dailyActionsRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWellId(Long.valueOf(list1.get(i).getWell().getWellId()));
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(list1.get(i).getDate()));
            list.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
        }
        return list;

    }

    ;

    public List<WellDailyActionsResponse> getAllDailyActionsByLosses(Double losses) {
        Type listType = new TypeToken<List<WellDailyActionsResponse>>() {
        }.getType();
        List<WellDailyActionsResponse> list;
        if(dailyActionsRepository.findByLosses(losses) == null)
        {
            throw new ResourceNotFoundException("There is no record in the table");
        }
        list = modelMapper.map(dailyActionsRepository.findByLosses(losses), listType);
        List<DailyActions> list1 = dailyActionsRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
//            list.get(i).setDate(OffsetDateTime.from(list1.get(i).getDate().toInstant()));
            list.get(i).setWellId(Long.valueOf(list1.get(i).getWell().getWellId()));
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(list1.get(i).getDate()));
            list.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
        }
        return list;

    }

    ;

    public List<WellDailyActionsResponse> getAllDailyActionsByDownTime(Float downTime) {
        Type listType = new TypeToken<List<WellDailyActionsResponse>>() {
        }.getType();
        List<WellDailyActionsResponse> list;
        if(dailyActionsRepository.findByDownTime(downTime) == null){
            throw new ResourceNotFoundException("There is no record found in the table");
        }
        list = modelMapper.map(dailyActionsRepository.findByDownTime(downTime), listType);
        List<DailyActions> list1 = dailyActionsRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWellId(Long.valueOf(list1.get(i).getWell().getWellId()));
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(list1.get(i).getDate()));
            list.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
        }
        return list;

    }

    ;

    @Override
    public List<WellDailyActionsResponse> getAllDailyActionsFromWellWithShLvl4(Integer wellId, Long shlvl4) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with id");
        }
        List<DailyActions> returnedList = dailyActionsRepository.findAllByWell_WellIdEqualsAndShutinTypeLevel4Equals(wellId, Math.toIntExact(shlvl4));
        if(returnedList == null)
        {
            throw new ResourceNotFoundException("There is no record found in the table");
        }
        if (wellRepo.findById(wellId).isPresent() && !returnedList.isEmpty()) {
            Type listType = new TypeToken<List<LabMeasurementResponse>>() {
            }.getType();
            List<WellDailyActionsResponse> resultList;
            resultList = modelMapper.map(returnedList, listType);
            List<DailyActions> list1 = dailyActionsRepository.findAll();
            for (int i = 0; i < resultList.size(); i++) {
                resultList.get(i).setWellId(Long.valueOf(list1.get(i).getWell().getWellId()));
                resultList.get(i).setDate(OffsetDateTimeHelper.dateHelper(returnedList.get(i).getDate()));
                resultList.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
            }
            return resultList;
        } else {
            return null;
        }
    }

    @Override
    public List<WellDailyActionsResponse> getAllDailyActionsFromWellWithLosses(Integer wellId, Double losses) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with id");
        }

        List<DailyActions> returnedList = dailyActionsRepository.findAllByWell_WellIdEqualsAndLossesEquals(wellId, Double.valueOf(losses));
        if(returnedList == null)
        {
            throw new ResourceNotFoundException("There is no record found in the table");
        }
        if (wellRepo.findById(wellId).isPresent() && !returnedList.isEmpty()) {
            Type listType = new TypeToken<List<LabMeasurementResponse>>() {
            }.getType();
            List<WellDailyActionsResponse> resultList;
            resultList = modelMapper.map(returnedList, listType);
            List<DailyActions> list1 = dailyActionsRepository.findAll();
            for (int i = 0; i < resultList.size(); i++) {
            resultList.get(i).setWellId(Long.valueOf(list1.get(i).getWell().getWellId()));
                resultList.get(i).setDate(OffsetDateTimeHelper.dateHelper(returnedList.get(i).getDate()));
                resultList.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
            }
            return resultList;
        } else {
            return null;
        }
    }

    @Override
    public List<WellDailyActionsResponse> getAllDailyActionsFromWellWithDownTime(Integer wellId, Float downTime) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with id");
        }
        List<DailyActions> returnedList = dailyActionsRepository.findAllByWell_WellIdEqualsAndDownTimeEquals(wellId, Float.valueOf(downTime));
        if(returnedList==null)
        {
            throw new ResourceNotFoundException("There is no record found with this data");
        }
        if (wellRepo.findById(wellId).isPresent() && !returnedList.isEmpty()) {
            Type listType = new TypeToken<List<LabMeasurementResponse>>() {
            }.getType();
            List<WellDailyActionsResponse> resultList;
            resultList = modelMapper.map(returnedList, listType);
            List<DailyActions> list1 = dailyActionsRepository.findAll();
            for (int i = 0; i < resultList.size(); i++) {
                resultList.get(i).setWellId(Long.valueOf(list1.get(i).getWell().getWellId()));
                resultList.get(i).setDate(OffsetDateTimeHelper.dateHelper(returnedList.get(i).getDate()));
                resultList.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
                resultList.get(i).setSiLVL4(Long.valueOf(list1.get(i).getShutinTypeLevel4().getId()));
            }
            return resultList;
        } else {
            return null;
        }
    }


}
