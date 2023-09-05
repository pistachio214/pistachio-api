package com.pistachio.system.service.impl;

import com.pistachio.common.exception.ServiceException;
import com.pistachio.common.utils.StringUtil;
import com.pistachio.system.dto.req.ExceptionLogListRequest;
import com.pistachio.system.dto.req.OperLogListRequest;
import com.pistachio.system.entity.SysExceptionLogEntity;
import com.pistachio.system.entity.SysOperLogEntity;
import com.pistachio.system.repository.SysExceptionLogRepository;
import com.pistachio.system.repository.SysOperLogRepository;
import com.pistachio.system.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: SysOperLogServiceImpl
 * @date: 2023/08/09 14:23
 * @author: Pengsy
 */
@Service
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private SysOperLogRepository sysOperLogRepository;

    @Autowired
    private SysExceptionLogRepository sysExceptionLogRepository;

    @Override
    public SysOperLogEntity save(SysOperLogEntity entity) {
        return sysOperLogRepository.save(entity);
    }

    @Override
    public SysExceptionLogEntity save(SysExceptionLogEntity entity) {
        return sysExceptionLogRepository.save(entity);
    }

    @Override
    public Page<SysOperLogEntity> operPageLists(OperLogListRequest request) {
        // 构造分页参数
        Pageable pageable = PageRequest.of(request.getCurrent(), request.getSize(), Sort.by("createdAt").descending());

        return sysOperLogRepository.findAll((Specification<SysOperLogEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!StringUtil.isEmpty(request.getOperUserName())) {
                predicateList.add(criteriaBuilder.like(root.get("operUserName"), "%" + request.getOperUserName() + "%"));
            }

            if (StringUtil.isNotNull(request.getStartAt()) && StringUtil.isNotNull(request.getEndAt())) {
                predicateList.add(criteriaBuilder.between(root.get("createdAt"), request.getStartAt(), request.getEndAt()));
            }

            return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
        }, pageable);
    }

    @Override
    public SysOperLogEntity getOperLogById(Long id) {
        return sysOperLogRepository.findById(id).orElseThrow(() -> new ServiceException("操作日志不存在"));
    }

    @Override
    public Page<SysExceptionLogEntity> exceptionPageLists(ExceptionLogListRequest request) {
        // 构造分页参数
        Pageable pageable = PageRequest.of(request.getCurrent(), request.getSize(), Sort.by("createdAt").descending());

        return sysExceptionLogRepository.findAll((Specification<SysExceptionLogEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (StringUtil.isNotNull(request.getStartAt()) && StringUtil.isNotNull(request.getEndAt())) {
                predicateList.add(criteriaBuilder.between(root.get("createdAt"), request.getStartAt(), request.getEndAt()));
            }

            if (!StringUtil.isEmpty(request.getOperUserName())) {
                predicateList.add(criteriaBuilder.like(root.get("operUserName"), "%" + request.getOperUserName() + "%"));
            }

            return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
        }, pageable);
    }

    @Override
    public SysExceptionLogEntity getExceptionLogById(Long id) {
        return sysExceptionLogRepository.findById(id).orElseThrow(() -> new ServiceException("异常日志不存在"));
    }
}
