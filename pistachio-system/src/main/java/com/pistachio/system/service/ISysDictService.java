package com.pistachio.system.service;

import com.pistachio.system.dto.req.*;
import com.pistachio.system.dto.vo.SysDictAndItemVo;
import com.pistachio.system.entity.SysDictEntity;
import com.pistachio.system.entity.SysDictItemEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/04 15:53
 * @description: ISysDictService
 */
public interface ISysDictService {

    Page<SysDictEntity> lists(DictListRequest request);

    SysDictAndItemVo findDictAndItem(String key);

    SysDictEntity save(SysDictEntity sysDictEntity);

    SysDictEntity save(DictCreateRequest request);

    SysDictEntity edit(DictEditRequest request);

    void softDelete(Long id);

    SysDictEntity findById(Long id);

    SysDictItemEntity save(SysDictItemEntity entity);

    SysDictItemEntity save(DictItemCreateRequest request);

    SysDictItemEntity edit(DictItemEditRequest request);

    void softDeleteItem(Long id);

    Page<SysDictItemEntity> selectByDictId(Long dictId);

    SysDictItemEntity getDictItemInfo(Long id);
}
