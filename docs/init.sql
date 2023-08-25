SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`          bigint(20)   NOT NULL COMMENT '编号',
    `type`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `remarks`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `system`      char(1) CHARACTER SET utf8 COLLATE utf8_general_ci      DEFAULT '0',
    `created_at`  timestamp(0) NOT NULL                                   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  timestamp(0) NOT NULL                                   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `is_delete`   int(11)                                                 DEFAULT 1,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '字典表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict`
VALUES (1484154771923578881, 'sex_state', '性别', '性别 1男 0女 2未知', '1', '2022-01-20 21:24:07',
        '2022-01-20 21:24:07', 1);
INSERT INTO `sys_dict`
VALUES (1688794906526482433, 'dicts_type', '类型', '表示字典的分类 1系统类', '1', '2023-08-08 14:10:56',
        '2023-08-08 17:48:44', 1);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`
(
    `id`          bigint(20)   NOT NULL COMMENT '编号',
    `dict_id`     bigint(20)   NOT NULL,
    `value`       varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `label`       varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `type`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `remarks`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `sort`        int(11)      NOT NULL                                   DEFAULT 0 COMMENT '排序（升序）',
    `created_at`  timestamp(0) NOT NULL                                   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  timestamp(0) NOT NULL                                   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `is_delete`   int(11)                                                 DEFAULT 1,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '字典项'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item`
VALUES (1484187904907722754, 1484154771923578881, '1', '男', 'sex_state', '表示男性', NULL, 1, '2022-01-20 23:35:46',
        '2022-01-20 23:35:46', 1);
INSERT INTO `sys_dict_item`
VALUES (1484187995441774593, 1484154771923578881, '0', '女', 'sex_state', '表示女性', '', 2, '2022-01-20 23:36:08',
        '2022-01-20 23:59:35', 1);
INSERT INTO `sys_dict_item`
VALUES (1484188143530065922, 1484154771923578881, '2', '未知', 'sex_state', '表示没有输入性别', '没有填入性别选项', 3,
        '2022-01-20 23:36:43', '2022-01-20 23:59:40', 1);
INSERT INTO `sys_dict_item`
VALUES (1688849156531027970, 1688794906526482433, '1', '系统类', 'dicts_type', '表示系统内部字典', '系统内部字典', 1,
        '2023-08-08 17:46:30', '2023-08-08 17:46:30', 1);
INSERT INTO `sys_dict_item`
VALUES (1689875058614337538, 1688794906526482433, '2', '业务类', 'dicts_type', '表示系统内部字典', '系统内部字典', 1,
        '2023-08-11 13:43:05', '2023-08-11 13:43:05', 1);

-- ----------------------------
-- Table structure for sys_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_log`;
CREATE TABLE `sys_exception_log`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT,
    `oper_requ_method` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '请求方式',
    `exc_requ_param`   text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求参数',
    `exc_name`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '异常名称',
    `exc_message`      text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '异常信息',
    `oper_user_id`     bigint(20)                                              DEFAULT NULL COMMENT '操作者',
    `oper_user_name`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '操作员名称',
    `oper_method`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作方法',
    `oper_uri`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求URL',
    `oper_ip`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '请求IP',
    `oper_ver`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '操作版号',
    `created_at`       timestamp(0) NOT NULL                                   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`       timestamp(0) NOT NULL                                   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `is_delete`        int(11)                                                 DEFAULT 1 COMMENT '删除标识',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1689562631293108227
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '异常日志表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_exception_log
-- ----------------------------
INSERT INTO `sys_exception_log`
VALUES (1689194410589290497, 'GET', '{}', 'org.springframework.data.mapping.PropertyReferenceException',
        'org.springframework.data.mapping.PropertyReferenceException:No property \'created\' found for type \'SysUserEntity\' Did you mean \'\'createdAt\'\'\n	org.springframework.data.mapping.PropertyPath.<init>(PropertyPath.java:91)\norg.springframework.data.mapping.PropertyPath.create(PropertyPath.java:438)\norg.springframework.data.mapping.PropertyPath.create(PropertyPath.java:414)\norg.springframework.data.mapping.PropertyPath.lambda$from$0(PropertyPath.java:367)\njava.util.concurrent.ConcurrentMap.computeIfAbsent(ConcurrentMap.java:324)\norg.springframework.data.mapping.PropertyPath.from(PropertyPath.java:349)\norg.springframework.data.mapping.PropertyPath.from(PropertyPath.java:332)\norg.springframework.data.jpa.repository.query.QueryUtils.toJpaOrder(QueryUtils.java:719)\norg.springframework.data.jpa.repository.query.QueryUtils.toOrders(QueryUtils.java:672)\norg.springframework.data.jpa.repository.support.SimpleJpaRepository.getQuery(SimpleJpaRepository.java:798)\norg.springframework.data.jpa.repository.support.SimpleJpaRepository.getQuery(SimpleJpaRepository.java:755)\norg.springframework.data.jpa.repository.support.SimpleJpaRepository.findAll(SimpleJpaRepository.java:506)\nsun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\nsun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\nsun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\njava.lang.reflect.Method.invoke(Method.java:498)\norg.springframework.data.repository.core.support.RepositoryMethodInvoker$RepositoryFragmentMethodInvoker.lambda$new$0(RepositoryMethodInvoker.java:289)\norg.springframework.data.repository.core.support.RepositoryMethodInvoker.doInvoke(RepositoryMethodInvoker.java:137)\norg.springframework.data.repository.core.support.RepositoryMethodInvoker.invoke(RepositoryMethodInvoker.java:121)\norg.springframework.data.repository.core.support.RepositoryComposition$RepositoryFragments.invoke(RepositoryComposition.java:530)\norg.springframework.data.repository.core.support.RepositoryComposition.invoke(RepositoryComposition.java:286)\norg.springframework.data.repository.core.support.RepositoryFactorySupport$ImplementationMethodExecutionInterceptor.invoke(RepositoryFactorySupport.java:640)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:164)\norg.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:139)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:81)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)\norg.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:388)\norg.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:174)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:215)\ncom.sun.proxy.$Proxy140.findAll(Unknown Source)\ncom.pistachio.system.service.impl.SysUserServiceImpl.selectUserPage(SysUserServiceImpl.java:76)\ncom.pistachio.admin.controller.system.SysUserController.list(SysUserController.java:29)\ncom.pistachio.admin.controller.system.SysUserController$$FastClassBySpringCGLIB$$feff623f.invoke(<generated>)\norg.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:64)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708)\ncom.pistachio.admin.controller.system.SysUserController$$EnhancerBySpringCGLIB$$96555337.list(<generated>)\nsun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\nsun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\nsun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\njava.lang.reflect.Method.invoke(Method.java:498)\norg.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\norg.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\norg.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\norg.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\norg.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1070)\norg.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)\norg.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\norg.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:655)\norg.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:764)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\ncom.pistachio.common.filter.RepeatableFilter.doFilter(RepeatableFilter.java:28)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\norg.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\norg.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\norg.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\norg.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\norg.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\norg.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)\norg.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)\norg.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\norg.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:890)\norg.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1789)\norg.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\norg.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\norg.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\norg.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\njava.lang.Thread.run(Thread.java:748)\n',
        1, 'admin', 'com.pistachio.admin.controller.system.SysUserController.list', '/sys-user/list', '127.0.0.1',
        '0.0.1', '2023-08-09 16:38:25', '2023-08-09 16:38:25', 1);
INSERT INTO `sys_exception_log`
VALUES (1689212305503420417, 'POST', '[\"1\",\"3\"]', 'org.springframework.dao.DataIntegrityViolationException',
        'org.springframework.dao.DataIntegrityViolationException:could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement\n	org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:276)\norg.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:233)\norg.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:566)\norg.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:743)\norg.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:711)\norg.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:654)\norg.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:407)\norg.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:174)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:215)\ncom.sun.proxy.$Proxy152.saveAll(Unknown Source)\ncom.pistachio.system.service.impl.SysRoleServiceImpl.rolePerm(SysRoleServiceImpl.java:77)\ncom.pistachio.admin.controller.system.SysUserController.rolePerm(SysUserController.java:40)\ncom.pistachio.admin.controller.system.SysUserController$$FastClassBySpringCGLIB$$feff623f.invoke(<generated>)\norg.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:64)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:57)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708)\ncom.pistachio.admin.controller.system.SysUserController$$EnhancerBySpringCGLIB$$1db9267a.rolePerm(<generated>)\nsun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\nsun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\nsun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\njava.lang.reflect.Method.invoke(Method.java:498)\norg.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\norg.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\norg.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\norg.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\norg.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1070)\norg.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)\norg.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\norg.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:681)\norg.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:764)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\ncom.pistachio.common.filter.RepeatableFilter.doFilter(RepeatableFilter.java:30)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\norg.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\norg.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\norg.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\norg.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\norg.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\norg.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)\norg.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)\norg.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\norg.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:890)\norg.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1789)\norg.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\norg.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\norg.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\norg.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\njava.lang.Thread.run(Thread.java:748)\n',
        1, 'admin', 'com.pistachio.admin.controller.system.SysUserController.rolePerm', '/sys-user/role/1', '127.0.0.1',
        '0.0.1', '2023-08-09 17:49:32', '2023-08-09 17:49:32', 1);
INSERT INTO `sys_exception_log`
VALUES (1689213851570012161, 'POST', '[\"1\",\"3\"]', 'org.springframework.dao.DataIntegrityViolationException',
        'org.springframework.dao.DataIntegrityViolationException:could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement\n	org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:276)\norg.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:233)\norg.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:566)\norg.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:743)\norg.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:711)\norg.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:654)\norg.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:407)\norg.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:174)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:215)\ncom.sun.proxy.$Proxy155.saveAll(Unknown Source)\ncom.pistachio.system.service.impl.SysRoleServiceImpl.rolePerm(SysRoleServiceImpl.java:77)\ncom.pistachio.admin.controller.system.SysUserController.rolePerm(SysUserController.java:40)\ncom.pistachio.admin.controller.system.SysUserController$$FastClassBySpringCGLIB$$feff623f.invoke(<generated>)\norg.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:64)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:57)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708)\ncom.pistachio.admin.controller.system.SysUserController$$EnhancerBySpringCGLIB$$65b3544a.rolePerm(<generated>)\nsun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\nsun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\nsun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\njava.lang.reflect.Method.invoke(Method.java:498)\norg.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\norg.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\norg.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\norg.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\norg.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1070)\norg.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)\norg.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\norg.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:681)\norg.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:764)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\ncom.pistachio.common.filter.RepeatableFilter.doFilter(RepeatableFilter.java:30)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\norg.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\norg.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\norg.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\norg.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\norg.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\norg.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)\norg.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)\norg.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\norg.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:890)\norg.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1789)\norg.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\norg.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\norg.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\norg.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\njava.lang.Thread.run(Thread.java:748)\n',
        1, 'admin', 'com.pistachio.admin.controller.system.SysUserController.rolePerm', '/sys-user/role/1', '127.0.0.1',
        '0.0.1', '2023-08-09 17:55:40', '2023-08-09 17:55:40', 1);
INSERT INTO `sys_exception_log`
VALUES (1689541830347063297, 'POST',
        '{\"username\":\"test\",\"nickname\":\"测试管理员\",\"email\":\"test@qq.com\",\"status\":1}',
        'org.springframework.dao.DataIntegrityViolationException',
        'org.springframework.dao.DataIntegrityViolationException:could not execute statement; SQL [n/a]; constraint [UK_USERNAME]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement\n	org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:276)\norg.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:233)\norg.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:566)\norg.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:743)\norg.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:711)\norg.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:654)\norg.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:407)\norg.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:174)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:215)\ncom.sun.proxy.$Proxy148.save(Unknown Source)\ncom.pistachio.system.service.impl.SysUserServiceImpl.save(SysUserServiceImpl.java:160)\ncom.pistachio.system.service.impl.SysUserServiceImpl.save(SysUserServiceImpl.java:156)\ncom.pistachio.system.service.impl.SysUserServiceImpl$$FastClassBySpringCGLIB$$547e05bc.invoke(<generated>)\norg.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\norg.springframework.aop.framework.CglibAopProxy.invokeMethod(CglibAopProxy.java:386)\norg.springframework.aop.framework.CglibAopProxy.access$000(CglibAopProxy.java:85)\norg.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:704)\ncom.pistachio.system.service.impl.SysUserServiceImpl$$EnhancerBySpringCGLIB$$c7e5bf71.save(<generated>)\ncom.pistachio.admin.controller.system.SysUserController.save(SysUserController.java:64)\ncom.pistachio.admin.controller.system.SysUserController$$FastClassBySpringCGLIB$$feff623f.invoke(<generated>)\norg.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:64)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:57)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708)\ncom.pistachio.admin.controller.system.SysUserController$$EnhancerBySpringCGLIB$$3c01ad63.save(<generated>)\nsun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\nsun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\nsun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\njava.lang.reflect.Method.invoke(Method.java:498)\norg.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\norg.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\norg.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\norg.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\norg.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1070)\norg.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)\norg.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\norg.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:681)\norg.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:764)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\ncom.pistachio.common.filter.RepeatableFilter.doFilter(RepeatableFilter.java:30)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\norg.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\norg.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\norg.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\norg.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\norg.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\norg.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)\norg.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)\norg.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\norg.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:890)\norg.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1789)\norg.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\norg.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\norg.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\norg.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\njava.lang.Thread.run(Thread.java:748)\n',
        1, 'admin', 'com.pistachio.admin.controller.system.SysUserController.save', '/sys-user/save', '127.0.0.1',
        '0.0.1', '2023-08-10 15:38:57', '2023-08-10 15:38:57', 1);
INSERT INTO `sys_exception_log`
VALUES (1689562631293108226, 'PUT',
        '{\"id\":\"1689562445363806210\",\"name\":\"ccc\",\"code\":\"2222\",\"status\":1,\"remark\":\"33333\"}',
        'org.springframework.dao.DataIntegrityViolationException',
        'org.springframework.dao.DataIntegrityViolationException:could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement\n	org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:276)\norg.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:233)\norg.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:566)\norg.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:743)\norg.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:711)\norg.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:654)\norg.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:407)\norg.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:174)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:215)\ncom.sun.proxy.$Proxy158.save(Unknown Source)\ncom.pistachio.system.service.impl.SysRoleServiceImpl.save(SysRoleServiceImpl.java:127)\ncom.pistachio.system.service.impl.SysRoleServiceImpl.update(SysRoleServiceImpl.java:118)\ncom.pistachio.system.service.impl.SysRoleServiceImpl$$FastClassBySpringCGLIB$$e1046831.invoke(<generated>)\norg.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\norg.springframework.aop.framework.CglibAopProxy.invokeMethod(CglibAopProxy.java:386)\norg.springframework.aop.framework.CglibAopProxy.access$000(CglibAopProxy.java:85)\norg.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:704)\ncom.pistachio.system.service.impl.SysRoleServiceImpl$$EnhancerBySpringCGLIB$$63eacefd.update(<generated>)\ncom.pistachio.admin.controller.system.SysRoleController.update(SysRoleController.java:66)\ncom.pistachio.admin.controller.system.SysRoleController$$FastClassBySpringCGLIB$$45985d2a.invoke(<generated>)\norg.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:64)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:57)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708)\ncom.pistachio.admin.controller.system.SysRoleController$$EnhancerBySpringCGLIB$$c4b0eb5c.update(<generated>)\nsun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\nsun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\nsun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\njava.lang.reflect.Method.invoke(Method.java:498)\norg.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\norg.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\norg.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\norg.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\norg.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1070)\norg.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)\norg.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\norg.springframework.web.servlet.FrameworkServlet.doPut(FrameworkServlet.java:920)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:684)\norg.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:764)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\ncom.pistachio.common.filter.RepeatableFilter.doFilter(RepeatableFilter.java:30)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\norg.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\norg.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\norg.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\norg.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\norg.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\norg.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\norg.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)\norg.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)\norg.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\norg.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:890)\norg.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1789)\norg.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\norg.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\norg.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\norg.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\njava.lang.Thread.run(Thread.java:748)\n',
        1, 'admin', 'com.pistachio.admin.controller.system.SysRoleController.update', '/sys-role/update', '127.0.0.1',
        '0.0.1', '2023-08-10 17:01:37', '2023-08-10 17:01:37', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`         bigint(20)                                             NOT NULL AUTO_INCREMENT,
    `parent_id`  bigint(20)                                              DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
    `name`       varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `path`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单URL',
    `perms`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
    `component`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `type`       int(11)                                                NOT NULL COMMENT '类型: 0目录;1菜单;2按钮',
    `icon`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '菜单图标',
    `order_num`  int(11)                                                 DEFAULT NULL COMMENT '排序',
    `created_at` datetime(0)                                            NOT NULL,
    `updated_at` datetime(0)                                             DEFAULT NULL,
    `status`     int(11)                                                NOT NULL,
    `is_delete`  int(11)                                                 DEFAULT 1 COMMENT '删除标识',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name` (`name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1694921182698012675
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统菜单表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, 0, '仪表盘', '/dashboard', 'sys:home', NULL, 0, 'DashboardOutlined', 1, '2021-10-06 23:46:52',
        '2023-08-11 11:01:20', 1, 1);
INSERT INTO `sys_menu`
VALUES (5, 0, '开发者工具', '/developer', 'sys:developer', NULL, 0, 'RadiusSettingOutlined', 2, '2021-01-15 19:06:11',
        '2023-08-25 11:42:19', 1, 1);
INSERT INTO `sys_menu`
VALUES (6, 5, '数字字典', '/developer-dict', 'developer:dict:list', NULL, 1, '', 1, '2021-01-15 19:07:18',
        '2023-08-25 11:53:34', 1, 1);
INSERT INTO `sys_menu`
VALUES (21, 0, '系统设置', '/system', 'sys:home', NULL, 0, 'SettingOutlined', 2, '2021-10-04 00:00:00',
        '2022-01-08 15:50:29', 1, 1);
INSERT INTO `sys_menu`
VALUES (22, 21, '人员管理', '/system/users', 'sys:user:list', 'sys/User', 1, '', 1, '2021-01-15 19:03:45',
        '2021-11-10 11:15:05', 1, 1);
INSERT INTO `sys_menu`
VALUES (23, 21, '角色管理', '/system/roles', 'sys:role:list', 'sys/Role', 1, '', 2, '2021-01-15 19:03:45',
        '2021-01-15 19:03:48', 1, 1);
INSERT INTO `sys_menu`
VALUES (24, 21, '菜单管理', '/system/menus', 'sys:menu:list', 'sys/Menu', 1, '', 3, '2021-01-15 19:03:45',
        '2021-01-15 19:03:48', 1, 1);
INSERT INTO `sys_menu`
VALUES (27, 23, '添加角色', '', 'sys:role:save', '', 2, '', 1, '2021-01-15 23:02:25', '2021-01-17 21:53:14', 0, 1);
INSERT INTO `sys_menu`
VALUES (29, 22, '添加用户', NULL, 'sys:user:save', NULL, 2, NULL, 1, '2021-01-17 21:48:32', NULL, 1, 1);
INSERT INTO `sys_menu`
VALUES (30, 22, '修改用户', NULL, 'sys:user:update', NULL, 2, NULL, 2, '2021-01-17 21:49:03', '2021-01-17 21:53:04', 1,
        1);
INSERT INTO `sys_menu`
VALUES (31, 22, '删除用户', NULL, 'sys:user:delete', NULL, 2, NULL, 3, '2021-01-17 21:49:21', NULL, 1, 1);
INSERT INTO `sys_menu`
VALUES (32, 23, '分配角色', NULL, 'sys:user:role', NULL, 2, NULL, 4, '2021-01-17 21:49:58', NULL, 1, 1);
INSERT INTO `sys_menu`
VALUES (33, 22, '重置密码', NULL, 'sys:user:repass', NULL, 2, NULL, 5, '2021-01-17 21:50:36', NULL, 1, 1);
INSERT INTO `sys_menu`
VALUES (34, 23, '修改角色', NULL, 'sys:role:update', NULL, 2, NULL, 2, '2021-01-17 21:51:14', NULL, 1, 1);
INSERT INTO `sys_menu`
VALUES (35, 23, '删除角色', NULL, 'sys:role:delete', NULL, 2, NULL, 3, '2021-01-17 21:51:39', NULL, 1, 1);
INSERT INTO `sys_menu`
VALUES (36, 23, '分配权限', NULL, 'sys:role:perm', NULL, 2, NULL, 5, '2021-01-17 21:52:02', NULL, 1, 1);
INSERT INTO `sys_menu`
VALUES (37, 24, '添加菜单', NULL, 'sys:menu:save', NULL, 2, NULL, 1, '2021-01-17 21:53:53', '2021-01-17 21:55:28', 1,
        1);
INSERT INTO `sys_menu`
VALUES (38, 24, '修改菜单', NULL, 'sys:menu:update', NULL, 2, NULL, 2, '2021-01-17 21:56:12', NULL, 1, 1);
INSERT INTO `sys_menu`
VALUES (39, 24, '删除菜单', NULL, 'sys:menu:delete', NULL, 2, NULL, 3, '2021-01-17 21:56:36', NULL, 1, 1);
INSERT INTO `sys_menu`
VALUES (40, 22, '修改头像', NULL, 'sys:user:save:avatar', NULL, 2, NULL, 4, '2022-01-08 23:26:01',
        '2022-01-08 23:26:14', 1, 1);
INSERT INTO `sys_menu`
VALUES (41, 6, '字典列表', NULL, 'developer:dict:list', NULL, 2, NULL, 1, '2022-01-19 21:35:58', '2023-08-25 11:45:14',
        1, 1);
INSERT INTO `sys_menu`
VALUES (42, 6, '编辑字典', NULL, 'developer:dict:edit', NULL, 2, NULL, 1, '2022-01-19 22:49:02', '2023-08-25 11:45:19',
        1, 1);
INSERT INTO `sys_menu`
VALUES (43, 6, '新增字典', NULL, 'developer:dict:save', NULL, 2, NULL, 1, '2022-01-19 22:49:26', '2023-08-25 11:45:24',
        1, 1);
INSERT INTO `sys_menu`
VALUES (44, 6, '删除字典', NULL, 'developer:dict:delete', NULL, 2, NULL, 1, '2022-01-19 22:54:57',
        '2023-08-25 11:45:29', 1, 1);
INSERT INTO `sys_menu`
VALUES (45, 6, '字典项列表', NULL, 'developer:dict:item:list', NULL, 2, NULL, 1, '2022-01-20 22:26:00',
        '2023-08-25 11:45:35', 1, 1);
INSERT INTO `sys_menu`
VALUES (46, 6, '新增数据字典项', NULL, 'developer:dict:item:save', NULL, 2, NULL, 1, '2022-01-20 23:32:30',
        '2023-08-25 11:45:40', 1, 1);
INSERT INTO `sys_menu`
VALUES (47, 6, '删除数据字典项', NULL, 'developer:dict:item:delete', NULL, 2, NULL, 1, '2022-01-20 23:42:49',
        '2023-08-25 11:45:48', 1, 1);
INSERT INTO `sys_menu`
VALUES (48, 6, '更新数据字典项', NULL, 'developer:dict:item:edit', NULL, 2, NULL, 1, '2022-01-20 23:47:07',
        '2023-08-25 11:45:54', 1, 1);
INSERT INTO `sys_menu`
VALUES (1694919344129048577, 5, '操作日志', '/developer/oper', 'developer:oper', NULL, 1, NULL, 1,
        '2023-08-25 11:47:16', '2023-08-25 11:47:57', 1, 1);
INSERT INTO `sys_menu`
VALUES (1694920155726872578, 1694919344129048577, '日志列表', NULL, 'developer:oper:log:list', NULL, 2, NULL, 1,
        '2023-08-25 11:50:30', '2023-08-25 11:50:30', 1, 1);
INSERT INTO `sys_menu`
VALUES (1694920242511216642, 1694919344129048577, '日志详情', NULL, 'developer:oper:log:info', NULL, 2, NULL, 1,
        '2023-08-25 11:50:51', '2023-08-25 11:50:51', 1, 1);
INSERT INTO `sys_menu`
VALUES (1694920467812450306, 5, '异常日志', '/developer-exception-log', 'developer:exception', NULL, 1, NULL, 1,
        '2023-08-25 11:51:44', '2023-08-25 11:51:44', 1, 1);
INSERT INTO `sys_menu`
VALUES (1694921070563295234, 1694920467812450306, '异常日志列表', NULL, 'developer:exception:log:list', NULL, 2, NULL,
        1, '2023-08-25 11:54:08', '2023-08-25 11:54:08', 1, 1);
INSERT INTO `sys_menu`
VALUES (1694921182698012674, 1694920467812450306, '异常日志详情', NULL, 'developer:exception:log:info', NULL, 2, NULL,
        1, '2023-08-25 11:54:35', '2023-08-25 11:54:35', 1, 1);

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT,
    `oper_modul`       varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '功能模块',
    `oper_type`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '操作类型',
    `oper_desc`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '操作描述',
    `oper_requ_method` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '请求方式',
    `oper_requ_param`  text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求参数',
    `oper_resp_param`  text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '返回参数',
    `oper_user_id`     bigint(20)                                              DEFAULT NULL COMMENT '操作者',
    `oper_user_name`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '操作员名称',
    `oper_method`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作方法',
    `oper_uri`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求URL',
    `oper_ip`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '请求IP',
    `oper_ver`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '操作版号',
    `created_at`       timestamp(0) NOT NULL                                   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`       timestamp(0) NOT NULL                                   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `is_delete`        int(11)                                                 DEFAULT 1 COMMENT '删除标识',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1694967307488460803
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '日志记录表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log`
VALUES (1689183353875464194, '字典模块 - 添加字典', '新增', '添加字典', 'POST',
        '{\"type\":\"5555\",\"description\":\"5555\",\"system\":\"1\",\"remarks\":\"55555\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"type\":\"5555\",\"description\":\"5555\",\"remarks\":\"55555\",\"system\":\"1\",\"id\":1689183353808355330,\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysDictController.save', '/sys-dict/save', '127.0.0.1',
        '0.0.1', '2023-08-09 15:54:29', '2023-08-09 15:54:29', 1);
INSERT INTO `sys_oper_log`
VALUES (1689184454838321154, '字典模块 - 删除字典', '删除', '删除字典', 'DELETE', NULL,
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysDictController.delete', '/sys-dict/1689183353808355330', '127.0.0.1',
        '0.0.1', '2023-08-09 15:58:52', '2023-08-09 15:58:52', 1);
INSERT INTO `sys_oper_log`
VALUES (1689184463864463362, '字典模块 - 删除字典', '删除', '删除字典', 'DELETE', NULL,
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysDictController.delete', '/sys-dict/1689182264769904642', '127.0.0.1',
        '0.0.1', '2023-08-09 15:58:54', '2023-08-09 15:58:54', 1);
INSERT INTO `sys_oper_log`
VALUES (1689184470554378241, '字典模块 - 删除字典', '删除', '删除字典', 'DELETE', NULL,
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysDictController.delete', '/sys-dict/1689181930345463810', '127.0.0.1',
        '0.0.1', '2023-08-09 15:58:55', '2023-08-09 15:58:55', 1);
INSERT INTO `sys_oper_log`
VALUES (1689184481820278786, '字典模块 - 删除字典', '删除', '删除字典', 'DELETE', NULL,
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysDictController.delete', '/sys-dict/1689181345189724162', '127.0.0.1',
        '0.0.1', '2023-08-09 15:58:58', '2023-08-09 15:58:58', 1);
INSERT INTO `sys_oper_log`
VALUES (1689184492448645122, '字典模块 - 删除字典', '删除', '删除字典', 'DELETE', NULL,
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysDictController.delete', '/sys-dict/1689180351693324290', '127.0.0.1',
        '0.0.1', '2023-08-09 15:59:01', '2023-08-09 15:59:01', 1);
INSERT INTO `sys_oper_log`
VALUES (1689214263639408641, '管理员模块 - 管理员设置角色', '编辑', '管理员设置角色', 'POST', '[\"1\",\"3\"]',
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysUserController.rolePerm', '/sys-user/role/1', '127.0.0.1', '0.0.1',
        '2023-08-09 17:57:19', '2023-08-09 17:57:19', 1);
INSERT INTO `sys_oper_log`
VALUES (1689532220395487233, '管理员模块 - 管理员设置角色', '编辑', '管理员设置角色', 'POST', '[\"3\",\"1\"]',
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysUserController.rolePerm', '/sys-user/role/3', '127.0.0.1', '0.0.1',
        '2023-08-10 15:00:46', '2023-08-10 15:00:46', 1);
INSERT INTO `sys_oper_log`
VALUES (1689532261365448705, '管理员模块 - 管理员设置角色', '编辑', '管理员设置角色', 'POST', '[\"3\"]',
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysUserController.rolePerm', '/sys-user/role/3', '127.0.0.1', '0.0.1',
        '2023-08-10 15:00:56', '2023-08-10 15:00:56', 1);
INSERT INTO `sys_oper_log`
VALUES (1689536938375118850, '管理员模块 - 重置管理员密码', '编辑', '重置管理员密码', 'POST', '3',
        '{\"code\":200,\"message\":\"success\",\"data\":\"12345678\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysUserController.repass', '/sys-user/repass', '127.0.0.1', '0.0.1',
        '2023-08-10 15:19:31', '2023-08-10 15:19:31', 1);
INSERT INTO `sys_oper_log`
VALUES (1689541716995997697, '管理员模块 - 删除管理员', '删除', '删除管理员', 'DELETE', NULL,
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysUserController.delete', '/sys-user/delete/3', '127.0.0.1', '0.0.1',
        '2023-08-10 15:38:30', '2023-08-10 15:38:30', 1);
INSERT INTO `sys_oper_log`
VALUES (1689541974681452545, '管理员模块 - 新增管理员', '新增', '新增管理员', 'POST',
        '{\"username\":\"test_user\",\"nickname\":\"测试管理员\",\"email\":\"test_user@qq.com\",\"status\":1}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"username\":\"test_user\",\"nickname\":\"测试管理员\",\"password\":\"0b02361c545e299b2a90ce35c67485e98c045e46ca75cf9913f78b1def7ede1af77908192c2ccc1878d1cefbfcf70bdbae3b96b1c164d61f97a3a9c56fcb8988cd63b9bc1253ba0e916bec1b5022c3333cc302a7c9ac91a24d3a1acc673afd2f8c4adacef8297f7421c45d0deadac2bd5314aeec289490492236ad9687455248\",\"avatar\":\"https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg\",\"email\":\"test_user@qq.com\",\"status\":1,\"sysRoles\":[],\"id\":1689541974618537986,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":10},\"time\":{\"hour\":15,\"minute\":39,\"second\":31,\"nano\":586000000}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":10},\"time\":{\"hour\":15,\"minute\":39,\"second\":31,\"nano\":586000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysUserController.save', '/sys-user/save', '127.0.0.1',
        '0.0.1', '2023-08-10 15:39:32', '2023-08-10 15:39:32', 1);
INSERT INTO `sys_oper_log`
VALUES (1689542023599620098, '管理员模块 - 管理员设置角色', '编辑', '管理员设置角色', 'POST', '[\"3\"]',
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysUserController.rolePerm', '/sys-user/role/1689541974618537986',
        '127.0.0.1', '0.0.1', '2023-08-10 15:39:43', '2023-08-10 15:39:43', 1);
INSERT INTO `sys_oper_log`
VALUES (1689557486685650945, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST',
        '[1,5,6,21,22,23,24,27,29,30,31,32,33,34,35,36,37,38,39,41,42,43,44,40,45,46,47,48]',
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/1', '127.0.0.1', '0.0.1',
        '2023-08-10 16:41:10', '2023-08-10 16:41:10', 1);
INSERT INTO `sys_oper_log`
VALUES (1689557531380154370, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST',
        '[1,5,6,41,42,43,44,45,46,47,48,24,37,38,39]', '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/3', '127.0.0.1', '0.0.1',
        '2023-08-10 16:41:21', '2023-08-10 16:41:21', 1);
INSERT INTO `sys_oper_log`
VALUES (1689557553236672514, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST',
        '[1,5,6,41,42,43,44,45,46,47,48]', '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/3', '127.0.0.1', '0.0.1',
        '2023-08-10 16:41:26', '2023-08-10 16:41:26', 1);
INSERT INTO `sys_oper_log`
VALUES (1689562445619658754, '角色模块 - 新增角色', '新增', '新增角色', 'POST',
        '{\"name\":\"ccc\",\"code\":\"11111\",\"status\":1,\"remark\":\"33333\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"name\":\"ccc\",\"code\":\"11111\",\"status\":1,\"remark\":\"33333\",\"id\":1689562445363806210,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":10},\"time\":{\"hour\":17,\"minute\":0,\"second\":52,\"nano\":178000000}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":10},\"time\":{\"hour\":17,\"minute\":0,\"second\":52,\"nano\":178000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysRoleController.save', '/sys-role/save', '127.0.0.1',
        '0.0.1', '2023-08-10 17:00:52', '2023-08-10 17:00:52', 1);
INSERT INTO `sys_oper_log`
VALUES (1689563312758456321, '角色模块 - 新增角色', '新增', '新增角色', 'POST',
        '{\"name\":\"222\",\"code\":\"333\",\"status\":1,\"remark\":\"4444\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"name\":\"222\",\"code\":\"333\",\"status\":1,\"remark\":\"4444\",\"id\":1689563312691347457,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":10},\"time\":{\"hour\":17,\"minute\":4,\"second\":18,\"nano\":980000000}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":10},\"time\":{\"hour\":17,\"minute\":4,\"second\":18,\"nano\":980000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysRoleController.save', '/sys-role/save', '127.0.0.1',
        '0.0.1', '2023-08-10 17:04:19', '2023-08-10 17:04:19', 1);
INSERT INTO `sys_oper_log`
VALUES (1689563348066107393, '角色模块 - 更新角色', '编辑', '更新角色', 'PUT',
        '{\"id\":\"1689563312691347457\",\"name\":\"222\",\"code\":\"333\",\"status\":1,\"remark\":\"44443333\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"name\":\"222\",\"code\":\"333\",\"status\":1,\"remark\":\"44443333\",\"id\":1689563312691347457,\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":10},\"time\":{\"hour\":17,\"minute\":4,\"second\":27,\"nano\":404000000}}}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysRoleController.update', '/sys-role/update', '127.0.0.1',
        '0.0.1', '2023-08-10 17:04:27', '2023-08-10 17:04:27', 1);
INSERT INTO `sys_oper_log`
VALUES (1689564575092965378, '角色模块 - 更新角色', '编辑', '更新角色', 'PUT',
        '{\"id\":\"1689563312691347457\",\"name\":\"222\",\"code\":\"333\",\"status\":1,\"remark\":\"444433333333111111\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"name\":\"222\",\"code\":\"333\",\"status\":1,\"remark\":\"444433333333111111\",\"id\":1689563312691347457,\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":10},\"time\":{\"hour\":17,\"minute\":9,\"second\":19,\"nano\":898000000}}}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysRoleController.update', '/sys-role/update', '127.0.0.1',
        '0.0.1', '2023-08-10 17:09:20', '2023-08-10 17:09:20', 1);
INSERT INTO `sys_oper_log`
VALUES (1689565092695244801, '角色模块 - 更新角色', '编辑', '更新角色', 'PUT',
        '{\"id\":\"1689563312691347457\",\"name\":\"222\",\"code\":\"333\",\"status\":1,\"remark\":\"13123456789\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"name\":\"222\",\"code\":\"333\",\"status\":1,\"remark\":\"13123456789\",\"id\":1689563312691347457,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":10},\"time\":{\"hour\":17,\"minute\":9,\"second\":19,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":10},\"time\":{\"hour\":17,\"minute\":11,\"second\":23,\"nano\":304000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysRoleController.update', '/sys-role/update', '127.0.0.1',
        '0.0.1', '2023-08-10 17:11:23', '2023-08-10 17:11:23', 1);
INSERT INTO `sys_oper_log`
VALUES (1689565174488367106, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST', '[24,37,38,39,1]',
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/1689563312691347457',
        '127.0.0.1', '0.0.1', '2023-08-10 17:11:43', '2023-08-10 17:11:43', 1);
INSERT INTO `sys_oper_log`
VALUES (1689566865564631042, '角色模块 - 删除角色', '删除', '删除角色', 'DELETE', NULL,
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.delete', '/sys-role/delete/1689563312691347457',
        '127.0.0.1', '0.0.1', '2023-08-10 17:18:26', '2023-08-10 17:18:26', 1);
INSERT INTO `sys_oper_log`
VALUES (1689834305896841217, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"1\",\"parentId\":0,\"name\":\"仪表盘\",\"perms\":\"sys:home\",\"type\":0,\"status\":1,\"orderNum\":10,\"path\":\"/dashboard\",\"icon\":\"DashboardOutlined\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":0,\"name\":\"仪表盘\",\"path\":\"/dashboard\",\"perms\":\"sys:home\",\"type\":0,\"icon\":\"DashboardOutlined\",\"orderNum\":10,\"status\":1,\"children\":[],\"id\":1,\"createdAt\":{\"date\":{\"year\":2021,\"month\":10,\"day\":6},\"time\":{\"hour\":23,\"minute\":46,\"second\":52,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":11},\"time\":{\"hour\":11,\"minute\":1,\"second\":8,\"nano\":650000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-11 11:01:09', '2023-08-11 11:01:09', 1);
INSERT INTO `sys_oper_log`
VALUES (1689834353825153025, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"1\",\"parentId\":0,\"name\":\"仪表盘\",\"perms\":\"sys:home\",\"type\":0,\"status\":1,\"orderNum\":1,\"path\":\"/dashboard\",\"icon\":\"DashboardOutlined\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":0,\"name\":\"仪表盘\",\"path\":\"/dashboard\",\"perms\":\"sys:home\",\"type\":0,\"icon\":\"DashboardOutlined\",\"orderNum\":1,\"status\":1,\"children\":[],\"id\":1,\"createdAt\":{\"date\":{\"year\":2021,\"month\":10,\"day\":6},\"time\":{\"hour\":23,\"minute\":46,\"second\":52,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":11},\"time\":{\"hour\":11,\"minute\":1,\"second\":20,\"nano\":216000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-11 11:01:20', '2023-08-11 11:01:20', 1);
INSERT INTO `sys_oper_log`
VALUES (1694618030543208449, '字典模块 - 删除字典', '删除', '删除字典', 'DELETE', NULL,
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysDictController.delete', '/sys-dict/1689903045476876289', '127.0.0.1',
        '0.0.1', '2023-08-24 15:49:58', '2023-08-24 15:49:58', 1);
INSERT INTO `sys_oper_log`
VALUES (1694618040211079170, '字典模块 - 删除字典', '删除', '删除字典', 'DELETE', NULL,
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysDictController.delete', '/sys-dict/1689875058983436290', '127.0.0.1',
        '0.0.1', '2023-08-24 15:50:00', '2023-08-24 15:50:00', 1);
INSERT INTO `sys_oper_log`
VALUES (1694917209408667649, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"5\",\"parentId\":0,\"name\":\"开发者工具\",\"perms\":\"sys:tools\",\"type\":0,\"status\":1,\"orderNum\":2,\"path\":\"/utils\",\"icon\":\"RadiusSettingOutlined\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":0,\"name\":\"开发者工具\",\"path\":\"/utils\",\"perms\":\"sys:tools\",\"type\":0,\"icon\":\"RadiusSettingOutlined\",\"orderNum\":2,\"status\":1,\"children\":[],\"id\":5,\"createdAt\":{\"date\":{\"year\":2021,\"month\":1,\"day\":15},\"time\":{\"hour\":19,\"minute\":6,\"second\":11,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":38,\"second\":47,\"nano\":337000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:38:47', '2023-08-25 11:38:47', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918096491380738, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"5\",\"parentId\":0,\"name\":\"开发者工具\",\"perms\":\"sys:developer\",\"type\":0,\"status\":1,\"orderNum\":2,\"path\":\"/developer\",\"icon\":\"RadiusSettingOutlined\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":0,\"name\":\"开发者工具\",\"path\":\"/developer\",\"perms\":\"sys:developer\",\"type\":0,\"icon\":\"RadiusSettingOutlined\",\"orderNum\":2,\"status\":1,\"children\":[],\"id\":5,\"createdAt\":{\"date\":{\"year\":2021,\"month\":1,\"day\":15},\"time\":{\"hour\":19,\"minute\":6,\"second\":11,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":42,\"second\":18,\"nano\":920000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:42:19', '2023-08-25 11:42:19', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918171921743874, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"6\",\"parentId\":5,\"name\":\"数字字典\",\"perms\":\"sys:dict:list\",\"type\":1,\"status\":1,\"orderNum\":1,\"path\":\"/developer/dict\",\"icon\":\"\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":5,\"name\":\"数字字典\",\"path\":\"/developer/dict\",\"perms\":\"sys:dict:list\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"status\":1,\"children\":[],\"id\":6,\"createdAt\":{\"date\":{\"year\":2021,\"month\":1,\"day\":15},\"time\":{\"hour\":19,\"minute\":7,\"second\":18,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":42,\"second\":36,\"nano\":896000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:42:37', '2023-08-25 11:42:37', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918802384355329, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"6\",\"parentId\":5,\"name\":\"数字字典\",\"perms\":\"developer:dict:list\",\"type\":1,\"status\":1,\"orderNum\":1,\"path\":\"/developer/dict\",\"icon\":\"\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":5,\"name\":\"数字字典\",\"path\":\"/developer/dict\",\"perms\":\"developer:dict:list\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"status\":1,\"children\":[],\"id\":6,\"createdAt\":{\"date\":{\"year\":2021,\"month\":1,\"day\":15},\"time\":{\"hour\":19,\"minute\":7,\"second\":18,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":45,\"second\":7,\"nano\":211000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:45:07', '2023-08-25 11:45:07', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918831362801665, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"41\",\"parentId\":6,\"name\":\"字典列表\",\"perms\":\"developer:dict:list\",\"type\":2,\"status\":1,\"orderNum\":1,\"path\":null,\"icon\":null}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":6,\"name\":\"字典列表\",\"perms\":\"developer:dict:list\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":41,\"createdAt\":{\"date\":{\"year\":2022,\"month\":1,\"day\":19},\"time\":{\"hour\":21,\"minute\":35,\"second\":58,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":45,\"second\":14,\"nano\":119000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:45:14', '2023-08-25 11:45:14', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918851625484289, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"42\",\"parentId\":6,\"name\":\"编辑字典\",\"perms\":\"developer:dict:edit\",\"type\":2,\"status\":1,\"orderNum\":1,\"path\":null,\"icon\":null}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":6,\"name\":\"编辑字典\",\"perms\":\"developer:dict:edit\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":42,\"createdAt\":{\"date\":{\"year\":2022,\"month\":1,\"day\":19},\"time\":{\"hour\":22,\"minute\":49,\"second\":2,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":45,\"second\":18,\"nano\":955000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:45:19', '2023-08-25 11:45:19', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918872383094785, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"43\",\"parentId\":6,\"name\":\"新增字典\",\"perms\":\"developer:dict:save\",\"type\":2,\"status\":1,\"orderNum\":1,\"path\":null,\"icon\":null}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":6,\"name\":\"新增字典\",\"perms\":\"developer:dict:save\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":43,\"createdAt\":{\"date\":{\"year\":2022,\"month\":1,\"day\":19},\"time\":{\"hour\":22,\"minute\":49,\"second\":26,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":45,\"second\":23,\"nano\":906000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:45:24', '2023-08-25 11:45:24', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918892129878018, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"44\",\"parentId\":6,\"name\":\"删除字典\",\"perms\":\"developer:dict:delete\",\"type\":2,\"status\":1,\"orderNum\":1,\"path\":null,\"icon\":null}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":6,\"name\":\"删除字典\",\"perms\":\"developer:dict:delete\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":44,\"createdAt\":{\"date\":{\"year\":2022,\"month\":1,\"day\":19},\"time\":{\"hour\":22,\"minute\":54,\"second\":57,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":45,\"second\":28,\"nano\":618000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:45:29', '2023-08-25 11:45:29', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918917396365314, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"45\",\"parentId\":6,\"name\":\"字典项列表\",\"perms\":\"developer:dict:item:list\",\"type\":2,\"status\":1,\"orderNum\":1,\"path\":null,\"icon\":null}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":6,\"name\":\"字典项列表\",\"perms\":\"developer:dict:item:list\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":45,\"createdAt\":{\"date\":{\"year\":2022,\"month\":1,\"day\":20},\"time\":{\"hour\":22,\"minute\":26,\"second\":0,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":45,\"second\":34,\"nano\":638000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:45:35', '2023-08-25 11:45:35', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918937935872002, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"46\",\"parentId\":6,\"name\":\"新增数据字典项\",\"perms\":\"developer:dict:item:save\",\"type\":2,\"status\":1,\"orderNum\":1,\"path\":null,\"icon\":null}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":6,\"name\":\"新增数据字典项\",\"perms\":\"developer:dict:item:save\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":46,\"createdAt\":{\"date\":{\"year\":2022,\"month\":1,\"day\":20},\"time\":{\"hour\":23,\"minute\":32,\"second\":30,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":45,\"second\":39,\"nano\":531000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:45:40', '2023-08-25 11:45:40', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918973637787649, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"47\",\"parentId\":6,\"name\":\"删除数据字典项\",\"perms\":\"developer:dict:item:delete\",\"type\":2,\"status\":1,\"orderNum\":1,\"path\":null,\"icon\":null}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":6,\"name\":\"删除数据字典项\",\"perms\":\"developer:dict:item:delete\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":47,\"createdAt\":{\"date\":{\"year\":2022,\"month\":1,\"day\":20},\"time\":{\"hour\":23,\"minute\":42,\"second\":49,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":45,\"second\":48,\"nano\":38000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:45:48', '2023-08-25 11:45:48', 1);
INSERT INTO `sys_oper_log`
VALUES (1694918998631645186, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"48\",\"parentId\":6,\"name\":\"更新数据字典项\",\"perms\":\"developer:dict:item:edit\",\"type\":2,\"status\":1,\"orderNum\":1,\"path\":null,\"icon\":null}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":6,\"name\":\"更新数据字典项\",\"perms\":\"developer:dict:item:edit\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":48,\"createdAt\":{\"date\":{\"year\":2022,\"month\":1,\"day\":20},\"time\":{\"hour\":23,\"minute\":47,\"second\":7,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":45,\"second\":54,\"nano\":6000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:45:54', '2023-08-25 11:45:54', 1);
INSERT INTO `sys_oper_log`
VALUES (1694919344129048577, '菜单模块 - 新增菜单', '新增', '新增菜单', 'POST',
        '{\"parentId\":\"5\",\"name\":\"操作日志\",\"perms\":\"developer\",\"type\":1,\"status\":1,\"orderNum\":1}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":5,\"name\":\"操作日志\",\"perms\":\"developer\",\"type\":1,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":1694919344129048577,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":47,\"second\":16,\"nano\":378000000}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":47,\"second\":16,\"nano\":378000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.add', '/sys-menu/save', '127.0.0.1',
        '0.0.1', '2023-08-25 11:47:16', '2023-08-25 11:47:16', 1);
INSERT INTO `sys_oper_log`
VALUES (1694919420004007937, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"1694919344129048577\",\"parentId\":5,\"name\":\"操作日志\",\"perms\":\"developer\",\"type\":1,\"status\":1,\"orderNum\":1,\"path\":\"/developer/oper\",\"icon\":null}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":5,\"name\":\"操作日志\",\"path\":\"/developer/oper\",\"perms\":\"developer\",\"type\":1,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":1694919344129048577,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":47,\"second\":16,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":47,\"second\":34,\"nano\":470000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:47:34', '2023-08-25 11:47:34', 1);
INSERT INTO `sys_oper_log`
VALUES (1694919514392625154, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"1694919344129048577\",\"parentId\":5,\"name\":\"操作日志\",\"perms\":\"developer:oper\",\"type\":1,\"status\":1,\"orderNum\":1,\"path\":\"/developer/oper\",\"icon\":null}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":5,\"name\":\"操作日志\",\"path\":\"/developer/oper\",\"perms\":\"developer:oper\",\"type\":1,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":1694919344129048577,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":47,\"second\":16,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":47,\"second\":56,\"nano\":973000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:47:57', '2023-08-25 11:47:57', 1);
INSERT INTO `sys_oper_log`
VALUES (1694920155793981442, '菜单模块 - 新增菜单', '新增', '新增菜单', 'POST',
        '{\"parentId\":\"1694919344129048577\",\"name\":\"日志列表\",\"perms\":\"developer:oper:log:list\",\"type\":2,\"status\":1,\"orderNum\":1}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":1694919344129048577,\"name\":\"日志列表\",\"perms\":\"developer:oper:log:list\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":1694920155726872578,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":50,\"second\":29,\"nano\":891000000}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":50,\"second\":29,\"nano\":891000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.add', '/sys-menu/save', '127.0.0.1',
        '0.0.1', '2023-08-25 11:50:30', '2023-08-25 11:50:30', 1);
INSERT INTO `sys_oper_log`
VALUES (1694920242511216641, '菜单模块 - 新增菜单', '新增', '新增菜单', 'POST',
        '{\"parentId\":\"1694919344129048577\",\"name\":\"日志详情\",\"perms\":\"developer:oper:log:info\",\"type\":2,\"status\":1,\"orderNum\":1}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":1694919344129048577,\"name\":\"日志详情\",\"perms\":\"developer:oper:log:info\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":1694920242511216642,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":50,\"second\":50,\"nano\":575000000}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":50,\"second\":50,\"nano\":575000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.add', '/sys-menu/save', '127.0.0.1',
        '0.0.1', '2023-08-25 11:50:51', '2023-08-25 11:50:51', 1);
INSERT INTO `sys_oper_log`
VALUES (1694920467812450306, '菜单模块 - 新增菜单', '新增', '新增菜单', 'POST',
        '{\"parentId\":\"5\",\"name\":\"异常日志\",\"perms\":\"developer:exception\",\"type\":1,\"status\":1,\"orderNum\":1,\"path\":\"/developer-exception-log\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":5,\"name\":\"异常日志\",\"path\":\"/developer-exception-log\",\"perms\":\"developer:exception\",\"type\":1,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":1694920467812450306,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":51,\"second\":44,\"nano\":293000000}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":51,\"second\":44,\"nano\":293000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.add', '/sys-menu/save', '127.0.0.1',
        '0.0.1', '2023-08-25 11:51:44', '2023-08-25 11:51:44', 1);
INSERT INTO `sys_oper_log`
VALUES (1694920926803525633, '菜单模块 - 更新菜单', '编辑', '更新菜单', 'PUT',
        '{\"id\":\"6\",\"parentId\":5,\"name\":\"数字字典\",\"perms\":\"developer:dict:list\",\"type\":1,\"status\":1,\"orderNum\":1,\"path\":\"/developer-dict\",\"icon\":\"\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":5,\"name\":\"数字字典\",\"path\":\"/developer-dict\",\"perms\":\"developer:dict:list\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"status\":1,\"children\":[],\"id\":6,\"createdAt\":{\"date\":{\"year\":2021,\"month\":1,\"day\":15},\"time\":{\"hour\":19,\"minute\":7,\"second\":18,\"nano\":0}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":53,\"second\":33,\"nano\":723000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.update', '/sys-menu/update', '127.0.0.1',
        '0.0.1', '2023-08-25 11:53:34', '2023-08-25 11:53:34', 1);
INSERT INTO `sys_oper_log`
VALUES (1694921070630404097, '菜单模块 - 新增菜单', '新增', '新增菜单', 'POST',
        '{\"parentId\":\"1694920467812450306\",\"name\":\"异常日志列表\",\"perms\":\"developer:exception:log:list\",\"type\":2,\"status\":1,\"orderNum\":1}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":1694920467812450306,\"name\":\"异常日志列表\",\"perms\":\"developer:exception:log:list\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":1694921070563295234,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":54,\"second\":8,\"nano\":4000000}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":54,\"second\":8,\"nano\":4000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.add', '/sys-menu/save', '127.0.0.1',
        '0.0.1', '2023-08-25 11:54:08', '2023-08-25 11:54:08', 1);
INSERT INTO `sys_oper_log`
VALUES (1694921182698012673, '菜单模块 - 新增菜单', '新增', '新增菜单', 'POST',
        '{\"parentId\":\"1694920467812450306\",\"name\":\"异常日志详情\",\"perms\":\"developer:exception:log:info\",\"type\":2,\"status\":1,\"orderNum\":1}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"parentId\":1694920467812450306,\"name\":\"异常日志详情\",\"perms\":\"developer:exception:log:info\",\"type\":2,\"orderNum\":1,\"status\":1,\"children\":[],\"id\":1694921182698012674,\"createdAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":54,\"second\":34,\"nano\":726000000}},\"updatedAt\":{\"date\":{\"year\":2023,\"month\":8,\"day\":25},\"time\":{\"hour\":11,\"minute\":54,\"second\":34,\"nano\":726000000}},\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysMenuController.add', '/sys-menu/save', '127.0.0.1',
        '0.0.1', '2023-08-25 11:54:35', '2023-08-25 11:54:35', 1);
INSERT INTO `sys_oper_log`
VALUES (1694961925550505985, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST', '{\"menuIds\":[1,5,6]}',
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/3', '127.0.0.1', '0.0.1',
        '2023-08-25 14:36:29', '2023-08-25 14:36:29', 1);
INSERT INTO `sys_oper_log`
VALUES (1694962067796131841, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST', '{\"menuIds\":[1,5,6]}',
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/3', '127.0.0.1', '0.0.1',
        '2023-08-25 14:37:02', '2023-08-25 14:37:02', 1);
INSERT INTO `sys_oper_log`
VALUES (1694962104932499458, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST',
        '{\"menuIds\":[1,6,41,42,43,44,45,46,47,48]}', '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/3', '127.0.0.1', '0.0.1',
        '2023-08-25 14:37:11', '2023-08-25 14:37:11', 1);
INSERT INTO `sys_oper_log`
VALUES (1694962354929795074, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST',
        '{\"menuIds\":[1,41,42,43,44,45,46,47]}', '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/3', '127.0.0.1', '0.0.1',
        '2023-08-25 14:38:11', '2023-08-25 14:38:11', 1);
INSERT INTO `sys_oper_log`
VALUES (1694962423628300289, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST',
        '{\"menuIds\":[1,41,42,43,44,45,46,47]}', '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/3', '127.0.0.1', '0.0.1',
        '2023-08-25 14:38:27', '2023-08-25 14:38:27', 1);
INSERT INTO `sys_oper_log`
VALUES (1694963199339659265, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST',
        '{\"menuIds\":[1,41,42,43,44,45,46,47,48,6]}', '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/3', '127.0.0.1', '0.0.1',
        '2023-08-25 14:41:32', '2023-08-25 14:41:32', 1);
INSERT INTO `sys_oper_log`
VALUES (1694963489715519490, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST',
        '{\"menuIds\":[1,41,42,43,44,45,46,47]}', '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/3', '127.0.0.1', '0.0.1',
        '2023-08-25 14:42:42', '2023-08-25 14:42:42', 1);
INSERT INTO `sys_oper_log`
VALUES (1694967307488460802, '角色模块 - 角色设置菜单', '编辑', '角色设置菜单', 'POST',
        '{\"menuIds\":[1,5,6,21,22,23,24,27,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,1694919344129048600,1694920155726872600,1694920242511216600,1694920467812450300,1694921070563295200,1694921182698012700]}',
        '{\"code\":200,\"message\":\"success\"}', 1, 'admin',
        'com.pistachio.admin.controller.system.SysRoleController.perm', '/sys-role/perm/1', '127.0.0.1', '0.0.1',
        '2023-08-25 14:57:52', '2023-08-25 14:57:52', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`         bigint(20)                                             NOT NULL AUTO_INCREMENT,
    `name`       varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `code`       varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `remark`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci          DEFAULT NULL COMMENT '备注',
    `created_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `status`     int(11)                                                NOT NULL,
    `is_delete`  int(11)                                                         DEFAULT 1 COMMENT '删除标识',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name` (`name`) USING BTREE,
    UNIQUE INDEX `code` (`code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1689563312691347458
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统角色表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '超级管理员', 'administer', '系统默认最高权限，不可以编辑和任意修改', '2021-01-16 13:29:03',
        '2022-01-05 22:33:59', 1, 1);
INSERT INTO `sys_role`
VALUES (3, '普通用户', 'normal', '只有基本查看功能', '2021-01-04 10:09:14', '2022-01-06 20:32:27', 1, 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`         bigint(20)   NOT NULL AUTO_INCREMENT,
    `role_id`    bigint(20)   NOT NULL,
    `menu_id`    bigint(20)   NOT NULL,
    `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `status`     int(11)      NOT NULL,
    `is_delete`  int(11)               DEFAULT 1 COMMENT '删除标识',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1694979130505822242
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统角色菜单链接表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (1694963489648410625, 3, 1, '2023-08-25 14:42:41', '2023-08-25 14:42:41', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694963489648410626, 3, 41, '2023-08-25 14:42:41', '2023-08-25 14:42:41', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694963489648410627, 3, 42, '2023-08-25 14:42:41', '2023-08-25 14:42:41', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694963489648410628, 3, 43, '2023-08-25 14:42:41', '2023-08-25 14:42:41', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694963489648410629, 3, 44, '2023-08-25 14:42:41', '2023-08-25 14:42:41', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694963489648410630, 3, 45, '2023-08-25 14:42:41', '2023-08-25 14:42:41', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694963489648410631, 3, 46, '2023-08-25 14:42:41', '2023-08-25 14:42:41', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694963489648410632, 3, 47, '2023-08-25 14:42:41', '2023-08-25 14:42:41', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130447101954, 1, 1, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822209, 1, 5, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822210, 1, 6, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822211, 1, 21, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822212, 1, 22, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822213, 1, 23, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822214, 1, 24, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822215, 1, 27, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822216, 1, 29, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822217, 1, 30, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822218, 1, 31, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822219, 1, 32, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822220, 1, 33, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822221, 1, 34, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822222, 1, 35, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822223, 1, 36, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822224, 1, 37, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822225, 1, 38, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822226, 1, 39, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822227, 1, 40, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822228, 1, 41, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822229, 1, 42, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822230, 1, 43, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822231, 1, 44, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822232, 1, 45, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822233, 1, 46, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822234, 1, 47, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822235, 1, 48, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822236, 1, 1694919344129048577, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822237, 1, 1694920155726872578, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822238, 1, 1694920242511216642, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822239, 1, 1694920467812450306, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822240, 1, 1694921070563295234, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);
INSERT INTO `sys_role_menu`
VALUES (1694979130505822241, 1, 1694921182698012674, '2023-08-25 15:44:51', '2023-08-25 15:44:51', 1, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`         bigint(20)  NOT NULL AUTO_INCREMENT,
    `nickname`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '昵称',
    `username`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL,
    `password`   varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `type`       int(11)                                                 DEFAULT 1 COMMENT '用户类型: 1管理员; 2普通用户; ',
    `avatar`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `email`      varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL,
    `city`       varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL,
    `last_login` datetime(0)                                             DEFAULT NULL,
    `created_at` datetime(0) NOT NULL,
    `updated_at` datetime(0)                                             DEFAULT NULL,
    `status`     int(11)     NOT NULL,
    `is_delete`  int(11)                                                 DEFAULT 1 COMMENT '删除标识',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `UK_USERNAME` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1689541974618537987
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, '超级管理员', 'admin',
        '7533cd73daf8f5f723a97b6728f24bd9bb7d3c032ae7bd95b4928242e75aa1c1babed5aa3040294d63297186e8d4baf4f3c241ca27afb393c80d90de8a2e8b081a6938f2ab0c319a43d506170c602e951df13fc1104e36847dec3f00c8abfa1ed7bd6860b7d05918b6e98e6502da49942ac8f8eef69d7a7be0359c279d68e2bd',
        1, NULL, NULL, NULL, NULL, '2021-10-02 22:44:46', NULL, 1, 1);
INSERT INTO `sys_user`
VALUES (1689541974618537986, '测试管理员', 'test_user',
        '0b02361c545e299b2a90ce35c67485e98c045e46ca75cf9913f78b1def7ede1af77908192c2ccc1878d1cefbfcf70bdbae3b96b1c164d61f97a3a9c56fcb8988cd63b9bc1253ba0e916bec1b5022c3333cc302a7c9ac91a24d3a1acc673afd2f8c4adacef8297f7421c45d0deadac2bd5314aeec289490492236ad9687455248',
        NULL,
        'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
        'test_user@qq.com', NULL, NULL, '2023-08-10 15:39:32', '2023-08-10 15:39:32', 1, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`         bigint(20)  NOT NULL AUTO_INCREMENT,
    `user_id`    bigint(20)  NOT NULL,
    `role_id`    bigint(20)  NOT NULL,
    `created_at` datetime(0) NOT NULL,
    `updated_at` datetime(0) DEFAULT NULL,
    `status`     int(11)     NOT NULL,
    `is_delete`  int(11)     DEFAULT 1 COMMENT '删除标识',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1689542023536705538
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统管理员角色链接表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (13, 2, 3, '2021-01-17 21:56:36', NULL, 1, 1);
INSERT INTO `sys_user_role`
VALUES (28, 6, 1, '2022-01-07 15:48:49', NULL, 1, 1);
INSERT INTO `sys_user_role`
VALUES (1689214263341613058, 1, 1, '2023-08-09 17:57:19', '2023-08-09 17:57:19', 1, 1);
INSERT INTO `sys_user_role`
VALUES (1689214263375167490, 1, 3, '2023-08-09 17:57:19', '2023-08-09 17:57:19', 1, 1);
INSERT INTO `sys_user_role`
VALUES (1689542023536705537, 1689541974618537986, 3, '2023-08-10 15:39:43', '2023-08-10 15:39:43', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
