package com.pistachio.system.generator;

import com.pistachio.common.Sequence;
import com.pistachio.common.utils.uuid.IdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author: Pengsy
 * @date: 2023/08/07 16:52
 * @description: 雪花算法 - id生成器
 */
public class SnowFlakeIdGenerator implements IdentifierGenerator {

    @Value("${snowflake.worker-id}")
    private long workerId;

    @Value("${snowflake.datacenter-id}")
    private long dataCenterId;

    private final Sequence sequence;

    public SnowFlakeIdGenerator() {
        this.sequence = new Sequence(workerId, dataCenterId);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return sequence.nextId();
    }
}
