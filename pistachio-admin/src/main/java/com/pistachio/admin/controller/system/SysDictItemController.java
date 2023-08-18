package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.system.dto.req.DictItemCreateRequest;
import com.pistachio.system.dto.req.DictItemEditRequest;
import com.pistachio.system.entity.SysDictItemEntity;
import com.pistachio.system.service.ISysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 字典项目模块
 *
 * @description: dict item controller
 * @date: 2023/08/09 10:57
 * @author: Pengsy
 */
@ApiSupport(author = "Pengsy")
@Tag(name = "数据字典模块")
@RestController
@RequestMapping("/sys-dict-item")
public class SysDictItemController {

    @Autowired
    private ISysDictService iSysDictService;

    @Operation(summary = "字典项 - 列表", description = "权限 [ sys:dict:item:list ]; 根据字典id，获取字典项的数据")
    @Parameter(name = "id", description = "字典id", required = true)
    @GetMapping("/list/{id}")
    @SaCheckPermission("sys:dict:item:list")
    public R<Page<SysDictItemEntity>> list(@PathVariable("id") Long id) {
        return R.success(iSysDictService.selectByDictId(id));
    }

    @Operation(summary = "字典项 - 详情", description = "权限 [ sys:dict:item:list ]; 根据字典项id，获取单个字典项详情")
    @Parameter(name = "id", description = "字典项id", required = true)
    @GetMapping("/{id}")
    @SaCheckPermission("sys:dict:item:list")
    public R<SysDictItemEntity> info(@PathVariable("id") Long id) {
        return R.success(iSysDictService.getDictItemInfo(id));
    }

    @Operation(summary = "字典项 - 新增", description = "权限 [ sys:dict:item:save ]")
    @OperLog(operModul = "字典模块 - 新增字典项", operType = OperationLogConst.SAVE, operDesc = "新增字典项")
    @PostMapping("/save")
    @SaCheckPermission("sys:dict:item:save")
    public R<SysDictItemEntity> save(@Validated @RequestBody DictItemCreateRequest request) {
        return R.success(iSysDictService.save(request));
    }

    @Operation(summary = "字典项 - 更新", description = "权限 [ sys:dict:item:edit ]")
    @OperLog(operModul = "字典模块 - 更新字典项", operType = OperationLogConst.EDIT, operDesc = "更新字典项")
    @PutMapping("/edit")
    @SaCheckPermission("sys:dict:item:edit")
    public R<SysDictItemEntity> edit(@Validated @RequestBody DictItemEditRequest request) {
        return R.success(iSysDictService.edit(request));
    }

    @Operation(summary = "字典项 - 删除", description = "权限 [ sys:dict:item:delete ]")
    @Parameter(name = "id", description = "字典项id", required = true)
    @OperLog(operModul = "字典模块 - 删除字典项", operType = OperationLogConst.DELETE, operDesc = "删除字典项")
    @DeleteMapping("/{id}")
    @SaCheckPermission("sys:dict:item:delete")
    public R<Object> delete(@PathVariable("id") Long id) {
        iSysDictService.softDeleteItem(id);
        return R.success();
    }

}
