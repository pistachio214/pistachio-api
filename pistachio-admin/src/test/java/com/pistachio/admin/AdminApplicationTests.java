package com.pistachio.admin;

//import cn.dev33.satoken.secure.SaSecureUtil;
//import com.pistachio.framework.security.handle.SysLoginHandle;
import com.pistachio.system.entity.SysDictEntity;
import com.pistachio.system.entity.SysDictItemEntity;
import com.pistachio.system.service.ISysDictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Pengsy
 * @date: 2023/08/04 14:20
 * @description: 测试案例
 */
@SpringBootTest
public class AdminApplicationTests {

//    @Autowired
//    private SysLoginHandle sysLoginHandle;

    @Value("${sa-token.private-key}")
    private String privateKey;

    @Autowired
    private ISysDictService iSysDictService;

    @Test
    void testVerifyPassword() {

//        String str = "12345678";
//        String encrypt = sysLoginHandle.rsaEncryptByPublic(str);
//
//        if (!SaSecureUtil.rsaDecryptByPrivate(privateKey, encrypt).equals(str)) {
//            System.out.println("密码不正确");
//        } else {
//            System.out.println("密码正确");
//        }

    }

    @Test
    void generatePublicKeyAndPrivateKey() {
//        try {
//            System.out.println(SaSecureUtil.rsaGenerateKeyPair());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @Test
    void testSoftDelete() {
        Long id = 1688794906526482433L;

        SysDictItemEntity sysDictItemEntity = new SysDictItemEntity();
        sysDictItemEntity.setDictId(id);
        sysDictItemEntity.setValue("2");
        sysDictItemEntity.setLabel("业务类");
        sysDictItemEntity.setType("dicts_type");
        sysDictItemEntity.setDescription("表示系统内部字典");
        sysDictItemEntity.setRemarks("系统内部字典");
        sysDictItemEntity.setSort(1);



//        SysDictEntity entity = iSysDictService.findById(id);
//        System.out.println(entity);
        iSysDictService.save(sysDictItemEntity);
    }

    @Test
    void addSysDict() {
        SysDictEntity sysDictEntity = new SysDictEntity();
        sysDictEntity.setType("dicts_type");
        sysDictEntity.setDescription(null);
        sysDictEntity.setRemarks(null);
        sysDictEntity.setSystem("1");

        iSysDictService.save(sysDictEntity);

    }

    @Test
    void testSqlResultSetMapping() {

    }
}
