package com.pistachio.admin.controller.system;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.system.dto.req.DictCreateRequest;
import com.pistachio.system.dto.req.DictEditRequest;
import com.pistachio.system.dto.req.DictListRequest;
import com.pistachio.system.dto.vo.SysDictAndItemVo;
import com.pistachio.system.entity.SysDictEntity;
import com.pistachio.system.service.ISysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@ApiSupport(author = "Pengsy")
@Tag(name = "数据字典模块")
@RestController
@RequestMapping("/developer-dict")
public class SysDictController {

    @Autowired
    private ISysDictService iSysDictService;

    @Operation(summary = "字典 - 列表", description = "权限 [ developer:dict:list ]")
    @GetMapping("/list")
    public R<Page<SysDictEntity>> list(DictListRequest request) {
        return R.success(iSysDictService.lists(request));
    }

    @Operation(summary = "字典 - 详情", description = "权限 [ developer:dict:list ]")
    @Parameter(name = "id", description = "字典id", required = true)
    @GetMapping("/{id}")
    public R<SysDictEntity> info(@PathVariable("id") Long id) {
        return R.success(iSysDictService.findById(id));
    }

    @Operation(summary = "字典 - 添加", description = "权限 [ developer:dict:save ]")
    @OperLog(operModul = "字典模块 - 添加字典", operType = OperationLogConst.SAVE, operDesc = "添加字典")
    @PostMapping("/save")
    public R<SysDictEntity> save(@Validated @RequestBody DictCreateRequest request) {
        return R.success(iSysDictService.save(request));
    }

    @Operation(summary = "字典 - 更新", description = "权限 [ developer:dict:edit ]")
    @OperLog(operModul = "字典模块 - 更新字典", operType = OperationLogConst.EDIT, operDesc = "更新字典")
    @PutMapping("/edit")
    public R<SysDictEntity> edit(@Validated @RequestBody DictEditRequest request) {
        return R.success(iSysDictService.edit(request));
    }

    @Operation(summary = "字典 - 删除", description = "权限 [ developer:dict:delete ]")
    @Parameter(name = "id", description = "字典id", required = true)
    @OperLog(operModul = "字典模块 - 删除字典", operType = OperationLogConst.DELETE, operDesc = "删除字典")
    @DeleteMapping("/{id}")
    public R<Object> delete(@PathVariable("id") Long id) {
        iSysDictService.softDelete(id);
        return R.success();
    }

    @Operation(summary = "字典 - 根据key获取字典标准数据", description = "权限 [ developer:dict:find:key ]")
    @Parameter(name = "key", description = "数据的type", required = true)
    @GetMapping("/findByKey/{key}")
    public R<SysDictAndItemVo> findByKey(@PathVariable("key") String key) {
        return R.success(iSysDictService.findDictAndItem(key));
    }

}
