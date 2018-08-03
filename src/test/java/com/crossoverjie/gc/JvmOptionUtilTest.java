package com.crossoverjie.gc;

import com.sun.management.VMOption;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: xiac
 * @date: 2018/8/2
 * @desc: 一句话描述
 */
public class JvmOptionUtilTest {


    private Logger LOG = LoggerFactory.getLogger(JvmOptionUtilTest.class);

    @Test
    public void printVmOptions() {
        List<VMOption> list = JvmOptionUtil.getVmOptions();
        assertNotNull(list);
        for (VMOption vmOption : list) {
            System.out.println(vmOption.getName() + " = " + vmOption.getValue());
            LOG.debug(vmOption.getName() + " = " + vmOption.getValue());
        }
    }
}