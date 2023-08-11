package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.system.dto.req.DictCreateRequest;
import com.pistachio.system.dto.req.DictEditRequest;
import com.pistachio.system.dto.req.DictListRequest;
import com.pistachio.system.dto.vo.SysDictAndItemVo;
import com.pistachio.system.entity.SysDictEntity;
import com.pistachio.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统数据字典
 *
 * @author: Pengsy
 * @date: 2023/08/04 15:52
 * @description: 系统数据字典模块
 */
@RestController
@RequestMapping("/sys-dict")
public class SysDictController {

    @Autowired
    private ISysDictService iSysDictService;

    /**
     * 数据字典列表
     *
     * @apiNote 权限 sys:dict:list
     */
    @GetMapping("/list")
    @SaCheckPermission("sys:dict:list")
    public R<Page<SysDictEntity>> list(DictListRequest request) {
        return R.success(iSysDictService.lists(request));
    }

    /**
     * 字典详情
     *
     * @param id 字典ID
     * @apiNote 权限 sys:dict:list
     */
    @GetMapping("/{id}")
    @SaCheckPermission("sys:dict:list")
    public R<SysDictEntity> info(@PathVariable("id") Long id) {
        return R.success(iSysDictService.findById(id));
    }

    /**
     * 添加字典
     *
     * @param request 字典信息传输对象
     * @apiNote 权限 sys:dict:save
     */
    @OperLog(operModul = "字典模块 - 添加字典", operType = OperationLogConst.SAVE, operDesc = "添加字典")
    @PostMapping("/save")
    @SaCheckPermission("sys:dict:save")
    public R<SysDictEntity> save(@Validated @RequestBody DictCreateRequest request) {
        return R.success(iSysDictService.save(request));
    }

    /**
     * 更新字典
     *
     * @param request 字典信息传输对象
     * @apiNote 权限 sys:dict:edit
     */
    @OperLog(operModul = "字典模块 - 更新字典", operType = OperationLogConst.EDIT, operDesc = "更新字典")
    @PutMapping("/edit")
    @SaCheckPermission("sys:dict:edit")
    public R<SysDictEntity> edit(@Validated @RequestBody DictEditRequest request) {
        return R.success(iSysDictService.edit(request));
    }

    /**
     * 删除字典
     *
     * @param id 字典ID
     * @apiNote 权限 sys:dict:delete
     */
    @OperLog(operModul = "字典模块 - 删除字典", operType = OperationLogConst.DELETE, operDesc = "删除字典")
    @DeleteMapping("/{id}")
    @SaCheckPermission("sys:dict:delete")
    public R<Object> delete(@PathVariable("id") Long id) {
        iSysDictService.softDelete(id);
        return R.success();
    }

    /**
     * 根据key获取字典标准数据
     *
     * @param key 字典key
     * @apiNote 需要登录权限
     */
    @GetMapping("/findByKey/{key}")
    public R<SysDictAndItemVo> findByKey(@PathVariable("key") String key) {
        return R.success(iSysDictService.findDictAndItem(key));
    }

}
