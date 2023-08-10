package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.system.dto.req.DictItemCreateRequest;
import com.pistachio.system.dto.req.DictItemEditRequest;
import com.pistachio.system.entity.SysDictItemEntity;
import com.pistachio.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description: dict item controller
 * @date: 2023/08/09 10:57
 * @author: Pengsy
 */
@RestController
@RequestMapping("/sys-dict-item")
public class SysDictItemController {

    @Autowired
    private ISysDictService iSysDictService;

    @GetMapping("/list/{id}")
    @SaCheckPermission("sys:dict:item:list")
    public R<Page<SysDictItemEntity>> list(@PathVariable("id") Long id) {
        return R.success(iSysDictService.selectByDictId(id));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:dict:item:list")
    public R<SysDictItemEntity> info(@PathVariable("id") Long id) {
        return R.success(iSysDictService.getDictItemInfo(id));
    }

    @OperLog(operModul = "字典项模块 - 新增字典项", operType = OperationLogConst.SAVE, operDesc = "新增字典项")
    @PostMapping("/save")
    @SaCheckPermission("sys:dict:item:save")
    public R<SysDictItemEntity> save(@Validated @RequestBody DictItemCreateRequest request) {
        return R.success(iSysDictService.save(request));
    }

    @OperLog(operModul = "字典项模块 - 更新字典项", operType = OperationLogConst.EDIT, operDesc = "更新字典项")
    @PutMapping("/edit")
    @SaCheckPermission("sys:dict:item:edit")
    public R<SysDictItemEntity> edit(@Validated @RequestBody DictItemEditRequest request) {
        return R.success(iSysDictService.edit(request));
    }

    @OperLog(operModul = "字典项模块 - 删除字典项", operType = OperationLogConst.DELETE, operDesc = "删除字典项")
    @DeleteMapping("/{id}")
    @SaCheckPermission("sys:dict:item:delete")
    public R<Object> delete(@PathVariable("id") Long id) {
        iSysDictService.softDeleteItem(id);
        return R.success();
    }

}
