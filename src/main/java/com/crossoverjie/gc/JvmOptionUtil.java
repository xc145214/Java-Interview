package com.crossoverjie.gc;

import com.sun.management.VMOption;
import sun.management.HotSpotDiagnostic;

import java.util.List;

/**
 * @author: xiac
 * @date: 2018/8/2
 * @desc: jvm参数工具
 */
public final class JvmOptionUtil {

    private JvmOptionUtil(){};

    /**
     * 获取JVM 参数列表
     *
     * @return
     */
    public static List<VMOption> getVmOptions(){
        HotSpotDiagnostic mxBean = new HotSpotDiagnostic();
        return mxBean.getDiagnosticOptions();
    }

}
