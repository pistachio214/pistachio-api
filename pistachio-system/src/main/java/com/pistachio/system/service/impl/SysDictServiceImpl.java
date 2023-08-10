package com.pistachio.system.service.impl;

import com.pistachio.common.core.ConvertCore;
import com.pistachio.common.exception.ServiceException;
import com.pistachio.common.utils.StringUtil;
import com.pistachio.system.dto.req.*;
import com.pistachio.system.dto.vo.SysDictAndItemVo;
import com.pistachio.system.dto.vo.SysDictItemVo;
import com.pistachio.system.entity.SysDictEntity;
import com.pistachio.system.entity.SysDictItemEntity;
import com.pistachio.system.repository.SysDictItemRepository;
import com.pistachio.system.repository.SysDictRepository;
import com.pistachio.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/04 15:53
 * @description: 系统字典服务
 */
@Service
public class SysDictServiceImpl implements ISysDictService {

    @Autowired
    private SysDictRepository sysDictRepository;

    @Autowired
    private SysDictItemRepository sysDictItemRepository;

    @Transactional
    @Override
    public Page<SysDictEntity> lists(DictListRequest request) {
        // 构造分页参数
        Pageable pageable = PageRequest.of(request.getCurrent(), request.getSize(), Sort.by("id").descending());

        return sysDictRepository.findAll((Specification<SysDictEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtil.isEmpty(request.getType())) {
                predicateList.add(criteriaBuilder.like(root.get("type"), "%" + request.getType() + "%"));
            }

            if (StringUtil.isNotNull(request.getSystem())) {
                predicateList.add(criteriaBuilder.equal(root.get("system"), request.getSystem()));
            }

            return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
        }, pageable);
    }

    @Override
    public SysDictAndItemVo findDictAndItem(String key) {
        SysDictEntity sysDict = sysDictRepository.findFirstByType(key).orElseThrow(() -> new ServiceException("不存在数据"));

        SysDictAndItemVo vo = new SysDictAndItemVo();
        vo.setId(sysDict.getId());
        vo.setName(sysDict.getDescription());
        vo.setType(sysDict.getType());

        List<SysDictItemVo> sysDictItemVos = new ArrayList<>();

        sysDict.getItems().forEach(item -> {
            SysDictItemVo sysDictItemVo = new SysDictItemVo();
            sysDictItemVo.setLabel(item.getLabel());
            sysDictItemVo.setValue(item.getValue());

            sysDictItemVos.add(sysDictItemVo);
        });

        vo.setItems(sysDictItemVos);

        return vo;
    }

    @Override
    public SysDictEntity save(SysDictEntity sysDictEntity) {
        return sysDictRepository.save(sysDictEntity);
    }

    @Override
    public SysDictEntity save(DictCreateRequest request) {
        SysDictEntity entity = ConvertCore.map(request, SysDictEntity.class);
        return save(entity);
    }

    @Override
    public SysDictEntity edit(DictEditRequest request) {
        SysDictEntity entity = findById(request.getId());
        entity.setDescription(request.getDescription());
        entity.setSystem(request.getSystem().toString());
        entity.setType(request.getType());
        entity.setRemarks(request.getRemarks());

        return save(entity);
    }

    @Transactional
    @Override
    public void softDelete(Long id) {
        if (sysDictRepository.findById(id).orElse(null) != null) {
            sysDictRepository.softDelete(id);
        }
    }

    @Override
    public SysDictEntity findById(Long id) {
        return sysDictRepository.findById(id).orElseThrow(() -> new ServiceException("数据不存在"));
    }

    @Override
    public SysDictItemEntity save(SysDictItemEntity entity) {
        return sysDictItemRepository.save(entity);
    }

    @Override
    public SysDictItemEntity save(DictItemCreateRequest request) {
        SysDictItemEntity entity = ConvertCore.map(request, SysDictItemEntity.class);
        return save(entity);
    }

    @Override
    public SysDictItemEntity edit(DictItemEditRequest request) {
        SysDictItemEntity entity = getDictItemInfo(request.getId());
        entity.setType(request.getType());
        entity.setDictId(request.getDictId());
        entity.setValue(request.getValue());
        entity.setLabel(request.getLabel());
        entity.setDescription(request.getDescription());
        entity.setSort(request.getSort());
        entity.setRemarks(request.getRemarks());

        return save(entity);
    }

    @Transactional
    @Override
    public void softDeleteItem(Long id) {
        if (sysDictItemRepository.findById(id).orElse(null) != null) {
            sysDictItemRepository.softDelete(id);
        }
    }

    @Override
    public Page<SysDictItemEntity> selectByDictId(Long dictId) {
        // 默认设置100条数据，字典项也不会太多完全足够
        Pageable pageable = PageRequest.of(0, 100, Sort.by("sort").descending());
        return sysDictItemRepository.findByDictId(dictId, pageable);
    }

    @Override
    public SysDictItemEntity getDictItemInfo(Long id) {
        return sysDictItemRepository.findById(id).orElseThrow(() -> new ServiceException("数据不存在"));
    }
}
