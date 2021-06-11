package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.DrillingInfoDataRequest;
import com.iti.jets.openapi.model.DrillingInfoDataResponse;
import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.entities.ProductionBudget;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.repos.DrillingInfoRepository;
//import com.iti.jets.reportingsystem.repos.WellRepository;
import com.iti.jets.reportingsystem.repos.WellRepository;
import com.iti.jets.reportingsystem.services.DrillingInfoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class  DrillingServiceImpl implements DrillingInfoService {
    @Autowired
    private DrillingInfoRepository drillingInfoRepository;
    @Autowired
    private WellRepository wellRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<DrillingInfoDataResponse> getAllDrillingInfo() {

        List<DrillingInfoDataResponse> drillingInfoModelList = new ArrayList<>();
        List<DrillingInfo> drillingInfooo = drillingInfoRepository.findAll();
        Type listType = new TypeToken<List<DrillingInfoDataResponse>>() {
        }.getType();
        drillingInfoModelList = modelMapper.map(drillingInfooo, listType);
        return drillingInfoModelList;
    }

    @Override
    public List<DrillingInfoDataResponse> getForWellId(int id) {
        List<DrillingInfo> drillingInfo = drillingInfoRepository.getAllForWellId(id);
        System.out.println("drillingInfo====== " + drillingInfo);
        List<DrillingInfoDataResponse> drillingInfoModel = new ArrayList<>();
        Type listType = new TypeToken<List<DrillingInfoDataResponse>>() {
        }.getType();

        drillingInfoModel = modelMapper.map(drillingInfo, listType);
        return drillingInfoModel;
    }

    @Override
    public void creat(DrillingInfoDataRequest drillingInfoModel , int id) {
        DrillingInfo drillingInfo = new DrillingInfo();
        Well well = wellRepository.findById(id).get();
        drillingInfo = modelMapper.map(drillingInfoModel, DrillingInfo.class);
        drillingInfo.setWell(well);
        drillingInfoRepository.saveAndFlush(drillingInfo);
    }

    @Override
    public void delete(int id) {
        List<DrillingInfo> drillingInfos = drillingInfoRepository.findAllByWell_WellIdEquals(id);
        for (int i = 0; i < drillingInfos.size(); i++) {
            drillingInfoRepository.deleteById(drillingInfos.get(i).getId());
        }

    }


    @Override
    public void deleteWellInSpecificId(int wellId, int id) {
        drillingInfoRepository.deleteById(id);
//        DrillingInfo drillingInfo = drillingInfoRepository.findAllByWell_WellIdEqualsAndIdEquals(wellId ,id);

    }

    @Override
    public DrillingInfoDataResponse getWellForId(int wellId, int id) {

        DrillingInfo drillingInfo = drillingInfoRepository.findById(id).get();
        System.out.println("drillingInfo====== " + drillingInfo);
        DrillingInfoDataResponse drillingInfoModel = new DrillingInfoDataResponse();
        drillingInfoModel = modelMapper.map(drillingInfo, DrillingInfoDataResponse.class);
        return drillingInfoModel;
    }

    @Override
    public void updateWellForId(int wellId, int id, DrillingInfoDataRequest drillingInfoModel) {
        System.out.println("wellID == "+ wellId );
        System.out.println("idd == "+id);
        DrillingInfo drillingInfo = drillingInfoRepository.findAllByWell_WellIdEqualsAndIdEquals(wellId, id);
        System.out.println("drilling info == "+ drillingInfo);
        if (drillingInfoModel.getReleaseDate() != null) {
            drillingInfo.setReleaseDate(Date.from(drillingInfoModel.getReleaseDate().toInstant()));
        }

        if (drillingInfoModel.getWellDescription() != null) {
            drillingInfo.setWellDescription(drillingInfoModel.getWellDescription());
        }
        if (drillingInfoModel.getWellType() != null) {
            drillingInfo.setWellType(drillingInfoModel.getWellType());
        }
        if (drillingInfoModel.getBoreType() != null) {
            drillingInfo.setBoreType(drillingInfoModel.getBoreType());
        }
        if (drillingInfoModel.getMeasuredDepth() != null) {
            drillingInfo.setMeasuredDepth(drillingInfoModel.getMeasuredDepth());
        }
        if (drillingInfoModel.getTvdDepth() != null) {
            drillingInfo.setTvdDepth(drillingInfoModel.getTvdDepth());
        }
        if (drillingInfoModel.getBbtp() != null) {
            drillingInfo.setBbtp(drillingInfoModel.getBbtp());
        }
        if (drillingInfoModel.getProductionGeneralInfo() != null) {
            drillingInfo.setProductionGeneralInfo(drillingInfoModel.getProductionGeneralInfo());
        }
        drillingInfoRepository.saveAndFlush(drillingInfo);
    }
}
